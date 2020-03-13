package bakatd.mc.rualauncher.neko.runtime.launch;

import bakatd.mc.rualauncher.neko.core.RuleUnit;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class RuleChecker {
    private RuleUnit ruleUnit;

    public RuleChecker(RuleUnit ruleUnit) {
        this.ruleUnit = ruleUnit;
    }

    public boolean check(){
        if(isNeedCheck(ruleUnit.getOsName()) && (!isSameOSName(System.getProperty("os.name"),ruleUnit.getOsName())))
            return !getActionAttitude(ruleUnit.getAction());
        if(isNeedCheck(ruleUnit.getOsVersion()) && (!Pattern.matches(ruleUnit.getOsVersion(),System.getProperty("os.version"))))
            return !getActionAttitude(ruleUnit.getAction());
        if(isNeedCheck(ruleUnit.getOsArch()) && (!isSameArch(System.getProperty("os.arch"),ruleUnit.getOsArch())))
            return !getActionAttitude(ruleUnit.getAction());
        return getActionAttitude(ruleUnit.getAction());
    }

    public boolean check(Map<String, Boolean> featuresMap){
        if(!check())
            return !getActionAttitude(ruleUnit.getAction());

        for(String checkKey:ruleUnit.getFeaturesMap().keySet()){
            /*if (featuresMap.get(checkKey)){
                if(!ruleUnit.getFeaturesMap().containsKey(checkKey))
                    return !getActionAttitude(ruleUnit.getAction());
                if(ruleUnit.getFeaturesMap().get(checkKey) == featuresMap.get(checkKey))
                    return getActionAttitude(ruleUnit.getAction());
            } else {
                if(!ruleUnit.getFeaturesMap().containsKey(checkKey))
                    return getActionAttitude(ruleUnit.getAction());  //If doesn't contains, it will be false I guess
                if(ruleUnit.getFeaturesMap().get(checkKey) != featuresMap.get(checkKey))
                    return !getActionAttitude(ruleUnit.getAction());
            }*/

            boolean myself = featuresMap.containsKey("checkKey")?featuresMap.get(checkKey):false;
            boolean theyNeed = ruleUnit.getFeaturesMap().containsKey(checkKey)?ruleUnit.getFeaturesMap().get(checkKey):false;

            if(myself != theyNeed)
                return !getActionAttitude(ruleUnit.getAction());
        }

        return getActionAttitude(ruleUnit.getAction());
    }

    public RuleUnit getRuleUnit() {
        return ruleUnit;
    }

    public void setRuleUnit(RuleUnit ruleUnit) {
        this.ruleUnit = ruleUnit;
    }

    public static boolean isSameOSName(String beChecked, String needed){
        if(needed.toLowerCase().equals("any")) return true;
        if(needed.toLowerCase().equals(beChecked.toLowerCase())) return true;
        if(isMacOS(beChecked) && isMacOS(needed)) return true;

        return false;
    }

    public static boolean isMacOS(String key){
        if( (key.toLowerCase().contains("os") && key.toLowerCase().contains("x")) )
            return true;
        if( key.toLowerCase().contains("mac"))
            return true;
        return false;
    }

    private static boolean isNeedCheck(String str){
        if(str == null || str.trim().equals(""))
            return false;
        return true;
    }

    private static boolean isNeedCheck(List l){
        if(l==null || l.size() == 0)
            return false;
        return true;
    }

    public static boolean getActionAttitude(String str){
        if(str.equals("allow"))
            return true;
        if(str.equals("disallow"))
            return false;
        return true;
    }

    public static boolean isSameArch(String beChecked, String needed){
        if(needed.contains("64") && beChecked.contains("64"))
            return true;
        if(needed.contains("86") && beChecked.contains("86"))
            return true;
        if(needed.contains("32") && beChecked.contains("32"))
            return true;
        return false;
    }
}
