package com.example.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;

public class NetUtils {

    public static Cancel request(String url, HashMap<String, String> params, String method, NetResult result) {
        Thread job = new Thread(()->{
            String mUrl = appendUrl(url, params, method);
            HttpURLConnection connection = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                URL mURL = new URL(mUrl);
                connection = (HttpURLConnection) mURL.openConnection();
                //配置请求信息
                config(connection, method.toUpperCase());
                //连接
                connection.connect();
                if (method.equalsIgnoreCase("post")) {
                    outputStream = connection.getOutputStream();
                    outputStream.write(appendParams(params).getBytes());
                }
                inputStream = connection.getInputStream();

                //如果当前的线程没有被停止->调用回调
                if (!Thread.currentThread().isInterrupted()) result.onSuccess(streamToString(inputStream));

            } catch (IOException e) {
                e.printStackTrace();
                //如果当前线程没有被停止->调用回掉
                if (!Thread.currentThread().isInterrupted()) result.onError(e);
            }finally {
                if (connection!=null) connection.disconnect();
                if (outputStream !=null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (inputStream !=null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        job.start();

        return ()-> job.interrupt();

    }

    private static String streamToString(InputStream in) {
        StringBuilder sb = new StringBuilder();//新建一个StringBuilder，用于一点一点
        String oneLine;//流转换为字符串的一行
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));//
        try {
            while ((oneLine = reader.readLine()) != null) {//readLine方法将读取一行
                sb.append(oneLine).append('\n');//拼接字符串并且增加换行，提高可读性
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();//将拼接好的字符串返回出去
    }

    private static void config(HttpURLConnection connection, String method) throws ProtocolException {
        connection.setRequestMethod(method);
        connection.setConnectTimeout(8000);//设置最大连接时间，单位为毫
        connection.setReadTimeout(8000);//设置最大的读取时间，单位为毫秒，
        if (method.equalsIgnoreCase("post")){
            connection.setDoOutput(true);//允许输入流
        }
        connection.setDoInput(true);//允许输出流
    }

    private static String appendUrl(String url, HashMap<String, String> params, String method) {
        String str = url;
        if (method.equalsIgnoreCase("get")) str += appendParams(params);
        return str;
    }

    private static String appendParams(HashMap<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : params.keySet()) {
            stringBuilder.append(s).append("=").append(params.get(s)).append("&");
        }
        if (stringBuilder.length() != 0) {
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length() );
        }
        return stringBuilder.toString();
    }

    interface NetResult {
        void onSuccess(String str);

        void onError(Throwable throwable);
    }

    interface Cancel{
        void cancel();
    }

}
