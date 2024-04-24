package com.module.util;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


public class CrawlerUtils {


    public static String doGet(String url) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);

            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }


    public static String getIndexHtml(String url) {
        GetMethod getMethod = null;
        String infomation = "";
        getMethod = new GetMethod(url);
        try {
            int statcode = HttpClientUtil.getHttpClient().executeMethod(getMethod);
            infomation = getMethod.getResponseBodyAsString();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return infomation;
    }


    public static void writeDir(String dir, String src, String file_name) {
        InputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            URL url = new URL(src);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // 截取文件后缀名
            String fileNames[] = src.split("/");
            String fileName = fileNames[fileNames.length - 1];
            String prefix = "";
            if (fileName.indexOf(".") != -1) {
                prefix = fileName.substring(fileName.lastIndexOf(".")); // 截取文件后缀名，防止gif图片变成普通图片
            }
            // 超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            inStream = conn.getInputStream();
            byte[] data = readInputStream(inStream);
            File dirs = new File(dir);
            if (!dirs.exists()) {
                dirs.mkdirs();
            }
            File imageFile = new File(dir + "/" + file_name + prefix);
            outStream = new FileOutputStream(imageFile);
            outStream.write(data);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] readInputStream(InputStream inStream) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        try {
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outStream.toByteArray();
    }


}
