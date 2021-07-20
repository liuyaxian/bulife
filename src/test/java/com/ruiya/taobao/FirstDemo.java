package com.ruiya.taobao;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/***
 * https://blog.csdn.net/justry_deng/article/details/81042379
 *
 */
public class FirstDemo {


    @RequestMapping("/doGetControl")
    public String doGetControl() {
        return "";
    }

//    @RequestMapping("/doGetControlTwo")
//    public String doGetControlTwo(String name, Integer age) {
//        return "";
//    }
//
//    @RequestMapping(value = "/doPostControlTwo", method = RequestMethod.POST)
//    public String doGetControlTwo(String name, Integer age) {
//        return "";
//    }

    /**
     * GET---无参测试
     */
    @Test
    public void doGetTestOne() {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        String uri = "https://www.jjmmw.com/api/products/?zltype=ALL_FND&sub_status=1&sales_status=1&page_size=10&fields=code%2Cname_abbr%2Caccum_net%2Cunit_net%2Czlfee%2Ctradedate_display2%2Coneweek_income%2Conemonth_income%2Cthreemonth_income%2Ccanbuy%2Csixmonth_income%2Coneyear_income%2Cthisyear_income%2Cfound_income&ordering=oneyear_income&ordering_direction=desc";
        // 创建Get请求
        HttpGet httpGet = new HttpGet(uri);

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
//                Html html = new Html(EntityUtils.toString(responseEntity));

//                /html/body/div[7]/div/div/div[1]/div[1]/p/span[3] /html/body/div[7]/div/div/div[1]/div[17]/p/span[3]
//                String location = html.xpath("/html/body/div/").toString();
//                System.out.println("响应内容长度为:" + location );
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    /**
//     * GET---有参测试 (方式一:手动在url后面加上参数)
//     */
//    @Test
//    public void doGetTestWayOne() {
//        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//
//        // 参数
//        StringBuffer params = new StringBuffer();
//        try {
//            // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
//            params.append("name=" + URLEncoder.encode("&", "utf-8"));
//            params.append("&");
//            params.append("age=24");
//        } catch (UnsupportedEncodingException e1) {
//            e1.printStackTrace();
//        }
//
//        // 创建Get请求
//        HttpGet httpGet = new HttpGet("http://localhost:12345/doGetControllerTwo" + "?" + params);
//        // 响应模型
//        CloseableHttpResponse response = null;
//        try {
//            // 配置信息
//            RequestConfig requestConfig = RequestConfig.custom()
//                    // 设置连接超时时间(单位毫秒)
//                    .setConnectTimeout(5000)
//                    // 设置请求超时时间(单位毫秒)
//                    .setConnectionRequestTimeout(5000)
//                    // socket读写超时时间(单位毫秒)
//                    .setSocketTimeout(5000)
//                    // 设置是否允许重定向(默认为true)
//                    .setRedirectsEnabled(true).build();
//
//            // 将上面的配置信息 运用到这个Get请求里
//            httpGet.setConfig(requestConfig);
//
//            // 由客户端执行(发送)Get请求
//            response = httpClient.execute(httpGet);
//
//            // 从响应模型中获取响应实体
//            HttpEntity responseEntity = response.getEntity();
//            System.out.println("响应状态为:" + response.getStatusLine());
//            if (responseEntity != null) {
//                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
//                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // 释放资源
//                if (httpClient != null) {
//                    httpClient.close();
//                }
//                if (response != null) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * GET有参(方式二：使用URI获得HttpGet)：
//     */
//    @Test
//    public void doGetTestWayTwo() {
//        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//
//        // 参数
//        URI uri = null;
//        try {
//            // 将参数放入键值对类NameValuePair中,再放入集合中
//            List<NameValuePair> params = new ArrayList<>();
//            params.add(new BasicNameValuePair("name", "&"));
//            params.add(new BasicNameValuePair("age", "18"));
//            // 设置uri信息,并将参数集合放入uri;
//            // 注:这里也支持一个键值对一个键值对地往里面放setParameter(String key, String value)
//            uri = new URIBuilder().setScheme("http").setHost("localhost")
//                    .setPort(12345).setPath("/doGetControllerTwo")
//                    .setParameters(params).build();
//        } catch (URISyntaxException e1) {
//            e1.printStackTrace();
//        }
//        // 创建Get请求
//        HttpGet httpGet = new HttpGet(uri);
//
//        // 响应模型
//        CloseableHttpResponse response = null;
//        try {
//            // 配置信息
//            RequestConfig requestConfig = RequestConfig.custom()
//                    // 设置连接超时时间(单位毫秒)
//                    .setConnectTimeout(5000)
//                    // 设置请求超时时间(单位毫秒)
//                    .setConnectionRequestTimeout(5000)
//                    // socket读写超时时间(单位毫秒)
//                    .setSocketTimeout(5000)
//                    // 设置是否允许重定向(默认为true)
//                    .setRedirectsEnabled(true).build();
//
//            // 将上面的配置信息 运用到这个Get请求里
//            httpGet.setConfig(requestConfig);
//
//            // 由客户端执行(发送)Get请求
//            response = httpClient.execute(httpGet);
//
//            // 从响应模型中获取响应实体
//            HttpEntity responseEntity = response.getEntity();
//            System.out.println("响应状态为:" + response.getStatusLine());
//            if (responseEntity != null) {
//                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
//                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // 释放资源
//                if (httpClient != null) {
//                    httpClient.close();
//                }
//                if (response != null) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /***
//     * 	 * POST---无参测试
//     */
//    @Test
//    public void doPostTestOne() {
//
//        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//
//        // 创建Post请求
//        HttpPost httpPost = new HttpPost("http://localhost:12345/doPostControllerOne");
//        // 响应模型
//        CloseableHttpResponse response = null;
//        try {
//            // 由客户端执行(发送)Post请求
//            response = httpClient.execute(httpPost);
//            // 从响应模型中获取响应实体
//            HttpEntity responseEntity = response.getEntity();
//
//            System.out.println("响应状态为:" + response.getStatusLine());
//            if (responseEntity != null) {
//                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
//                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // 释放资源
//                if (httpClient != null) {
//                    httpClient.close();
//                }
//                if (response != null) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /***
//     * POST有参(普通参数)：
//     * 注：POST传递普通参数时，方式与GET一样即可，这里以直接在url后缀上参数的方式示例。
//     */
//    @Test
//    public void doPostTestFour() {
//
//        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//
//        // 参数
//        StringBuffer params = new StringBuffer();
//        try {
//            // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
//            params.append("name=" + URLEncoder.encode("&", "utf-8"));
//            params.append("&");
//            params.append("age=24");
//        } catch (UnsupportedEncodingException e1) {
//            e1.printStackTrace();
//        }
//
//        // 创建Post请求
//        HttpPost httpPost = new HttpPost("http://localhost:12345/doPostControllerFour" + "?" + params);
//
//        // 设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)
//        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
//
//        // 响应模型
//        CloseableHttpResponse response = null;
//        try {
//            // 由客户端执行(发送)Post请求
//            response = httpClient.execute(httpPost);
//            // 从响应模型中获取响应实体
//            HttpEntity responseEntity = response.getEntity();
//
//            System.out.println("响应状态为:" + response.getStatusLine());
//            if (responseEntity != null) {
//                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
//                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // 释放资源
//                if (httpClient != null) {
//                    httpClient.close();
//                }
//                if (response != null) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    // 对应接收示例：
//    public String doPostControllerThree(String name, Integer age) {
//        return "";
//    }
//    /**
//     * POST有参(对象参数)：
//     */
//    /**
//     * POST---有参测试(对象参数)
//     *
//     * @date 2018年7月13日 下午4:18:50
//     */
//    @Test
//    public void doPostTestTwo() {
//
//        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//
//        // 创建Post请求
//        HttpPost httpPost = new HttpPost("http://localhost:12345/doPostControllerTwo");
//        User user = new User();
//        user.setName("潘晓婷");
//        user.setAge(18);
//        user.setGender("女");
//        user.setMotto("姿势要优雅~");
//        // 我这里利用阿里的fastjson，将Object转换为json字符串;
//        // (需要导入com.alibaba.fastjson.JSON包)
//        String jsonString = JSON.toJSONString(user);
//
//        StringEntity entity = new StringEntity(jsonString, "UTF-8");
//
//        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
//        httpPost.setEntity(entity);
//
//        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
//
//        // 响应模型
//        CloseableHttpResponse response = null;
//        try {
//            // 由客户端执行(发送)Post请求
//            response = httpClient.execute(httpPost);
//            // 从响应模型中获取响应实体
//            HttpEntity responseEntity = response.getEntity();
//
//            System.out.println("响应状态为:" + response.getStatusLine());
//            if (responseEntity != null) {
//                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
//                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // 释放资源
//                if (httpClient != null) {
//                    httpClient.close();
//                }
//                if (response != null) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @RequestMapping(value = "doPostControllerTwo", method = RequestMethod.POST)
//    public String doPostControllerTwo(@RequestBody User user) {
//        return user.getGender();
//    }
//
//    /**
//     * POST有参(普通参数 + 对象参数)：
//     * 注：POST传递普通参数时，方式与GET一样即可，这里以通过URI获得HttpPost的方式为例。
//     */
//    @Test
//    public void doPostTestThree() {
//
//        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//
//        // 创建Post请求
//        // 参数
//        URI uri = null;
//        try {
//            // 将参数放入键值对类NameValuePair中,再放入集合中
//            List<NameValuePair> params = new ArrayList<>();
//            params.add(new BasicNameValuePair("flag", "4"));
//            params.add(new BasicNameValuePair("meaning", "这是什么鬼？"));
//            // 设置uri信息,并将参数集合放入uri;
//            // 注:这里也支持一个键值对一个键值对地往里面放setParameter(String key, String value)
//            uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(12345)
//                    .setPath("/doPostControllerThree").setParameters(params).build();
//        } catch (URISyntaxException e1) {
//            e1.printStackTrace();
//        }
//
//        HttpPost httpPost = new HttpPost(uri);
//        // HttpPost httpPost = new
//        // HttpPost("http://localhost:12345/doPostControllerThree1");
//
//        // 创建user参数
//        User user = new User();
//        user.setName("潘晓婷");
//        user.setAge(18);
//        user.setGender("女");
//        user.setMotto("姿势要优雅~");
//
//        // 将user对象转换为json字符串，并放入entity中
//        StringEntity entity = new StringEntity(JSON.toJSONString(user), "UTF-8");
//
//        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
//        httpPost.setEntity(entity);
//
//        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
//
//        // 响应模型
//        CloseableHttpResponse response = null;
//        try {
//            // 由客户端执行(发送)Post请求
//            response = httpClient.execute(httpPost);
//            // 从响应模型中获取响应实体
//            HttpEntity responseEntity = response.getEntity();
//
//            System.out.println("响应状态为:" + response.getStatusLine());
//            if (responseEntity != null) {
//                String responseStr = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
//                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
//                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // 释放资源
//                if (httpClient != null) {
//                    httpClient.close();
//                }
//                if (response != null) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @RequestMapping(value = "doPostControllerThree", method = RequestMethod.POST)
//    public String doPostControllerThree(@RequestBody User user, Integer age , String name) {
//        return user.getGender();
//    }
//
//
//    /**
//     * 根据是否是https请求，获取HttpClient客户端
//     *
//     * TODO 本人这里没有进行完美封装。对于 校不校验校验证书的选择，本人这里是写死
//     *      在代码里面的，你们在使用时，可以灵活二次封装。
//     *
//     * 提示: 此工具类的封装、相关客户端、服务端证书的生成，可参考我的这篇博客:
//     *      <linked>https://blog.csdn.net/justry_deng/article/details/91569132</linked>
//     *
//     *
//     * @param isHttps 是否是HTTPS请求
//     *
//     * @return  HttpClient实例
//     * @date 2019/9/18 17:57
//     */
//    private CloseableHttpClient getHttpClient(boolean isHttps) {
//        CloseableHttpClient httpClient;
//        if (isHttps) {
//            SSLConnectionSocketFactory sslSocketFactory;
//            try {
//                /// 如果不作证书校验的话
//                sslSocketFactory = getSocketFactory(false, null, null);
//
//                /// 如果需要证书检验的话
//                // 证书
//                //InputStream ca = this.getClass().getClassLoader().getResourceAsStream("client/ds.crt");
//                // 证书的别名，即:key。 注:cAalias只需要保证唯一即可，不过推荐使用生成keystore时使用的别名。
//                // String cAalias = System.currentTimeMillis() + "" + new SecureRandom().nextInt(1000);
//                //sslSocketFactory = getSocketFactory(true, ca, cAalias);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//            httpClient = HttpClientBuilder.create().setSSLSocketFactory(sslSocketFactory).build();
//            return httpClient;
//        }
//        httpClient = HttpClientBuilder.create().build();
//        return httpClient;
//    }
//
//    /**
//     * HTTPS辅助方法, 为HTTPS请求 创建SSLSocketFactory实例、TrustManager实例
//     *
//     * @param needVerifyCa
//     *         是否需要检验CA证书(即:是否需要检验服务器的身份)
//     * @param caInputStream
//     *         CA证书。(若不需要检验证书，那么此处传null即可)
//     * @param cAalias
//     *         别名。(若不需要检验证书，那么此处传null即可)
//     *         注意:别名应该是唯一的， 别名不要和其他的别名一样，否者会覆盖之前的相同别名的证书信息。别名即key-value中的key。
//     *
//     * @return SSLConnectionSocketFactory实例
//     * @throws NoSuchAlgorithmException
//     *         异常信息
//     * @throws CertificateException
//     *         异常信息
//     * @throws KeyStoreException
//     *         异常信息
//     * @throws IOException
//     *         异常信息
//     * @throws KeyManagementException
//     *         异常信息
//     * @date 2019/6/11 19:52
//     */
//    private static SSLConnectionSocketFactory getSocketFactory(boolean needVerifyCa, InputStream caInputStream, String cAalias)
//            throws CertificateException, NoSuchAlgorithmException, KeyStoreException,
//            IOException, KeyManagementException {
//        X509TrustManager x509TrustManager;
//        // https请求，需要校验证书
//        if (needVerifyCa) {
//            KeyStore keyStore = getKeyStore(caInputStream, cAalias);
//            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//            trustManagerFactory.init(keyStore);
//            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
//            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
//                throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
//            }
//            x509TrustManager = (X509TrustManager) trustManagers[0];
//            // 这里传TLS或SSL其实都可以的
//            SSLContext sslContext = SSLContext.getInstance("TLS");
//            sslContext.init(null, new TrustManager[]{x509TrustManager}, new SecureRandom());
//            return new SSLConnectionSocketFactory(sslContext);
//        }
//        // https请求，不作证书校验
//        x509TrustManager = new X509TrustManager() {
//            @Override
//            public void checkClientTrusted(X509Certificate[] arg0, String arg1) {
//            }
//
//            @Override
//            public void checkServerTrusted(X509Certificate[] arg0, String arg1) {
//                // 不验证
//            }
//
//            @Override
//            public X509Certificate[] getAcceptedIssuers() {
//                return new X509Certificate[0];
//            }
//        };
//        SSLContext sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(null, new TrustManager[]{x509TrustManager}, new SecureRandom());
//        return new SSLConnectionSocketFactory(sslContext);
//    }
//
//    /**
//     * 获取(密钥及证书)仓库
//     * 注:该仓库用于存放 密钥以及证书
//     *
//     * @param caInputStream
//     *         CA证书(此证书应由要访问的服务端提供)
//     * @param cAalias
//     *         别名
//     *         注意:别名应该是唯一的， 别名不要和其他的别名一样，否者会覆盖之前的相同别名的证书信息。别名即key-value中的key。
//     * @return 密钥、证书 仓库
//     * @throws KeyStoreException 异常信息
//     * @throws CertificateException 异常信息
//     * @throws IOException 异常信息
//     * @throws NoSuchAlgorithmException 异常信息
//     * @date 2019/6/11 18:48
//     */
//    private static KeyStore getKeyStore(InputStream caInputStream, String cAalias)
//            throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException {
//        // 证书工厂
//        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
//        // 秘钥仓库
//        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//        keyStore.load(null);
//        keyStore.setCertificateEntry(cAalias, certificateFactory.generateCertificate(caInputStream));
//        return keyStore;
//    }
//
////    application/x-www-form-urlencoded表单请求(示例)：
//
//**
//        *
//        * 发送文件
// *
//         * multipart/form-data传递文件(及相关信息)
// *
//         * 注:如果想要灵活方便的传输文件的话，
//            *    除了引入org.apache.httpcomponents基本的httpclient依赖外
// *    再额外引入org.apache.httpcomponents的httpmime依赖。
//            *    追注:即便不引入httpmime依赖，也是能传输文件的，不过功能不够强大。
//            *
//            */
//    @Test
//    public void test4() {
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        HttpPost httpPost = new HttpPost("http://localhost:12345/file");
//        CloseableHttpResponse response = null;
//        try {
//            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
//            // 第一个文件
//            String filesKey = "files";
//            File file1 = new File("C:\\Users\\JustryDeng\\Desktop\\back.jpg");
//            multipartEntityBuilder.addBinaryBody(filesKey, file1);
//            // 第二个文件(多个文件的话，使用同一个key就行，后端用数组或集合进行接收即可)
//            File file2 = new File("C:\\Users\\JustryDeng\\Desktop\\头像.jpg");
//            // 防止服务端收到的文件名乱码。 我们这里可以先将文件名URLEncode，然后服务端拿到文件名时在URLDecode。就能避免乱码问题。
//            // 文件名其实是放在请求头的Content-Disposition里面进行传输的，如其值为form-data; name="files"; filename="头像.jpg"
//            multipartEntityBuilder.addBinaryBody(filesKey, file2, ContentType.DEFAULT_BINARY, URLEncoder.encode(file2.getName(), "utf-8"));
//            // 其它参数(注:自定义contentType，设置UTF-8是为了防止服务端拿到的参数出现乱码)
//            ContentType contentType = ContentType.create("text/plain", Charset.forName("UTF-8"));
//            multipartEntityBuilder.addTextBody("name", "邓沙利文", contentType);
//            multipartEntityBuilder.addTextBody("age", "25", contentType);
//
//            HttpEntity httpEntity = multipartEntityBuilder.build();
//            httpPost.setEntity(httpEntity);
//
//            response = httpClient.execute(httpPost);
//            HttpEntity responseEntity = response.getEntity();
//            System.out.println("HTTPS响应状态为:" + response.getStatusLine());
//            if (responseEntity != null) {
//                System.out.println("HTTPS响应内容长度为:" + responseEntity.getContentLength());
//                // 主动设置编码，来防止响应乱码
//                String responseStr = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
//                System.out.println("HTTPS响应内容为:" + responseStr);
//            }
//        } catch (ParseException | IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // 释放资源
//                if (httpClient != null) {
//                    httpClient.close();
//                }
//                if (response != null) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}