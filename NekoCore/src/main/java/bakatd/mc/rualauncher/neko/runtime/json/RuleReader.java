package bakatd.mc.rualauncher.neko.runtime.json;

import bakatd.mc.rualauncher.neko.core.RuleUnit;
import bakatd.mc.rualauncher.neko.fuck.PFuck;
import com.alibaba.fastjson.JSONObject;

public class RuleReader {
    private JSONObject ruleJson;

    public RuleUnit read(){
        RuleUnit ruleUnit = new RuleUnit(ruleJson.getString("action"));

        if(ruleJson.containsKey("os")){
            JSONObject osJson = ruleJson.getJSONObject("os");

            if(osJson.containsKey("name"))
                ruleUnit.setOsName(osJson.getString("name"));
            if(osJson.containsKey("version"))
                ruleUnit.setOsVersion(osJson.getString("version"));
            if(osJson.containsKey("arch"))
                ruleUnit.setOsArch(osJson.getString("arch"));
        }

        if(ruleJson.containsKey("features")){
            for(String fStr : ruleJson.getJSONObject("features").keySet()){
                ruleUnit.pushFeature(fStr, PFuck.fuckBL(ruleJson,"features",fStr));
            }
        }

        return ruleUnit;
    }

    public RuleReader(JSONObject ruleJson) {
        this.ruleJson = ruleJson;
    }

    public JSONObject getRuleJson() {
        return ruleJson;
    }

    public void setRuleJson(JSONObject ruleJson) {
        this.ruleJson = ruleJson;
    }
}
