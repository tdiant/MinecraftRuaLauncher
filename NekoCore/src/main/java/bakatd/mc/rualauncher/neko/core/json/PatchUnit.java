package bakatd.mc.rualauncher.neko.core.json;

import java.util.ArrayList;
import java.util.List;

public class PatchUnit {
    private String id;
    private String mainClass;
    private String minimumLauncherVersion;
    private String releaseTime;
    private String time;
    private String type;

    private String assets;
    private AssetIndexUnit assetIndex;

    private List<PatchDownloadUnit> patchDownloadUnitList = new ArrayList<>();
    private List<LibraryUnit> libraryUnitList = new ArrayList<>();

    private List<ArgumentUnit> argumentOfGameList = new ArrayList<>();
    private List<ArgumentUnit> argumentOfJVMList = new ArrayList<>();

    private String version;
    private int priority;

    public PatchUnit(String id, String mainClass, String minimumLauncherVersion, String releaseTime, String time, String type, String assets, AssetIndexUnit assetIndex, List<PatchDownloadUnit> patchDownloadUnitList, List<LibraryUnit> libraryUnitList, List<ArgumentUnit> argumentOfGameList, List<ArgumentUnit> argumentOfJVMList) {
        this.id = id;
        this.mainClass = mainClass;
        this.minimumLauncherVersion = minimumLauncherVersion;
        this.releaseTime = releaseTime;
        this.time = time;
        this.type = type;
        this.assets = assets;
        this.assetIndex = assetIndex;
        this.patchDownloadUnitList = patchDownloadUnitList;
        this.libraryUnitList = libraryUnitList;
        this.argumentOfGameList = argumentOfGameList;
        this.argumentOfJVMList = argumentOfJVMList;
    }

    @Deprecated
    public PatchUnit(String id, String mainClass, String minimumLauncherVersion, String releaseTime, String time, String type, String assets) {
        this.id = id;
        this.mainClass = mainClass;
        this.minimumLauncherVersion = minimumLauncherVersion;
        this.releaseTime = releaseTime;
        this.time = time;
        this.type = type;
        this.assets = assets;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public String getMinimumLauncherVersion() {
        return minimumLauncherVersion;
    }

    public void setMinimumLauncherVersion(String minimumLauncherVersion) {
        this.minimumLauncherVersion = minimumLauncherVersion;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAssets() {
        return assets;
    }

    public void setAssets(String assets) {
        this.assets = assets;
    }

    public AssetIndexUnit getAssetIndex() {
        return assetIndex;
    }

    public void setAssetIndex(AssetIndexUnit assetIndex) {
        this.assetIndex = assetIndex;
    }

    public List<PatchDownloadUnit> getPatchDownloadUnitList() {
        return patchDownloadUnitList;
    }

    public void setPatchDownloadUnitList(List<PatchDownloadUnit> patchDownloadUnitList) {
        this.patchDownloadUnitList = patchDownloadUnitList;
    }

    public List<LibraryUnit> getLibraryUnitList() {
        return libraryUnitList;
    }

    public void setLibraryUnitList(List<LibraryUnit> libraryUnitList) {
        this.libraryUnitList = libraryUnitList;
    }

    public List<ArgumentUnit> getArgumentOfGameList() {
        return argumentOfGameList;
    }

    public void setArgumentOfGameList(List<ArgumentUnit> argumentOfGameList) {
        this.argumentOfGameList = argumentOfGameList;
    }

    public List<ArgumentUnit> getArgumentOfJVMList() {
        return argumentOfJVMList;
    }

    public void setArgumentOfJVMList(List<ArgumentUnit> argumentOfJVMList) {
        this.argumentOfJVMList = argumentOfJVMList;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
