package bakatd.mc.rualauncher.neko.runtime.download;

public class StatusRefresherThread extends Thread {
    private FileDownloader fileDownloader;

    public StatusRefresherThread(FileDownloader fileDownloader){
        this.fileDownloader = fileDownloader;
    }

    @Override
    public void run() {
        while(fileDownloader.getStatus() != FileDownloader.DownloadStatus.SUCCESS && fileDownloader.getStatus() != FileDownloader.DownloadStatus.FAILED){
            if(fileDownloader.getProcess()>=1){
                fileDownloader.stop();
                return;
            }
        }
    }

    public FileDownloader getFileDownloader() {
        return fileDownloader;
    }
}
