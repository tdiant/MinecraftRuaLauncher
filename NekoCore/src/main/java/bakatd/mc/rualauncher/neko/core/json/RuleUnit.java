package bakatd.mc.rualauncher.neko.core.json;

import java.util.HashMap;
import java.util.Map;

public class RuleUnit {
    private String action;

    private String osName;
    private String osVersion;
    private String osArch;

    private Map<String, Boolean> featuresMap = new HashMap<>();

    public RuleUnit(String action) {
        this.action = action;
    }

    public RuleUnit(String action, String osName) {
        this.action = action;
        this.osName = osName;
    }

    public RuleUnit(String action, String osName, String osVersion, String osArch, Map<String, Boolean> featuresMap) {
        this.action = action;
        this.osName = osName;
        this.osVersion = osVersion;
        this.osArch = osArch;
        this.featuresMap = featuresMap;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getOsArch() {
        return osArch;
    }

    public void setOsArch(String osArch) {
        this.osArch = osArch;
    }

    public Map<String, Boolean> getFeaturesMap() {
        return featuresMap;
    }

    public void setFeaturesMap(Map<String, Boolean> featuresMap) {
        this.featuresMap = featuresMap;
    }

    public void pushFeature(String feature, boolean b){
        this.featuresMap.put(feature,b);
    }
}
