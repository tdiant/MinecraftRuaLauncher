package bakatd.mc.rualauncher.neko.runtime.depend;

import bakatd.mc.rualauncher.neko.LauncherCore;
import bakatd.mc.rualauncher.neko.core.json.LibraryUnit;
import bakatd.mc.rualauncher.neko.core.json.PatchDownloadUnit;
import bakatd.mc.rualauncher.neko.core.json.PatchUnit;
import bakatd.mc.rualauncher.neko.core.json.RuleUnit;
import bakatd.mc.rualauncher.neko.manager.DownloadManager;
import bakatd.mc.rualauncher.neko.manager.VersionManager;
import bakatd.mc.rualauncher.neko.runtime.launch.RuleChecker;

import java.io.File;
import java.math.BigInteger;

public class DependChecker {
    private LauncherCore launcherCore;

    public DependChecker(LauncherCore launcherCore) {
        this.launcherCore = launcherCore;
    }

    public void check() throws Exception {
        boolean isNativeNeedCreate = false;
        File nativeDir = new File(launcherCore.getGameVersion().getUrlOfNative()+"\\");
        if(!nativeDir.exists()) {
            isNativeNeedCreate = true;
            nativeDir.mkdirs();
            nativeDir.mkdir();
        }

        for (PatchUnit patchUnit : launcherCore.getGameMasterUnit().getPatchUnitList()) {
            //Check Libraries
            for(LibraryUnit libraryUnit : patchUnit.getLibraryUnitList()) {
                //Check Rule
                boolean isRun = true;
                for (RuleUnit ruleUnit : libraryUnit.getRuleUnitList()) {
                    if (!new RuleChecker(ruleUnit).check(launcherCore.getFeatureMap()))
                        isRun = false;
                }
                if (!isRun) continue;

                //Download Analyse
                for (PatchDownloadUnit patchDownloadUnit : libraryUnit.getDownloadOfArtifactList()) {
                    try {
                        File f = new File(VersionManager.getUrlOfLibraries() + libraryUnit.getPath());
                        if (f.exists() && new BigInteger(f.getTotalSpace() + "").compareTo(patchDownloadUnit.getSize()) >= 0)
                            continue;
                        DownloadManager.addTask(patchDownloadUnit.getUrl(), VersionManager.getUrlOfLibraries() + libraryUnit.getDirPath(), libraryUnit.getFileName())
                                .download();
                    } catch (Exception e) {
                    }
                }

                for (PatchDownloadUnit patchDownloadUnit : libraryUnit.getDownloadOfClassifiersList()) {
                    if (!RuleChecker.isSameOSName(System.getProperty("os.name"), patchDownloadUnit.getName().toLowerCase().replace("native-", "").replace("natives-", "")))
                        continue;
                    try {
                        String[] args = libraryUnit.getName().split(":");

                        File f = new File(VersionManager.getUrlOfLibraries() + libraryUnit.getDirPath() + File.separator + args[1] + "-" + args[2] + "-" + patchDownloadUnit.getName() + ".jar");
                        if (f.exists() && new BigInteger(f.getTotalSpace() + "").compareTo(patchDownloadUnit.getSize()) >= 0)
                            continue;
                        DownloadManager.addTask(patchDownloadUnit.getUrl(), VersionManager.getUrlOfLibraries() + libraryUnit.getDirPath() + File.separator, args[1] + "-" + args[2] + "-" + patchDownloadUnit.getName() + ".jar")
                                .download();
                    } catch (Exception e) {}
                }

                if (isNativeNeedCreate) {
                    //
                }
            }

            //Check
        }
    }

    public LauncherCore getLauncherCore() {
        return launcherCore;
    }
}
