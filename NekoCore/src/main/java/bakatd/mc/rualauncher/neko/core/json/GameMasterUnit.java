package bakatd.mc.rualauncher.neko.core.json;

import java.util.ArrayList;
import java.util.List;

public class GameMasterUnit {
    private String id;
    private boolean root;
    private boolean hidden;
    private List<PatchUnit> patchUnitList = new ArrayList<>();

    public GameMasterUnit(String id, boolean root, boolean hidden) {
        this.id = id;
        this.root = root;
        this.hidden = hidden;
    }

    public void pushPatch(PatchUnit patchUnit){
        if(!patchUnitList.contains(patchUnit))
            patchUnitList.add(patchUnit);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public List<PatchUnit> getPatchUnitList() {
        return patchUnitList;
    }

    public void setPatchUnitList(List<PatchUnit> patchUnitList) {
        this.patchUnitList = patchUnitList;
    }
}
