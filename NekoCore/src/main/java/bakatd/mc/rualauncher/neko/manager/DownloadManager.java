package bakatd.mc.rualauncher.neko.manager;

import bakatd.mc.rualauncher.neko.runtime.download.FileDownloader;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class DownloadManager {
    private static List<FileDownloader> fileDownloaderList = new ArrayList<>();
    private static int threadNum = 4;

    public static List<FileDownloader> getFileDownloaderList() {
        return fileDownloaderList;
    }

    public static FileDownloader addTask(String url, String path, String name) throws MalformedURLException {
        FileDownloader fileDownloader = new FileDownloader(url,path,name,threadNum);
        fileDownloaderList.add(fileDownloader);
        return fileDownloader;
    }

    public static int getThreadNum() {
        return threadNum;
    }

    public static void setThreadNum(int threadNum) {
        DownloadManager.threadNum = threadNum;
    }
}
