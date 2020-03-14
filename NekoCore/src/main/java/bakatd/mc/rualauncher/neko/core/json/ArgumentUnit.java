package bakatd.mc.rualauncher.neko.core.json;

import java.util.ArrayList;
import java.util.List;

public class ArgumentUnit {
    private List<String> value = new ArrayList<>();
    private List<RuleUnit> ruleUnit = new ArrayList<>();

    public ArgumentUnit(String value){
        this.value.add(value);
    }

    public ArgumentUnit(List<String> value, List<RuleUnit> ruleUnit) {
        this.value = value;
        this.ruleUnit = ruleUnit;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

    public List<RuleUnit> getRuleUnit() {
        return ruleUnit;
    }

    public void setRuleUnit(List<RuleUnit> ruleUnit) {
        this.ruleUnit = ruleUnit;
    }
}
