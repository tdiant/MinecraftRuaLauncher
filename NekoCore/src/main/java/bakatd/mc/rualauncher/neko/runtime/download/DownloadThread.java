package bakatd.mc.rualauncher.neko.runtime.download;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;

public class DownloadThread extends Thread {
    private int id;
    private FileDownloader fileDownloader;

    private int finishSize = 0;
    private FileDownloader.DownloadStatus status = FileDownloader.DownloadStatus.INIT;

    public DownloadThread(int id, FileDownloader fileDownloader) {
        this.id = id;
        this.fileDownloader = fileDownloader;

        status = FileDownloader.DownloadStatus.READY;
    }

    public void run() {
        if (isInterrupted()) return;

        int start = id * fileDownloader.getThreadLen();
        int end = id * fileDownloader.getThreadLen() + fileDownloader.getThreadLen() - 1;

        try {
            HttpURLConnection conn = (HttpURLConnection) fileDownloader.getAUrl().openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Range", "bytes=" + start + "-" + end);

            InputStream in = conn.getInputStream();
            RandomAccessFile raf = new RandomAccessFile(fileDownloader.getFile(), "rws");
            raf.seek(start);

            status = FileDownloader.DownloadStatus.DOWNLOADING;

            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) != -1) {
                raf.write(buffer, 0, len);
                finishSize += len;
            }
            raf.close();

            status = FileDownloader.DownloadStatus.SUCCESS;
        } catch (IOException e) {
            status = FileDownloader.DownloadStatus.FAILED;
            e.printStackTrace();
        }
    }

    public double getProcess() {
        return finishSize / fileDownloader.getThreadLen() + 0.01;
    }

    public FileDownloader.DownloadStatus getStatus() {
        return status;
    }
}

