package com.toast.util.encrypt;

import java.util.Base64;

/**
 * @author 土司先生
 * @time 2023/3/21
 * @describe
 */
public class EncryptSalt {
    private EncryptSalt() {};

    public static String encrypt(String origin, String salt, int repeat) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] data = ("{" + salt + "}" + origin).getBytes();
        for(int x = 0; x < repeat; x ++) {
            data = encoder.encode(data);
        }
        return new String(data);
    }

    public static String decrypt(String mist, String salt, int repeat) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] data = mist.getBytes();
        for (int x = 0; x < repeat; x ++) {
            data = decoder.decode(data);
        }
        String str = new String(data);
        return str.substring((("{" + salt + "}")).length());
    }
}
