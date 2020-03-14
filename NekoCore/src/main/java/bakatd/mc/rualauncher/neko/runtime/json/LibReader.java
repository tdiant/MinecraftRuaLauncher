package bakatd.mc.rualauncher.neko.runtime.json;

import bakatd.mc.rualauncher.neko.core.json.LibraryUnit;
import bakatd.mc.rualauncher.neko.core.json.PatchDownloadUnit;
import bakatd.mc.rualauncher.neko.core.json.RuleUnit;
import bakatd.mc.rualauncher.neko.fuck.PFuck;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibReader {
    private JSONObject libJson;

    public LibReader(JSONObject libJson){
        this.libJson = libJson;
    }

    public LibraryUnit read(){
        String name = libJson.getString("name");
        Map<String, String> nativesMap = new HashMap<>();

        List<PatchDownloadUnit> downloadOfArtifactList = new ArrayList<>();
        List<PatchDownloadUnit> downloadOfClassifiersList = new ArrayList<>();

        List<RuleUnit> ruleUnitList = new ArrayList<>();

        //Load Downloads
        if(libJson.containsKey("downloads")){
            JSONObject downJson = libJson.getJSONObject("downloads");
            //Artifact
            if(downJson.containsKey("artifact")){ //However it is only one!
                downloadOfArtifactList.add(new PatchDownloadUnit(
                        "artifact",
                        PFuck.fuckS(downJson,"artifact","sha1"),
                        PFuck.fuckB(downJson,"artifact","size"),
                        PFuck.fuckS(downJson,"artifact","url")
                ));
            }

            //Classifiers
            if(downJson.containsKey("classifiers")){
                for(String cDownName : downJson.getJSONObject("classifiers").keySet()){
                    JSONObject cDownJson = downJson.getJSONObject("classifiers").getJSONObject(cDownName);
                    downloadOfClassifiersList.add(new PatchDownloadUnit(
                            cDownJson.getString("name"),
                            cDownJson.getString("sha1"),
                            cDownJson.getBigInteger("size"),
                            cDownJson.getString("url")
                    ));
                }
            }
        }

        //Load Natives
        if(libJson.containsKey("natives")){
            JSONObject nativeJson = libJson.getJSONObject("natives");
            for(String key : nativeJson.keySet()){
                nativesMap.put(key, nativeJson.getString(key));
            }
        }

        //Load Rules
        if(libJson.containsKey("rules")){
            for(Object obj1 : libJson.getJSONArray("rules")){
                if(!(obj1 instanceof JSONObject)) continue;

                JSONObject ruleJson = (JSONObject) obj1;
                ruleUnitList.add(new RuleReader(ruleJson).read());
            }
        }

        return new LibraryUnit(name,nativesMap,downloadOfArtifactList,downloadOfClassifiersList,ruleUnitList);
    }

    public JSONObject getLibJson() {
        return libJson;
    }

    public void setLibJson(JSONObject libJson) {
        this.libJson = libJson;
    }
}
