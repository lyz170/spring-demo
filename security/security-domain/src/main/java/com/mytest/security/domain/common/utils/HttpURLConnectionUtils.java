package com.mytest.security.domain.common.utils;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

public class HttpURLConnectionUtils {

    private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.78 Safari/537.36";

    private final static int CONN_TIMEOUT = 3000;

    private final static int READ_TIMEOUT = 10000;

    public static String doGet(String url, Map<String, String> params) throws IOException {
        return doGetConnection(url, params);
    }

    public static String doPost(String url, Map<String, String> params) throws IOException {
        return doPostConnection(url, params);
    }

    private static String doGetConnection(String url, Map<String, String> params)
            throws IOException {

        HttpURLConnection conn = null;
        DataOutputStream out = null;
        BufferedReader reader = null;
        try {
            // GET方式参数要拼接在URL之后,并使用UTF-8 encode
            if (isNotEmptyMap(params)) {
                url = url + "?" + urlencode(params);
            }
            // 创建URL对象
            URL urlObj = new URL(url);
            // 生成HttpURLConnection对象
            conn = (HttpURLConnection) urlObj.openConnection();
            // 设置URL请求的类别
            conn.setRequestMethod("GET");
            // 设置User-agent
            conn.setRequestProperty("User-agent", USER_AGENT);
            // 不使用缓存
            conn.setUseCaches(false);
            // 连接主机的超时时间
            conn.setConnectTimeout(CONN_TIMEOUT);
            // 从主机读取数据的超时时间
            conn.setReadTimeout(READ_TIMEOUT);
            // 设置是否从httpUrlConnection读入，默认情况下是true;
            conn.setDoInput(true);
            // 建立与服务器的TCP/IP连接
            conn.connect();
            // http请求实际上直到HttpURLConnection的getInputStream()这个函数里面才正式发送出去(此时才真正建立链接)
            // 而对outputStream的写操作，又必须要在inputStream的读操作之前
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String strRead = null;
            // 读取Response数据
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            return sb.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (out != null) {
                out.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    private static String doPostConnection(String url, Map<String, String> params)
            throws IOException {

        HttpURLConnection conn = null;
        DataOutputStream out = null;
        BufferedReader reader = null;
        try {
            // 创建URL对象
            URL urlObj = new URL(url);
            // 生成HttpURLConnection对象
            conn = (HttpURLConnection) urlObj.openConnection();
            // 设置URL请求的类别
            conn.setRequestMethod("POST");
            // 设置User-agent
            conn.setRequestProperty("User-agent", USER_AGENT);
            // 不使用缓存
            conn.setUseCaches(false);
            // 连接主机的超时时间
            conn.setConnectTimeout(CONN_TIMEOUT);
            // 从主机读取数据的超时时间
            conn.setReadTimeout(READ_TIMEOUT);
            // 设置是否从httpUrlConnection读入，默认情况下是true;
            conn.setDoInput(true);
            // 建立与服务器的TCP/IP连接
            conn.connect();
            // 如果需要POST方式发送数据
            // 此处getOutputStream会隐含的进行connect(如同调用上面的connect()方法)
            if (isNotEmptyMap(params)) {
                out = new DataOutputStream(conn.getOutputStream());
                out.writeBytes(urlencode(params));
            }
            // http请求实际上直到HttpURLConnection的getInputStream()这个函数里面才正式发送出去(此时才真正建立链接)
            // 而对outputStream的写操作，又必须要在inputStream的读操作之前
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String strRead = null;
            // 读取Response数据
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            return sb.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (out != null) {
                out.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    private static String urlencode(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Entry<String, String> i : params.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue(), "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private static boolean isNotEmptyMap(Map<String, String> params) {
        return params != null && !params.isEmpty();
    }
}