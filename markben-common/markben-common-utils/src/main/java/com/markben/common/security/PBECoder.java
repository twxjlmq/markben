package com.markben.common.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;
import java.util.Random;

/**
 * PBE安全编码组件
 * @since 0.0.1
 */
public class PBECoder extends Coder {
	/**
     * 支持以下任意一种算法
     * 
     * <pre>
     * PBEWithMD5AndDES 
     * PBEWithMD5AndTripleDES 
     * PBEWithSHA1AndDESede
     * PBEWithSHA1AndRC2_40
     * </pre>
     */
    public static final String ALGORITHM = "PBEWITHMD5andDES";
  
    /**
     * 盐初始化
     * @return 返回字节数组
     */
    public static byte[] initSalt() {
        byte[] salt = new byte[8];
        Random random = new Random();
        random.nextBytes(salt);
        return salt;
    }
  
    /**
     * 转换密钥
     * @param password 密码
     * @return 返回key
     * @throws Exception 没有找到算法或key初始化失败时抛出该异常
     */
    private static Key toKey(String password) throws Exception {
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(keySpec);
        return secretKey;
    }
  
    /**
     * 加密
     * 
     * @param data 数据
     * @param password 密码
     * @param salt  盐
     * @return 返回字节数组
     * @throws Exception 没有找到算法或key初始化失败时抛出该异常
     */
    public static byte[] encrypt(byte[] data, String password, byte[] salt) throws Exception {
        Key key = toKey(password);
        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 100);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
        return cipher.doFinal(data);
    }
  
    /**
     * 解密
     * 
     * @param data  数据
     * @param password 密码
     * @param salt  盐
     * @return 返回字节数组
     * @throws Exception 没有找到算法或key初始化失败时抛出该异常
     */
    public static byte[] decrypt(byte[] data, String password, byte[] salt) throws Exception {
        Key key = toKey(password);
        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 100);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        return cipher.doFinal(data);
  
    }
}
