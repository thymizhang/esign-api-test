import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.*;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.SSLInitializationException;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * e签宝接口：通过上传方式创建文件，官方代码
 *
 * @Author thymi
 * @Date 2020/12/21
 */
public class EsignTest {
    private static CloseableHttpClient httpClient = null;

    private static final String DEFAULT_CHAR_SET = "UTF-8";

    public static void main(String[] args) throws IOException {
        /* 设置请求方法 支持GET POST HEAD OPTIONS PUT PATCH DELETE TRACE */
        HttpRequestBase request = getRequest("POST");
        /* 设置请求url */
        request.setURI(URI.create("https://smlopenapi.esign.cn/v1/files/getUploadUrl"));
        /* 设置header */
        String headerJson = "{\"X-Tsign-Open-App-Id\":\"7438835487\",\"X-Tsign-Open-Token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJnSWQiOiIwNWJmMGE3MmQzYTg0NTA0OGFhMjg3MDYzMWRjNzM1MCIsImFwcElkIjoiNzQzODgzNTQ4NyIsIm9JZCI6Ijk5NGIwZGY5YjU2NzQ0Y2M4YjZhYTIxMmNiMGI4MmZhIiwidGltZXN0YW1wIjoxNjA4NTQ0NDAxMTAwfQ.ePuca1sFMXS_3ZqR1pyerJvABD4TSeLr-Thg9FDn6MI\"}";
        Map<String, String> headers = JSONObject.parseObject(headerJson, new TypeReference<LinkedHashMap<String,
                String>>() {
        });
        for (Map.Entry<String, String> h : headers.entrySet()) {
            request.setHeader(h.getKey(), h.getValue());
        }
        /* 设置requestbody中的参数 */
        String json = "{\"contentMd5\":\"X7XD1k3xIoYYs3F3mBNo+Q==\",\"contentType\":\"application/octet-stream\",\"convert2Pdf\":false,\"fileName\":\"C:\\Users\\Administrator\\Desktop\\星海小镇2018年01月10日项目施工日志.doc\",\"fileSize\":22134}";
        if (StringUtils.isNotBlank(json) && request instanceof HttpEntityEnclosingRequestBase) {
            HttpEntity entity = EntityBuilder.create()
                    .setContentEncoding(DEFAULT_CHAR_SET)
                    .setContentType(ContentType.APPLICATION_JSON)
                    .setText(json)
                    .build();
            ((HttpEntityEnclosingRequestBase) request).setEntity(entity);
        }
        CloseableHttpResponse response = httpClient.execute(request);
        try {
            if (null == response || null == response.getStatusLine()) {
                throw new RuntimeException("请求结果无法解析！");
            }
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("responseCode:" + statusCode);
            System.out.println("result:" + EntityUtils.toString(response.getEntity(), DEFAULT_CHAR_SET));
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * 初始化httpclient,样例代码不可用于生产，仅支持简单请求发送，核心参数未设置
     */
    static {
        try {
            RegistryBuilder<ConnectionSocketFactory> connectionSocketFactoryRegistryBuilder = RegistryBuilder.<ConnectionSocketFactory>create();
            connectionSocketFactoryRegistryBuilder.register("http", PlainConnectionSocketFactory.getSocketFactory());
            connectionSocketFactoryRegistryBuilder.register("https", new SSLConnectionSocketFactory(SSLContexts.custom().loadTrustMaterial(null,
                    new TrustStrategy() {
                        @Override
                        public boolean isTrusted(X509Certificate[] x509Certificates, String s) {
                            return true;
                        }
                    }).build(), NoopHostnameVerifier.INSTANCE));
            Registry<ConnectionSocketFactory> registry = connectionSocketFactoryRegistryBuilder
                    .build();

            PoolingHttpClientConnectionManager gcm = new PoolingHttpClientConnectionManager(registry);
            HttpClientBuilder httpClientBuilder = HttpClients.custom();
            httpClient = httpClientBuilder
                    .setConnectionManager(gcm)
                    .build();
        } catch (Throwable e) {
            throw new SSLInitializationException(e.getMessage(), e);
        }
    }

    private static HttpRequestBase getRequest(String method) {
        if (HttpGet.METHOD_NAME.equals(method)) {
            return new HttpGet();
        } else if (HttpPost.METHOD_NAME.equals(method)) {
            return new HttpPost();
        } else if (HttpHead.METHOD_NAME.equals(method)) {
            return new HttpHead();
        } else if (HttpOptions.METHOD_NAME.equals(method)) {
            return new HttpOptions();
        } else if (HttpPut.METHOD_NAME.equals(method)) {
            return new HttpPut();
        } else if (HttpPatch.METHOD_NAME.equals(method)) {
            return new HttpPatch();
        } else if (HttpDelete.METHOD_NAME.equals(method)) {
            return new HttpDelete();
        } else if (HttpTrace.METHOD_NAME.equals(method)) {
            return new HttpTrace();
        }
        return new HttpPost();
    }
}
