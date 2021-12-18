package com.example.net;

import android.os.Looper;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        NetUtils.request("https://www.wanandroid.com/banner/json", new HashMap<>(), "get",
                new NetUtils.NetResult() {
                    @Override
                    public void onSuccess(String str) {
                        System.out.println(str);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println(throwable.toString());
                    }
                });
    }
}
