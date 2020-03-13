package bakatd.mc.rualauncher.neko.fuck;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FFuck {
    public static String fuckFile(String fileURL) {
        BufferedReader reader = null;
        String returnBack = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(fileURL);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;

            while ((tempString = reader.readLine()) != null) {
                returnBack += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return returnBack;
    }
}
