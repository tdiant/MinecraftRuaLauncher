package bakatd.mc.rualauncher.neko.core.version;

import bakatd.mc.rualauncher.neko.fuck.FFuck;
import bakatd.mc.rualauncher.neko.manager.VersionManager;
import com.alibaba.fastjson.JSONObject;

import java.io.File;

public class GameVersion {
    private String gameName;
    private String urlOfNative;

    public GameVersion(String gameName, String urlOfNative) {
        this.gameName = gameName;
        this.urlOfNative = urlOfNative;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getUrlOfNative() {
        return urlOfNative;
    }

    public void setUrlOfNative(String urlOfNative) {
        this.urlOfNative = urlOfNative;
    }

    public JSONObject getMainJson(){
        String str = FFuck.fuckFile(VersionManager.getHomeDir()+"versions\\"+gameName+"\\"+gameName+".json");
        return JSONObject.parseObject(str);
    }

    @Override
    public String toString() {
        return "[NekoGameVersion]::GameName" + this.getGameName()+"::NATIVES::"+this.getUrlOfNative();
    }
}
