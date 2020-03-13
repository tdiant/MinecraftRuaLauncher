package bakatd.mc.rualauncher.neko.fuck;

import com.alibaba.fastjson.JSONObject;

import java.math.BigInteger;

public class PFuck {
    public static BigInteger fuckB(JSONObject j,String n, String s){
        return j.getJSONObject(n)
                .getBigInteger(s);
    }

    public static String fuckS(JSONObject j, String n, String s){
        return j.getJSONObject(n)
                .getString(s);
    }

    public static boolean fuckBL(JSONObject j,String n, String s){
        return j.getJSONObject(n)
                .getBoolean(s);
    }
}
