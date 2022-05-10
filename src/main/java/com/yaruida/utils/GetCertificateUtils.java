package com.yaruida.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class GetCertificateUtils {


    private static final Logger logger = Logger.getLogger(GetCertificateUtils.class);


    public static void main(String[] args){

        HttpServletResponse  httpResponse = null;
        try {
            String certificate =  getX509Certificate("www.luckywhb.com", "mycert.cer");
            System.out.println("certificate:" + certificate);

            InputStream in = new FileInputStream("test.cer");
            OutputStream outputStream = new BufferedOutputStream(httpResponse.getOutputStream());
            byte [] buff = new byte[]{};
            int n;
            while ((n=in.read(buff))!=-1){
                outputStream.write(buff, 0, n);
            }
            outputStream.flush();
            outputStream.close();
            in.close();
        } catch (Exception e){
            logger.error("获取证书异常" + e.getMessage());
        }

    }


    public static String getX509Certificate (String url, String fileName) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, KeyManagementException, IOException {
        File file = new File("jssecacerts");
        if (file.isFile() == false) {
            char SEP = File.separatorChar;
            File dir = new File(System.getProperty("java.home") + SEP
                    + "lib" + SEP + "security");

            file = new File(dir, "jssecacerts");
            if (file.isFile() == false) {
                file = new File(dir, "cacerts");
            }
        }
        InputStream in = new FileInputStream(file);
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        char[] passphrase =  getPassphrase(url);
        ks.load(in, passphrase);
        in.close();
        //TLS版本
        SSLContext context = SSLContext.getInstance("TLS");
        TrustManagerFactory tmf =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ks);
        X509TrustManager defaultTrustManager = (X509TrustManager)tmf.getTrustManagers()[0];
        SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
        context.init(null, new TrustManager[] {tm}, null);

        // 创建方式 SSLSocketFactory
        SSLSocketFactory factory = context.getSocketFactory();
        SSLSocket socket = (SSLSocket)factory.createSocket(url, 443);
        //使用指定的超时启用/禁  单位毫秒
        socket.setSoTimeout(10000);
        try {
            //创建连接
            socket.startHandshake();
            socket.close();
        } catch (SSLException e) {
            logger.error("Starting SSL handshake...  异常   ------------" + e.getMessage());
        }
        X509Certificate[] chain = tm.chain;

        String data = "";
        if (chain == null) {
            logger.error("无法获取服务器证书链");
            return data;
        }
        try {
            data = convertToPem(chain,fileName);
        } catch (Exception e) {
            logger.error("转化成pem文件:" + e.getMessage());
        }
        return  data;
    }

    /**
     * 转化成pem文件
     * @param chain
     * @throws Exception
     */
    private static String convertToPem(X509Certificate[] chain, String fileName) throws Exception {
        String cert_begin = "-----BEGIN CERTIFICATE-----\n";
        String end_cert = "-----END CERTIFICATE-----";

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(cert_begin);
        writer.newLine();
        if(chain.length == 0){
            logger.error("无法获取服务器证书链");
            return "";
        }
        X509Certificate cert = chain[0];
        String pemCert = DatatypeConverter.printBase64Binary(cert.getEncoded()).replaceAll("(.{64})", "$1\n");
        writer.write(pemCert);
        writer.write(end_cert);
        writer.newLine();
        writer.flush();
        writer.close();
        InputStream in = null;
        byte[] data = null;
        in = new FileInputStream(fileName);
        data = new byte[in.available()];
        in.read(data);
        in.close();
        return new String(Base64.encodeBase64(data));
    }


    /***
     * 在SSL握手之前，拿到服务端的证书
     */
    private static class SavingTrustManager implements X509TrustManager {

        private final X509TrustManager tm;
        private X509Certificate[]chain ;

        SavingTrustManager(X509TrustManager tm) {
            this.tm = tm;
        }

        public X509Certificate[] getAcceptedIssuers() {
            throw new UnsupportedOperationException();
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
            throw new UnsupportedOperationException();
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
            this.chain = chain;
            tm.checkServerTrusted(chain, authType);
        }
    }

    /***
     * 口令
     * @param url
     * @return
     */
    public static char[]  getPassphrase(String url){
        String[] urls = new String[]{url};
        if ((urls.length == 1) || (urls.length == 2)) {
            String[] c = urls[0].split(":");
            // 安装证书与查看证书默认密码是 changeit
            String p = (urls.length == 1) ? "changeit" : urls[1];
            return p.toCharArray();
        } else {
            System.out.println("访问https的时候缺少安全证书");
            return new char[]{};
        }
    }
}
