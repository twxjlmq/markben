package com.markben.common.security;

import com.markben.common.constant.MarkbenConstant;
import com.markben.common.utils.StringUtils;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * 加密解密算法工具类
 * @author 乌草坡
 * @since 0.0.1
 */
public class SecurityUtils {

	/**
	 * MD5加密
	 * @param value 加密内容
	 * @return 返回MD5加密后的值
	 */
	public static String md5(String value) {
		if(StringUtils.isNotEmpty(value)) {
			byte[] bytes;
			try {
				bytes = value.getBytes(StandardCharsets.UTF_8);
				bytes = Coder.encryptMD5(bytes);
				StringBuffer md5StrBuff = new StringBuffer();
				for (int i = 0; i < bytes.length; i++) {
					if (Integer.toHexString(0xFF & bytes[i]).length() == 1)
						md5StrBuff.append("0").append(Integer.toHexString(0xFF & bytes[i]));
					else
						md5StrBuff.append(Integer.toHexString(0xFF & bytes[i]));
				}
				value = md5StrBuff.toString();
			} catch (Exception e) {
				value = null;
				e.printStackTrace();
			}
		}
		return value;
	}
	
	/**
	 * SHA加密
	 * @param value 加密内容
	 * @return 返回加密后的值
	 */
	public static String sha(String value) {
		if(StringUtils.isNotEmpty(value)) {
			byte[] bytes;
			try {
				bytes = value.getBytes(StandardCharsets.UTF_8);
				bytes = Coder.encryptSHA(bytes);
				value = byteArrayToHex(bytes);
			} catch (Exception e) {
				value = null;
				e.printStackTrace();
			}
		}
		return value;
	}
	
	/**
	 * 字符数组转化为十六进制
	 * @param byteArray 字节数组
	 * @return 返回十六进制字符串
	 */
	private static String byteArrayToHex(byte[] byteArray) {
		char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };
		char[] resultCharArray =new char[byteArray.length * 2];
		int index = 0;
		for(byte b :byteArray){
			resultCharArray[index++] = hexDigits[b>>>4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}
		return new String(resultCharArray);
	}
	
	/**
	 * DES加密
	 * @param value 需要加密的字符串
	 * @param key 密钥
	 * @return 返回加密后的值
	 */
	public static String desEncode(String value, String key) {
		if(StringUtils.isNotEmpty(value) && StringUtils.isNotEmpty(key)) {
			try {
				byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
				bytes = DESCoder.encrypt(bytes,key);
				value = Base64.encodeBase64URLSafeString(bytes);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
	
	/**
	 * DES解密
	 * @param value 需要解密的字符串
	 * @param key 密钥
	 * @return 返回解密后的值
	 */
	public static String desDecode(String value,String key) {
		try {
			byte[] bytes = Base64.decodeBase64(value);
			bytes = DESCoder.decrypt(bytes,key);
			value = new String(bytes, StandardCharsets.UTF_8);
		} catch (UnsupportedEncodingException e) {
			value = null;
			e.printStackTrace();
		} catch (Exception e) {
			value = null;
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 对密码加密
	 * @param password 密码
	 * @return 返回加密处理后的密码
	 */
	public static String encryptPassword(String password) {
		if(StringUtils.isEmpty(password)) {
			return null;
		}
		String saltPassword =  MarkbenConstant.MD5_SALT + password;
		return SecurityUtils.md5(saltPassword);
	}
}
