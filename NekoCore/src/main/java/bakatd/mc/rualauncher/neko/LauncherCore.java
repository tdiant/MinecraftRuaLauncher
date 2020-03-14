package bakatd.mc.rualauncher.neko;

import bakatd.mc.rualauncher.neko.core.version.GameVersion;
import bakatd.mc.rualauncher.neko.runtime.json.MainReader;
import bakatd.mc.rualauncher.neko.runtime.launch.MainLauncher;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LauncherCore {
    private String launcherBrand;
    private String launcherVersion;

    private String javaBinDir;

    private GameVersion gameVersion;

    private JSONObject mainJson;

    private Map<String, Boolean> featureMap = new HashMap<>();
    private Map<String, String> replaceMap = new HashMap<>();

    public LauncherCore(GameVersion gameVersion, String javaBinDir){
        this.gameVersion = gameVersion;
        this.javaBinDir = javaBinDir;

        this.mainJson = gameVersion.getMainJson();
        this.replaceMap.put("${natives_directory}", gameVersion.getUrlOfNative());
    }

    public void startGame() throws Exception {
        String str = new MainLauncher(
                new MainReader(mainJson).read(),
                this
        ).launcher();

        /*Runtime run = Runtime.getRuntime();
        Process p = run.exec(str);*/
        System.out.println(str);
    }

    public Map<String, Boolean> getFeatureMap() {
        return featureMap;
    }

    public Map<String, String> getReplaceMap() {
        return replaceMap;
    }

    public String replaceToRuaString(String str){
        for(String key : replaceMap.keySet())
            str = str.replace(key,replaceMap.get(key));
        return str;
    }

    public String getLauncherBrand() {
        return launcherBrand;
    }

    public void setLauncherBrand(String launcherBrand) {
        this.launcherBrand = launcherBrand;
        this.replaceMap.put("${launcher_name}",launcherBrand);
    }

    public String getLauncherVersion() {
        return launcherVersion;
    }

    public void setLauncherVersion(String launcherVersion) {
        this.launcherVersion = launcherVersion;
        this.replaceMap.put("${launcher_version}",launcherVersion);
    }

    public String getJavaBinDir() {
        return javaBinDir;
    }

    public void setJavaBinDir(String javaBinDir) {
        this.javaBinDir = javaBinDir;
    }

    public JSONObject getMainJson() {
        return mainJson;
    }

    public void setMainJson(JSONObject mainJson) {
        this.mainJson = mainJson;
    }

    public void setFeatureMap(Map<String, Boolean> featureMap) {
        this.featureMap = featureMap;
    }

    public void setReplaceMap(Map<String, String> replaceMap) {
        this.replaceMap = replaceMap;
    }

    public LauncherCore pushReplaceArg(String key, String value){
        replaceMap.put(key,value);
        return this;
    }

    public GameVersion getGameVersion() {
        return gameVersion;
    }

    public void setGameVersion(GameVersion gameVersion) {
        this.gameVersion = gameVersion;
    }
}
