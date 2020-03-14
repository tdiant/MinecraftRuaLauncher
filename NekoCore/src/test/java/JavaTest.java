import bakatd.mc.rualauncher.neko.LauncherCore;
import bakatd.mc.rualauncher.neko.fuck.FFuck;
import bakatd.mc.rualauncher.neko.manager.VersionManager;
import com.alibaba.fastjson.JSONObject;

import java.io.File;

public class JavaTest {
    public static void main(String[] args) throws Exception {
        VersionManager.init("G:\\MC\\.minecraft\\");
        System.out.println(VersionManager.refreshVersions());

        LauncherCore core = new LauncherCore(
                VersionManager.getGameVersionByName("1.15.2"),
                "D:\\Java\\jdk1.8.0_201\\bin\\"
        );
        core.setLauncherBrand("RuaLauncher");
        core.setLauncherVersion("0.0.1_beta");

        core
                .pushReplaceArg("${auth_player_name}","bakatd")
                .pushReplaceArg("${version_name}","1.15.2")
                .pushReplaceArg("${game_directory}","G:\\MC\\.minecraft")
                .pushReplaceArg("${assets_root}","G:\\MC\\.minecraft\\assets")
                .pushReplaceArg("${assets_index_name}","1.15")
                .pushReplaceArg("${auth_uuid}","7574d0d00dd23d638c1119978a9a2ad6")
                .pushReplaceArg("${auth_access_token}","0")
                .pushReplaceArg("${user_type}","mojang")
                .pushReplaceArg("${version_type}","1.15.2");


        core.startGame();
    }

    public static void main1(String[] args){
        for(File f:new File("G:\\MC\\.minecraft\\versions\\").listFiles())
            System.out.println(f.getName());
    }
}
