package lk.ijse.Crop_monitoring_system.util;

import java.util.Base64;

public class AppUtill {
    public static String ImageToBase64(byte [] profilePic){
        return Base64.getEncoder().encodeToString(profilePic);
    }
}
