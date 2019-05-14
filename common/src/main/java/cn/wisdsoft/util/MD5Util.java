package cn.wisdsoft.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-04 10:31
 * @ Description：
 */
public class MD5Util {
    /**
     * 加密密码
     * @param target 要进行加密的密码
     * @return 加密后的密码
     */
    public static String MD5Encryption(String target) {
        Md5Hash md5Hash;
        //加密：MD5 + 盐 + 散列次数
        md5Hash = new Md5Hash(target,"this",3);
        //cd757bae8bd31da92c6b14c235668091
        return md5Hash.toString();
    }

    /**
     * 判断密码是否正确
     * @param plaintext 明文
     * @param ciphertext 密文
     * @return 正确：true  错误：false
     */
    public static boolean MD5Validate(String plaintext,String ciphertext){
        Md5Hash md5Hash;
        //加密：MD5 + 盐 + 散列次数
        md5Hash = new Md5Hash(plaintext,"this",3);
        if(md5Hash.toString().equals(ciphertext)){
            return true;
        } else {
            return false;
        }
    }
}
