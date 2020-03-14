package bakatd.mc.rualauncher.neko.runtime.download;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileDownloader {
    private String url;
    private String path;
    private String name;
    private int threadNum;

    private DownloadStatus status = DownloadStatus.INIT;

    private File file;
    private URL aUrl;
    private int threadLen = 0;
    private List<DownloadThread> threadList = new ArrayList<>();

    public static enum DownloadStatus{
        INIT, READY, DOWNLOADING, SUCCESS, FAILED
    }

    public FileDownloader(String url, String path, String name, int threadNum) throws MalformedURLException {
        this.url = url;
        this.path = path;
        this.name = name;
        this.threadNum = threadNum;

        aUrl = new URL(url);
        File dir = new File(path);
        if(!dir.exists())
            dir.mkdirs();
        file = new File(dir, name);

        this.status = DownloadStatus.READY;
    }

    public void download() throws IOException {
        HttpURLConnection conn = (HttpURLConnection) aUrl.openConnection();
        conn.setConnectTimeout(5000);

        int totalLen = conn.getContentLength(); //File Total Length
        threadLen = (totalLen + threadNum - 1) / threadNum; //Each Thread Length

        //Create File
        RandomAccessFile raf = new RandomAccessFile(file, "rws");
        raf.setLength(totalLen);
        raf.close();

        //Create Thread
        for (int i = 0; i < threadNum; i++) {
            DownloadThread thread = new DownloadThread(i,this);
            threadList.add(thread);
            thread.start();
        }

        //Start Download
        status = DownloadStatus.DOWNLOADING;

        //Watchdog start
        new StatusRefresherThread(this).start();
    }

    public DownloadStatus getStatus() {
        return status;
    }

    public double getProcess() {
        double process = 0.0;
        for (DownloadThread thread : threadList)
            process += thread.getProcess() * (1.0 / threadList.size());
        if (process >= 1)
            status = DownloadStatus.SUCCESS;
        return process>=1?1:
                process<=0?0:process;
    }

    public String getName() {
        return name;
    }

    public void stop(){
        for(DownloadThread thread : threadList)
            thread.interrupt();

        if(status!=DownloadStatus.DOWNLOADING)
            return;

        if(getProcess()>=1.0){
            status = DownloadStatus.SUCCESS;
        }else {
            status = DownloadStatus.FAILED;
        }
    }

    public int getThreadLen() {
        return threadLen;
    }

    public URL getAUrl() {
        return aUrl;
    }

    public File getFile() {
        return file;
    }
}

