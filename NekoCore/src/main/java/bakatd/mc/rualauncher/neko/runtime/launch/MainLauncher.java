package bakatd.mc.rualauncher.neko.runtime.launch;

import bakatd.mc.rualauncher.neko.LauncherCore;
import bakatd.mc.rualauncher.neko.core.json.*;
import bakatd.mc.rualauncher.neko.manager.VersionManager;

public class MainLauncher {
    private GameMasterUnit gameMasterUnit;
    private LauncherCore launcherCore;

    public MainLauncher(GameMasterUnit gameMasterUnit, LauncherCore launcherCore) {
        this.gameMasterUnit = gameMasterUnit;
        this.launcherCore = launcherCore;
    }

    public String launcher(){
        String front="";
        String cp="";
        String mainClass="";
        String tail="";

        int lastPriority = -1;

        for(PatchUnit patchUnit : gameMasterUnit.getPatchUnitList()){
            //Load Front
            for(ArgumentUnit argumentUnit : patchUnit.getArgumentOfJVMList()){
                //Check Rule
                boolean isRun = true;
                for(RuleUnit ruleUnit : argumentUnit.getRuleUnit()){
                    if(!new RuleChecker(ruleUnit).check(launcherCore.getFeatureMap()))
                        isRun = false;
                    //isRun = new RuleChecker(ruleUnit).check(launcherCore.getFeatureMap());
                }

                if(!isRun) continue;

                //Analyse value
                for(String value : argumentUnit.getValue()){
                    front += launcherCore.replaceToRuaString(value)+" ";
                }
            }

            //Add Launcher Brand
            /*front += "-Dminecraft.launcher.brand=" + launcherCore.getLauncherBrand() + " ";
            front += "-Dminecraft.launcher.version=" + launcherCore.getLauncherVersion();*/

            //Load CP
            for(LibraryUnit libraryUnit : patchUnit.getLibraryUnitList()) {
                boolean isRun = true;
                for (RuleUnit ruleUnit : libraryUnit.getRuleUnitList()) {
                    if (!new RuleChecker(ruleUnit).check(launcherCore.getFeatureMap()))
                        isRun = false;
                    //isRun = new RuleChecker(ruleUnit).check(launcherCore.getFeatureMap());
                }

                if(!isRun) continue;

                String cpUnit = VersionManager.getUrlOfLibraries() + libraryUnit.getPath();
                cp += cpUnit+";";
            }
            cp += VersionManager.getHomeDir() + "versions\\" + launcherCore.getGameVersion().getGameName()+"\\"+launcherCore.getGameVersion().getGameName()+".jar;";
            cp = cp.substring(0, cp.length() - 1);

            //Set MainClass
            if(patchUnit.getPriority()>lastPriority) {
                lastPriority = patchUnit.getPriority();
                mainClass = patchUnit.getMainClass();
            }

            //Load Tails
            for(ArgumentUnit argumentUnit : patchUnit.getArgumentOfGameList()){
                //Check Rule
                boolean isRun = true;
                for(RuleUnit ruleUnit : argumentUnit.getRuleUnit()){
                    if(!new RuleChecker(ruleUnit).check(launcherCore.getFeatureMap()))
                        isRun = false;
                    //isRun = new RuleChecker(ruleUnit).check(launcherCore.getFeatureMap());
                }

                if(!isRun) continue;

                //Analyse value
                for(String value : argumentUnit.getValue()){
                    tail += launcherCore.replaceToRuaString(value)+" ";
                }
            }
        }

        return launcherCore.getJavaBinDir() + "java.exe " + front + " -cp " + cp + " " + mainClass + " " + tail;


    }

    public GameMasterUnit getGameMasterUnit() {
        return gameMasterUnit;
    }

    public void setGameMasterUnit(GameMasterUnit gameMasterUnit) {
        this.gameMasterUnit = gameMasterUnit;
    }

    public LauncherCore getLauncherCore() {
        return launcherCore;
    }

    public void setLauncherCore(LauncherCore launcherCore) {
        this.launcherCore = launcherCore;
    }


}
