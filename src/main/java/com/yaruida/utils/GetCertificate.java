package com.yaruida.utils;


import com.itextpdf.kernel.xmp.impl.Base64;

import javax.net.ssl.*;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/***
 * 根据域名获取对应的证书
 */
public class GetCertificate {


    private static final String BEGIN_CERT = "-----BEGIN CERTIFICATE-----";
    private static final String END_CERT = "-----END CERTIFICATE-----";

    public static void main(String[] args) throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

        getX509Certificate("www.zlfund.com");

    }


    private static void getX509Certificate (String url) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, KeyManagementException, IOException {
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
            System.out.println("Starting SSL handshake...  异常   ------------" + e.getMessage());
        }
        X509Certificate[] chain = tm.chain;
        if (chain == null) {
            System.out.println("无法获取服务器证书链");
            return;
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("mycert.cer"));
        writer.write(BEGIN_CERT);
        writer.newLine();
        for (int i = 0; i < chain.length; i++) {
            X509Certificate cert = chain[i];
            writer.newLine();
            writer.write(convertToPem(cert));
        }
        writer.newLine();
        writer.write(END_CERT);
        writer.flush();
        writer.close();
    }


    public final static String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * 转化成pem文件，并按照64位分隔
     * @param cert
     * @return
     * @throws CertificateEncodingException
     */
    protected static String convertToPem(X509Certificate cert) throws CertificateEncodingException {
        Base64 encoder = new Base64();
        String cert_begin = "-----BEGIN CERTIFICATE-----\n";
        String end_cert = "-----END CERTIFICATE-----";
        byte[] derCert = cert.getEncoded();
        String pemCertPre = new String(encoder.encode(derCert));
        String pemCert = cert_begin + pemCertPre + end_cert;
        return DatatypeConverter.printBase64Binary(cert.getEncoded()).replaceAll("(.{64})", "$1\n");
    }


    /***
     * 在SSL握手之前，拿到服务端的证书
     */
    private static class SavingTrustManager implements X509TrustManager {

        private final X509TrustManager tm;
        private X509Certificate[] chain;

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
        String host = "";
        int port = 443;
        String[] urls = new String[]{url};
        if ((urls.length == 1) || (urls.length == 2)) {
            String[] c = urls[0].split(":");
            host = c[0];
            port = (c.length == 1) ? 443 : Integer.parseInt(c[1]);
            // 安装证书与查看证书默认密码是 changeit
            String p = (urls.length == 1) ? "changeit" : urls[1];
            return p.toCharArray();
        } else {
            System.out.println("访问https的时候缺少安全证书");
            return new char[]{};
        }
    }

}
