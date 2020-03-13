package bakatd.mc.rualauncher.neko.core;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryUnit {
    private String name;
    private Map<String, String> nativesMap = new HashMap<>();

    private List<PatchDownloadUnit> downloadOfArtifactList = new ArrayList<>();
    private List<PatchDownloadUnit> downloadOfClassifiersList = new ArrayList<>();

    private List<RuleUnit> ruleUnitList = new ArrayList<>();

    public LibraryUnit(String name, Map<String, String> nativesMap, List<PatchDownloadUnit> downloadOfArtifactList, List<PatchDownloadUnit> downloadOfClassifiersList, List<RuleUnit> ruleUnitList) {
        this.name = name;
        this.nativesMap = nativesMap;
        this.downloadOfArtifactList = downloadOfArtifactList;
        this.downloadOfClassifiersList = downloadOfClassifiersList;
        this.ruleUnitList = ruleUnitList;
    }

    public String getName() {
        return name;
    }

    public String getPath(){
        String path = "";
        //dir
        String[] args = getName().split(":");
        path += args[0].replace(".", File.separator) + File.separator;
        path += args[1].replace(".", File.separator) + File.separator;
        path += args[2] + File.separator;
        //file name
        path += args[1] + "-" + args[2] + ".jar";
        return path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getNativesMap() {
        return nativesMap;
    }

    public void setNativesMap(Map<String, String> nativesMap) {
        this.nativesMap = nativesMap;
    }

    public List<PatchDownloadUnit> getDownloadOfArtifactList() {
        return downloadOfArtifactList;
    }

    public void setDownloadOfArtifactList(List<PatchDownloadUnit> downloadOfArtifactList) {
        this.downloadOfArtifactList = downloadOfArtifactList;
    }

    public List<PatchDownloadUnit> getDownloadOfClassifiersList() {
        return downloadOfClassifiersList;
    }

    public void setDownloadOfClassifiersList(List<PatchDownloadUnit> downloadOfClassifiersList) {
        this.downloadOfClassifiersList = downloadOfClassifiersList;
    }

    public List<RuleUnit> getRuleUnitList() {
        return ruleUnitList;
    }

    public void setRuleUnitList(List<RuleUnit> ruleUnitList) {
        this.ruleUnitList = ruleUnitList;
    }
}
