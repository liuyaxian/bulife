package com.yaruida.utils;

import com.itextpdf.kernel.xmp.impl.Base64;

import javax.net.ssl.*;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;


public class InstallCert {

    private static final String BEGIN_CERT = "-----BEGIN CERTIFICATE-----";
    private static final String END_CERT = "-----END CERTIFICATE-----";

    public static void main(String[] args) throws Exception {
        String host = "163.177.151.110";
        int port = 443;
         args = new String[] {"www.zlfund.cn:443"};
        char[] passphrase;
        if ((args.length == 1) || (args.length == 2)) {
            String[] c = args[0].split(":");
            host = c[0];
            port = (c.length == 1) ? 443 : Integer.parseInt(c[1]);
            String p = (args.length == 1) ? "changeit" : args[1];
            passphrase = p.toCharArray();
        } else {
            System.out.println("Usage: java InstallCert <host>[:port] [passphrase]");
            return;
        }

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
        System.out.println("Loading KeyStore " + file + "...");
        InputStream in = new FileInputStream(file);
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(in, passphrase);
        in.close();
        //TLS版本
        SSLContext context = SSLContext.getInstance("TLSv1.2");
        TrustManagerFactory tmf =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ks);
        X509TrustManager defaultTrustManager = (X509TrustManager)tmf.getTrustManagers()[0];
        SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
        context.init(null, new TrustManager[] {tm}, null);
        SSLSocketFactory factory = context.getSocketFactory();

        System.out.println("Opening connection to " + host + ":" + port + "...");
        SSLSocket socket = (SSLSocket)factory.createSocket(host, port);
        socket.setSoTimeout(10000);
        try {
            System.out.println("Starting SSL handshake...");
            socket.startHandshake();
            socket.close();
            System.out.println();
            System.out.println("No errors, certificate is already trusted");
        } catch (SSLException e) {
            System.out.println("Starting SSL handshake...  异常   ------------");
            e.printStackTrace(System.out);
        }

        X509Certificate[] chain = tm.chain;
        if (chain == null) {
            System.out.println("Could not obtain server certificate chain");
            return;
        }

//        BufferedReader reader =
//                new BufferedReader(new InputStreamReader(System.in));

//        System.out.println();
//        System.out.println("Server sent " + chain.length + " certificate(s):");
//        System.out.println();
//        MessageDigest sha1 = MessageDigest.getInstance("SHA1");
//        MessageDigest md5 = MessageDigest.getInstance("MD5");

        String certContent="";
        BufferedWriter writer = new BufferedWriter(new FileWriter("mycert.cer"));
//        FileOutputStream fileOutputStream = new FileOutputStream("mycert.cer");

        writer.write(BEGIN_CERT);
        writer.newLine();
        for (int i = 0; i < chain.length; i++) {
            X509Certificate cert = chain[i];

            System.out.println("getVersion:"+ cert.getVersion());
            cert.checkValidity(new Date());
            System.out.println("getSerialNumber"+ cert.getSerialNumber());
            System.out.println("从证书中返回颁发者（颁发者专有名称:"+ cert.getIssuerX500Principal());
            System.out.println("getTBSCertificate: "+ String.valueOf(cert.getTBSCertificate()));

            System.out.println("getSignature: "+ String.valueOf(cert.getSignature()));
            System.out.println("getSigAlgName: "+ String.valueOf(cert.getSigAlgName()));


//            System.out.println
//                    (" " + (i + 1) + " Subject " + cert.getSubjectDN());
//            System.out.println("   Issuer  " + cert.getIssuerDN());
//            sha1.update(cert.getEncoded());
//            System.out.println("   sha1    " + toHexString(sha1.digest()));
//            md5.update(cert.getEncoded());
//            System.out.println("   md5     " + toHexString(md5.digest()));
//            System.out.println();
//            System.out.println("cert.getPublicKey():" + cert.getPublicKey());

            byte[] encodedCert = cert.getEncoded();
            String encodedStringCert = new String(Base64.encode(new String(encodedCert).getBytes(StandardCharsets.UTF_8)));
            certContent +=  convertToPem(cert);
//            certContent += Base64.encode(cert.getPublicKey().getEncoded());
            System.out.println("cert.getPublicKey():" + encodedStringCert);
            System.out.println("convertToPem: "+  convertToPem(cert));
            writer.newLine();
            writer.write(DatatypeConverter.printBase64Binary(cert.getEncoded()).replaceAll("(.{64})", "$1\n"));
        }
//        writer.write(certContent);
        writer.newLine();
        writer.write(END_CERT);
        writer.close();



//
//        System.out.println("Enter certificate to add to trusted keystore or 'q' to quit: [1]");
//        String line = reader.readLine().trim();
//        int k;
//        try {
//            k = (line.length() == 0) ? 0 : Integer.parseInt(line) - 1;
//        } catch (NumberFormatException e) {
//            System.out.println("KeyStore not changed");
//            return;
//        }
//
//        X509Certificate cert = chain[k];
//
//        String alias = host + "-" + (k + 1);
//        ks.setCertificateEntry(alias, cert);
//
//        OutputStream out = new FileOutputStream("baidubai.cer");
//        ks.store(out, passphrase);
//        out.close();
//
//        System.out.println("11111111111111111111111111111111111111111111111111111");
//        System.out.println(cert);
//        System.out.println("222222222222222222222222222222222222222222222222222222222222");
//
//
//
//
//
//
//        File file1 = new File("C:\\Users\\liu_y\\Desktop\\baidu11.cer");
//        FileOutputStream fileOutputStream = new FileOutputStream(file1);
//        byte [] b = cert.getPublicKey().toString().getBytes(StandardCharsets.UTF_8);
//        fileOutputStream.write(b);
//        fileOutputStream.close();
//
//        System.out.println("333333333333333333333333333333333333333333333333333333");
//        System.out.println
//                ("Added certificate to keystore 'jssecacerts' using alias '"
//                        + alias + "'");
    }

    public final static String LINE_SEPARATOR = System.getProperty("line.separator");

    protected static String convertToPem(X509Certificate cert) throws CertificateEncodingException {
        Base64 encoder = new Base64();
        String cert_begin = "-----BEGIN CERTIFICATE-----\n";
        String end_cert = "-----END CERTIFICATE-----";
//        final Base64.Encoder encoder = Base64.getMimeEncoder(64, LINE_SEPARATOR.getBytes());

        byte[] derCert = cert.getEncoded();
        String pemCertPre = new String(encoder.encode(derCert));
        String pemCert = cert_begin + pemCertPre + end_cert;
        return pemCert;
    }


    private static final char[] HEXDIGITS = "0123456789abcdef".toCharArray();

    private static String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 3);
        for (int b : bytes) {
            b &= 0xff;
            sb.append(HEXDIGITS[b >> 4]);
            sb.append(HEXDIGITS[b & 15]);
            sb.append(' ');
        }
        return sb.toString();
    }

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

}
