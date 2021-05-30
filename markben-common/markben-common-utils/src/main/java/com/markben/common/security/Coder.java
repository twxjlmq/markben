package com.markben.common.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 算法定义
 * @author 乌草坡
 */
public abstract class Coder {
	public static final String KEY_SHA = "SHA";
    public static final String KEY_MD5 = "MD5";
 
    /**
     * MAC算法可选以下多种算法
     * 
     * <pre>
     * HmacMD5 
     * HmacSHA1 
     * HmacSHA256 
     * HmacSHA384 
     * HmacSHA512
     * </pre>
     */
    public static final String KEY_MAC = "HmacMD5";
 
    /**
     * BASE64解密
     * @param key 秘钥
     * @return 返回字节数组
     */
    public static byte[] decryptBASE64(String key) {
       // return new BASE64Decoder().decodeBuffer(key);
    	return Base64.decodeBase64(key);
    }
 
    /**
     * BASE64加密
     * @param key 秘钥
     * @return 返回加密后的值
     */
    public static String encryptBASE64(byte[] key) {
       // return new BASE64Encoder().encodeBuffer(key);
    	return Base64.encodeBase64String(key);
    }
 
    /**
     * MD5加密
     * @param data 需要加密的内容字节数组
     * @return 返回加密后的值
     * @throws NoSuchAlgorithmException 没有找到算法时抛出该异常
     */
    public static byte[] encryptMD5(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);
        return md5.digest();
 
    }
 
    /**
     * SHA加密
     * @param data 需要加密的内容字节数组
     * @return 返回加密后的值
     * @throws NoSuchAlgorithmException 没有找到算法时抛出该异常
     */
    public static byte[] encryptSHA(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data);
        return sha.digest();
 
    }
 
    /**
     * 初始化HMAC密钥
     * @return 返回初始化Key
     * @throws NoSuchAlgorithmException 没有找到算法时抛出该异常
     */
    public static String initMacKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);
        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }
 
    /**
     * HMAC加密
     * 
     * @param data 需要加密的内容字节数组
     * @param key 加密秘钥
     * @return 返回加密后的值
     * @throws Exception 没有找到算法或key初始化失败时抛出该异常
     */
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);
 
    }
}
