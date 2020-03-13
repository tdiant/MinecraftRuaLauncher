import bakatd.mc.rualauncher.neko.LauncherCore;
import bakatd.mc.rualauncher.neko.fuck.FFuck;
import com.alibaba.fastjson.JSONObject;

public class JavaTest {
    public static void main(String[] args) throws Exception {
        LauncherCore core = new LauncherCore(
                "G:\\MC\\.minecraft\\",
                "D:\\Java\\jdk1.8.0_201\\bin\\",
                "G:\\MC\\.minecraft\\libraries\\",
                "G:\\MC\\.minecraft\\versions\\1.15.2\\natives\\",
                "1.15.2",
                JSONObject.parseObject(FFuck.fuckFile("G:\\MC\\.minecraft\\versions\\1.15.2\\1.15.2.json"))
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
}
