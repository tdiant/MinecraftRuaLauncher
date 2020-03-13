package bakatd.mc.rualauncher.neko.runtime.json;

import bakatd.mc.rualauncher.neko.core.*;
import bakatd.mc.rualauncher.neko.fuck.PFuck;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PatchReader {
    private JSONObject patchJson;

    public PatchReader(JSONObject patchJson) {
        this.patchJson = patchJson;
    }

    public PatchUnit read(){
        String id = patchJson.getString("id");
        String mainClass = patchJson.getString("mainClass");
        String minimumLauncherVersion = patchJson.getString("minimumLauncherVersion");
        String releaseTime = patchJson.getString("releaseTime");
        String time = patchJson.getString("time");
        String type = patchJson.getString("type");
        String assets = patchJson.getString("assets");

        AssetIndexUnit assetIndex;
        List<PatchDownloadUnit> patchDownloadUnitList = new ArrayList<>();
        List<LibraryUnit> libraryUnitList = new ArrayList<>();
        List<ArgumentUnit> argumentOfGameList = new ArrayList<>();
        List<ArgumentUnit> argumentOfJVMList = new ArrayList<>();

        //Load AssetsIndex
        assetIndex = new AssetIndexUnit(
                PFuck.fuckS(patchJson,"assetIndex","id"),
                PFuck.fuckS(patchJson,"assetIndex","sha1"),
                PFuck.fuckB(patchJson,"assetIndex","size"),
                PFuck.fuckB(patchJson,"assetIndex","totalSize"),
                PFuck.fuckS(patchJson,"assetIndex","url")
        );

        //Load Downloads
        for(String pDownName : patchJson.getJSONObject("downloads").keySet()){
            JSONObject downJson = patchJson.getJSONObject("downloads").getJSONObject(pDownName);
            patchDownloadUnitList.add(new PatchDownloadUnit(
                    pDownName,
                    downJson.getString("sha1"),
                    downJson.getBigInteger("size"),
                    downJson.getString("url")
            ));
        }

        //Scan Libs
        for(Object obj1 : patchJson.getJSONArray("libraries")){
            if(!(obj1 instanceof JSONObject)) continue;

            JSONObject libJson = (JSONObject) obj1;
            libraryUnitList.add(new LibReader(libJson).read());
        }

        //Load Args
        //What's the type?
        if(patchJson.containsKey("minecraftArguments")){  //Old
            argumentOfGameList.addAll(new OldArgumentReader(patchJson.getString("minecraftArguments")).read());
        } else if(patchJson.containsKey("arguments")){ //New
            if(patchJson.getJSONObject("arguments").containsKey("game")){
                JSONArray aJson = patchJson.getJSONObject("arguments").getJSONArray("game");
                argumentOfGameList.addAll(new NewArgumentReader(aJson).read());
            }

            if(patchJson.getJSONObject("arguments").containsKey("jvm")){
                JSONArray aJson = patchJson.getJSONObject("arguments").getJSONArray("jvm");
                argumentOfJVMList.addAll(new NewArgumentReader(aJson).read());
            }
        }

        PatchUnit patchUnit = new PatchUnit(
                id,mainClass,minimumLauncherVersion,releaseTime,time,type,assets,assetIndex,patchDownloadUnitList,libraryUnitList,argumentOfGameList,argumentOfJVMList
        );

        patchUnit.setVersion(
                patchJson.containsKey("version")?patchJson.getString("version"):""
        );

        patchUnit.setPriority(
                patchJson.containsKey("priority")?patchJson.getInteger("priority"):0
        );

        return patchUnit;
    }

    public JSONObject getPatchJson() {
        return patchJson;
    }

    public void setPatchJson(JSONObject patchJson) {
        this.patchJson = patchJson;
    }

    //WTF???!!!Why I put this fucking code twice!!!!
    //FUCK MY BRAIN!!!!!!!!!!
    //FUCK MY BRAIN!!!!!!!!!!
    //FUCK MY BRAIN!!!!!!!!!!
    //FUCK FUCK FUCK FUCK FUCK FUCK FUCK FUCK
    //FUCK FUCK FUCK FUCK FUCK FUCK FUCK FUCK
    @Deprecated
    private List<ArgumentUnit> __ruaGet(String name){
        if(patchJson.getJSONObject("arguments").containsKey(name)){
            List<ArgumentUnit> returnBack = new ArrayList<>();
            for(Object obj : patchJson.getJSONObject("arguments").getJSONArray(name)){
                if(obj instanceof String){
                    returnBack.addAll(new OldArgumentReader((String) obj).read());
                }else if(obj instanceof JSONObject){

                }
            }
            return returnBack;
        } else {
            return new ArrayList<>();
        }
    }
}
