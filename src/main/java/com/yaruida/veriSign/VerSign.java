package com.yaruida.veriSign;

import org.springframework.util.Base64Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Enumeration;

/**
 * @desc:
 * @author: admin
 * @since: 2022/3/4 16:09
 * @history:
 */
public class VerSign {

    public static String MCT_CODE = "201209100018";
    public static String PFX_FILE_PATH = "config_be/conf_yp/test_private_test.pfx";
    public static String CER_FILE_PATH = "config_be/conf_yp/test_public_test.cer";
    public static String CER_PWD = "111111";
    private static final String CHARSET_NAME = "UTF-8";


//    private static String sign(String grp) {
//        try {
//            byte[] sign = getSignInfo(PFX_FILE_PATH, CER_PWD,
//                    grp.getBytes(CHARSET_NAME));
//            return Base64Utils.encrypt(sign, CHARSET_NAME);
//        } catch (Exception e) {
//            log.error("添加签名失败", e);
//            MailHelper.sendEmail("yeepay sign 添加签名失败", e);
//            return "";
//        }
//    }

//    public static byte[] getSignInfo(String privatePath, String password, byte[] data) {
//        try {
//            PrivateKey pkey = getPrivate(privatePath, password);
//            Signature sign = Signature.getInstance("SHA1withRSA");
//            sign.initSign(pkey);
//            sign.update(data);
//            return sign.sign();
//        } catch (InvalidKeyException var5) {
//            throw new FundInfException("InvalidKeyException: ", var5);
//        } catch (NoSuchAlgorithmException var6) {
//            throw new FundInfException("NoSuchAlgorithmException: ", var6);
//        } catch (SignatureException var7) {
//            throw new FundInfException("SignatureException: ", var7);
//        } catch (Exception var8) {
//            throw new FundInfException("Exception: ", var8);
//        }
//    }
//
//    private static PrivateKey getPrivate(String keyStorePath, String password) {
//        try {
//            KeyStore ks = KeyStore.getInstance("PKCS12");
//            FileInputStream is = new FileInputStream(keyStorePath);
//            ks.load(is, password.toCharArray());
//            is.close();
//            Enumeration enum1 = ks.aliases();
//            String alias = (String)enum1.nextElement();
//            if (!ks.isKeyEntry(alias)) {
//                throw new Exception("No key is found in pfx file");
//            } else {
//                return (PrivateKey)ks.getKey(alias, password.toCharArray());
//            }
//        } catch (UnrecoverableKeyException var6) {
//            throw new FundInfException("UnrecoverableKeyException: ", var6);
//        } catch (KeyStoreException var7) {
//            throw new FundInfException("KeyStoreException: ", var7);
//        } catch (FileNotFoundException var8) {
//            throw new FundInfException("FileNotFoundException: ", var8);
//        } catch (NoSuchAlgorithmException var9) {
//            throw new FundInfException("NoSuchAlgorithmException: ", var9);
//        } catch (CertificateException var10) {
//            throw new FundInfException("CertificateException: ", var10);
//        } catch (IOException var11) {
//            throw new FundInfException("IOException: ", var11);
//        } catch (Exception var12) {
//            throw new FundInfException("Exception: ", var12);
//        }
//    }



}
