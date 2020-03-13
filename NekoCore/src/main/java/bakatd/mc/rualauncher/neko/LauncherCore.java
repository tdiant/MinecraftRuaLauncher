package bakatd.mc.rualauncher.neko;

import bakatd.mc.rualauncher.neko.runtime.json.MainReader;
import bakatd.mc.rualauncher.neko.runtime.launch.MainLauncher;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LauncherCore {
    private String launcherBrand;
    private String launcherVersion;

    private String homeDir;
    private String libDir;
    private String javaBinDir;
    private String gameName;

    private JSONObject mainJson;

    private Map<String, Boolean> featureMap = new HashMap<>();
    private Map<String, String> replaceMap = new HashMap<>();

    public LauncherCore(String homeDir, String javaBinDir, String libDir, String DjavaLibraryPath, String gameName, JSONObject mainJson){
        this.homeDir = homeDir;
        this.javaBinDir = javaBinDir;
        this.libDir = libDir;
        this.gameName = gameName;
        this.mainJson = mainJson;

        this.replaceMap.put("${natives_directory}", DjavaLibraryPath);
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

    public String getLibDir() {
        return libDir;
    }

    public void setLibDir(String libDir) {
        this.libDir = libDir;
    }

    public String getHomeDir() {
        return homeDir;
    }

    public void setHomeDir(String homeDir) {
        this.homeDir = homeDir;
    }

    public String getJavaBinDir() {
        return javaBinDir;
    }

    public void setJavaBinDir(String javaBinDir) {
        this.javaBinDir = javaBinDir;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
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
}
