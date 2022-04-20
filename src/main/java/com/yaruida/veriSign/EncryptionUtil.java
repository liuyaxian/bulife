package com.yaruida.veriSign;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtil {
    private static Logger logger = Logger.getLogger(EncryptionUtil.class);

    public static String md5(String str) {
        return md5(str, "UTF-8");
    }

    public static String md5(String str, String encoding) {
        try {
            return DigestUtils.md5Hex(str.getBytes(encoding));
        } catch(UnsupportedEncodingException e) {
            logger.error("md5 string encoding unsupport.", e);
        }
        return null;
    }

    public static String encodeBase64String(byte[] binaryData) {
        return Base64Helper.encode(binaryData);
    }

    public static byte[] decodeBase64(String base64String) {
        try {
            return Base64Helper.decode(base64String);
        } catch(UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage());
        }
        return null;
    }

    public static byte[] generateAESKey(int keySize) {
        byte[] key = null;
        KeyGenerator kgen = null;
        if (keySize != 128)
            keySize = 128;
        try {
            kgen = KeyGenerator.getInstance("AES");
            kgen.init(keySize);
            SecretKey skey = kgen.generateKey();
            key = skey.getEncoded();
        } catch(NoSuchAlgorithmException e) {
            throw new RuntimeException("生成密钥失败", e);
        }
        return key;
    }

    // cipher : miwen decrypt:解密
    public static byte[] decryptAES(byte[] privateKey, byte[] ciphertext) {
        return _aes(privateKey, ciphertext, Cipher.DECRYPT_MODE);
    }

    public static byte[] encryptAES(byte[] privateKey, byte[] plaintext) {
        return _aes(privateKey, plaintext, Cipher.ENCRYPT_MODE);
    }

    public static byte[] _aes(byte[] privateKey, byte[] text, int mode) {
        byte[] result = null;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(privateKey, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(mode, secretKeySpec);
            result = cipher.doFinal(text);
        } catch(Exception e) {
            logger.debug("AES " + (mode == Cipher.ENCRYPT_MODE ? "encrypt" : "decrypt") + " failure!", e);
        }
        return result;
    }

    public static String decryptStrss(String ciphertext, String key) throws UnsupportedEncodingException {
        //如果得到的数据有空格，说明由于服务器解码将+号转成了空格。可做以下处理
        byte[] ciphertexts = Base64Helper.decode(ciphertext.replace(" ", "+"));
        byte[] plaintext = _aes(EncryptionUtil.decodeBase64(key), ciphertexts, Cipher.DECRYPT_MODE);
        return new String(plaintext, "utf-8");
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(decryptStrss("KoeydZmoEQA9pP4K8RGnJytcdQiibDnqcDsZiJJs1oy0cqJwvc3YYaq8qjjZQPzIiUMIwGzFfESbhvX1hoTwRiCEg OdKUIbr/36L7eGSwZDUCNG2VvBDjZMAo6f2QYWyQVq6qRXGlswkY4Nmhl7lw==", "YWFhYWFhYWFhYWFhYWFhYQ=="));

        String originStr = "{\"mctcustno\":\"50000011423\",\"mobileno\":\"17666100076\",\"token\":\"0051bf2ab8bd34939df522dc5dd33af19\"}";//待加密的明文
        String signPwd = "YWFhYWFhYWFhYWFhYWFhYQ==";//加密密钥
        byte[] key = EncryptionUtil.decodeBase64(signPwd);//将加密密钥进行base64解码
        byte[] ciphertext = _aes(key, originStr.getBytes("utf-8"), Cipher.ENCRYPT_MODE);//使用AES算法加密
        System.out.println("加密前的明文： " + originStr);
        System.out.println("加密后的密文： " + Base64Helper.encode(ciphertext));//将加密字节数组base64编码
        byte[] plaintext = _aes(key, ciphertext, Cipher.DECRYPT_MODE);
        System.out.println(new String(plaintext, "utf-8"));
    }



    private static final char[] HEX_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    @SuppressWarnings("unused")
    private static char[] encodeHex(byte[] bytes) {
        char chars[] = new char[32];
        for (int i = 0; i < chars.length; i = i + 2) {
            byte b = bytes[i / 2];
            chars[i] = HEX_CHARS[(b >>> 0x4) & 0xf];
            chars[i + 1] = HEX_CHARS[b & 0xf];
        }
        return chars;
    }
}
