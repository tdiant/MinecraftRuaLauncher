package bakatd.mc.rualauncher.neko.runtime.json;

import bakatd.mc.rualauncher.neko.core.GameMasterUnit;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainReader {
    private JSONObject mainJson;

    public MainReader(JSONObject mainJson) {
        this.mainJson = mainJson;
    }

    public GameMasterUnit read() throws Exception {
        GameMasterUnit gameMasterUnit;
        List<JSONObject> patchJsonList = new ArrayList<>();

        if(mainJson.containsKey("patches")) {
            gameMasterUnit = new GameMasterUnit(
                    mainJson.getString("id"),
                    mainJson.getBoolean("root"),
                    mainJson.getBoolean("hidden")
            );
            for(Object ruaObject : mainJson.getJSONArray("patches")){
                if(!(ruaObject instanceof JSONObject)){
                    throw new Exception("Wrong Patches");
                    //continue;
                }
                patchJsonList.add((JSONObject) ruaObject);
            }
        } else {
            gameMasterUnit = new GameMasterUnit(
                    mainJson.getString("id"),
                    true,
                    false
            );
            patchJsonList.add(mainJson);
        }

        for(JSONObject patchJson : patchJsonList){
            gameMasterUnit.pushPatch(new PatchReader(patchJson).read());
        }

        return gameMasterUnit;
    }

    public JSONObject getMainJson() {
        return mainJson;
    }

    public void setMainJson(JSONObject mainJson) {
        this.mainJson = mainJson;
    }
}
