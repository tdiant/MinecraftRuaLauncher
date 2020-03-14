package bakatd.mc.rualauncher.neko.manager;

import bakatd.mc.rualauncher.neko.LauncherCore;
import bakatd.mc.rualauncher.neko.core.version.GameVersion;

import java.io.File;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.List;

public class VersionManager {
    private static String homeDir;
    private String libDir;

    private static List<GameVersion> gameVersionList = new ArrayList<>();

    public static void init(String homeDir){
        VersionManager.homeDir = homeDir;
        VersionManager.refreshVersions();
    }

    public static List<GameVersion> refreshVersions(){
        gameVersionList.clear();
        for(File f:new File(getHomeDir()+"versions").listFiles()){
            if(!f.isDirectory()) continue;
            if(!new File(f,f.getName()+".json").exists()) continue;

            gameVersionList.add(new GameVersion(f.getName(),homeDir+"versions\\"+f.getName()+"\\natives"));
        }

        return gameVersionList;
    }

    public static List<GameVersion> getGameVersionList() {
        return gameVersionList;
    }

    public static String getHomeDir() {
        return homeDir;
    }

    public static void setHomeDir(String homeDir) {
        VersionManager.homeDir = homeDir;
    }

    public String getLibrariesDir() {
        return libDir;
    }

    public void setLibrariesDir(String libDir) {
        this.libDir = libDir;
    }

    public String getUrlOfVersion(GameVersion gameVersion){
        return VersionManager.getHomeDir()+"versions\\"+ gameVersion.getGameName()+"\\";
    }

    public static String getUrlOfLibraries(){
        return VersionManager.getHomeDir()+"libraries\\";
    }

    public static GameVersion getGameVersionByName(String gameName){
        for(GameVersion gameVersion : gameVersionList){
            if(gameVersion.getGameName().equals(gameName))
                return gameVersion;
        }
        return null;
    }
}
