package bakatd.mc.rualauncher.launcher.console;

import bakatd.mc.rualauncher.neko.LauncherCore;
import bakatd.mc.rualauncher.neko.core.version.GameVersion;
import bakatd.mc.rualauncher.neko.manager.VersionManager;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("====== Minecraft Rua Launcher ======");
        System.out.println("CONSOLE LAUNCHER VERSION. 1.0.0");

        Scanner scan = new Scanner(System.in);

        log("TIP", "Launcher will use the same Java which Launcher is using to launch games.");
        String javaPath = System.getProperty("java.home") + "\\bin\\";

        br();

        //Output Minecraft Versions
        String homeDir = new File("").getAbsolutePath()+"\\.minecraft\\";
        VersionManager.init(homeDir);
        int i=0;
        for(GameVersion gameVersion : VersionManager.getGameVersionList())
            log("VERSION",(i++) + " :: "+gameVersion.getGameName());
        log("TIP","Choose a version to launch. Input the number of the game:");

        int num = scan.nextInt();

        GameVersion gameVersion = VersionManager.getGameVersionList().get(num);
        LauncherCore core = new LauncherCore(
                gameVersion,
                javaPath
        );

        core.setLauncherBrand("RuaLauncher");
        core.setLauncherVersion("0.0.1_beta");

        log("SETTING","Please input your user data:");
        log("SETTING","Player Name:");
        String playerName = scan.next();

        core
                .pushReplaceArg("${auth_player_name}",playerName)
                .pushReplaceArg("${version_name}",gameVersion.getGameName())
                .pushReplaceArg("${game_directory}",homeDir)
                .pushReplaceArg("${assets_root}",homeDir + "assets")
                .pushReplaceArg("${assets_index_name}",core.getGameMasterUnit().getPatchUnitList().get(0).getAssets())
                .pushReplaceArg("${auth_uuid}", UUID.randomUUID().toString())
                .pushReplaceArg("${auth_access_token}","0")
                .pushReplaceArg("${user_type}","mojang")
                .pushReplaceArg("${version_type}",gameVersion.getGameName())
                .pushReplaceArg("${game_assets}",homeDir + "assets");

        log("TIP", "Launching "+gameVersion.getGameName()+", please wait...");

        core.startGame();
    }

    public static void log(String type, String str){
        System.out.println("["+type+"] "+str);
    }

    public static void br(){
        System.out.println(" ");
    }
}
