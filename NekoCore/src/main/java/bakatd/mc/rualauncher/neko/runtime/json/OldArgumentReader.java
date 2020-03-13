package bakatd.mc.rualauncher.neko.runtime.json;

import bakatd.mc.rualauncher.neko.core.ArgumentUnit;

import java.util.ArrayList;
import java.util.List;

public class OldArgumentReader {
    private String arguments;

    public OldArgumentReader(String arguments) {
        this.arguments = arguments;
    }

    public List<ArgumentUnit> read(){
        List<ArgumentUnit> argumentUnitList = new ArrayList<>();

        for(String str : arguments.split(" "))
            argumentUnitList.add(new ArgumentUnit(str));

        return argumentUnitList;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }
}
