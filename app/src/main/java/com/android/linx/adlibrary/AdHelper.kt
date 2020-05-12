package com.android.linx.adlibrary

import android.app.Application
import com.bytedance.sdk.openadsdk.TTAdConfig
import com.bytedance.sdk.openadsdk.TTAdManager
import com.bytedance.sdk.openadsdk.TTAdSdk

/**
 * desc:
 *
 * @author LinX
 * @date 2020/5/12
 * @QQ 297258690
 */
object AdHelper {


    /**
     *
    private static TTAdConfig buildConfig(Context context) {
    return new TTAdConfig.Builder()
    .appId("5001121")
    .useTextureView(true) //使用TextureView控件播放视频,默认为SurfaceView,当有SurfaceView冲突的场景，可以使用TextureView
    .appName("APP测试媒体")
    .titleBarTheme(TTAdConstant.TITLE_BAR_THEME_DARK)
    .allowShowNotify(true) //是否允许sdk展示通知栏提示
    .allowShowPageWhenScreenLock(true) //是否在锁屏场景支持展示广告落地页
    .debug(true) //测试阶段打开，可以通过日志排查问题，上线时去除该调用
    .directDownloadNetworkType(TTAdConstant.NETWORK_STATE_WIFI, TTAdConstant.NETWORK_STATE_3G) //允许直接下载的网络状态集合
    .supportMultiProcess(true)//是否支持多进程
    .needClearTaskReset()
    //.httpStack(new MyOkStack3())//自定义网络库，demo中给出了okhttp3版本的样例，其余请自行开发或者咨询工作人员。
    .build();
    }
     */

    fun init(context: Application?, config: TTAdConfig) {
        //强烈建议在应用对应的Application#onCreate()方法中调用，避免出现content为null的异常
        TTAdSdk.init(context, config)
    }


    fun get(): TTAdManager? = TTAdSdk.getAdManager()


}