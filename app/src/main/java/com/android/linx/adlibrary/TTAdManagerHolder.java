package com.android.linx.adlibrary;

import android.content.Context;

import com.bytedance.sdk.openadsdk.TTAdConfig;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdSdk;


/**
 * 可以用一个单例来保存TTAdManager实例，在需要初始化sdk的时候调用
 */
public class TTAdManagerHolder {

    private static boolean sInit;


    public static TTAdManager get() {
        if (!sInit) {
            throw new RuntimeException("TTAdSdk is not init, please check.");
        }
        return TTAdSdk.getAdManager();
    }

    /**
     * 默认模式调用
     *
     * @param context
     * @param appid   appid来自穿山甲
     * @param appName app名字
     */
    public static void init(Context context, String appid, String appName) {
        doInit(context, appid, appName);
    }


    /**
     * 自定义模式调用
     *
     * @param context
     * @param config
     */
    private static void init(Context context, TTAdConfig config) {
        if (!sInit) {
            TTAdSdk.init(context, config);
            sInit = true;
        }
    }


    //step1:接入网盟广告sdk的初始化操作，详情见接入文档和穿山甲平台说明
    private static void doInit(Context context, String appid, String appName) {
        if (!sInit) {
            sInit = true;
            TTAdSdk.init(context, buildConfig(appid, appName), new TTAdSdk.InitCallback() {
                @Override
                public void success() {

                }

                @Override
                public void fail(int i, String s) {
                    sInit=false;
                }
            });

        }
    }

    private static TTAdConfig buildConfig(String appid, String appName) {
        return new TTAdConfig.Builder()
                .appId(appid)
                .useTextureView(true) //使用TextureView控件播放视频,默认为SurfaceView,当有SurfaceView冲突的场景，可以使用TextureView
                .appName(appName)
                .titleBarTheme(TTAdConstant.TITLE_BAR_THEME_DARK)
                .allowShowNotify(true) //是否允许sdk展示通知栏提示
                .allowShowPageWhenScreenLock(true) //是否在锁屏场景支持展示广告落地页
                .debug(BuildConfig.DEBUG) //测试阶段打开，可以通过日志排查问题，上线时去除该调用
                .directDownloadNetworkType(TTAdConstant.NETWORK_STATE_WIFI) //允许直接下载的网络状态集合
                .supportMultiProcess(true) //是否支持多进程，true支持
                .asyncInit(true) //异步初始化sdk，开启可减少初始化耗时
                //.httpStack(new MyOkStack3())//自定义网络库，demo中给出了okhttp3版本的样例，其余请自行开发或者咨询工作人员。
                .build();
    }
}
