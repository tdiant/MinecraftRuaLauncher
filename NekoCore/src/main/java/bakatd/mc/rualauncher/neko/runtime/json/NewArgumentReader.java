package bakatd.mc.rualauncher.neko.runtime.json;

import bakatd.mc.rualauncher.neko.core.ArgumentUnit;
import bakatd.mc.rualauncher.neko.core.RuleUnit;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewArgumentReader {
    private JSONArray argumentsJsons;

    public NewArgumentReader(JSONArray argumentsJson) {
        this.argumentsJsons = argumentsJson;
    }

    public List<ArgumentUnit> read(){
        List<ArgumentUnit> argumentUnitList = new ArrayList<>();
        
        for(Object obj : argumentsJsons){
            if(obj instanceof String){
                if(obj.equals("-cp")){ //skip cp
                    break;
                }
                argumentUnitList.addAll(new OldArgumentReader((String) obj).read());
            } else if(obj instanceof JSONObject){
                JSONObject argJson = (JSONObject) obj;

                List<String> valueList = new ArrayList<>();
                List<RuleUnit> ruleUnitList = new ArrayList<>();

                //Load Value
                Object value = argJson.get("value");
                if(value.equals("-cp")){ //skip cp
                    break;
                }
                if(value instanceof String) {
                    valueList.add((String) value);
                }else if(value instanceof String[]) {
                    //I'm fucking forgot how to put String[]'s data into a List!!!
                    //Holy Shit!!!
                    for (String grassShit : (String[]) value) {
                        valueList.add(grassShit);
                    }
                }

                //Load Rules
                if(argJson.containsKey("rules")) {
                    for (Object objR : argJson.getJSONArray("rules")) {
                        JSONObject ruleJson = (JSONObject) objR;
                        ruleUnitList.add(new RuleReader(ruleJson).read());
                    }
                }

                //Get a argObj!!!
                argumentUnitList.add(new ArgumentUnit(
                        valueList,
                        ruleUnitList
                ));
            }
        }

        return argumentUnitList;
    }

    public JSONArray getArgumentsJson() {
        return argumentsJsons;
    }

    public void setArgumentsJson(JSONArray argumentsJson) {
        this.argumentsJsons = argumentsJson;
    }
}
