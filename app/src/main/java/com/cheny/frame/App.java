package com.cheny.frame;

import android.app.Application;
import android.content.Context;

import com.cheny.frame.base.Base;
import com.facebook.stetho.Stetho;
import com.socks.library.KLog;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by y on 2016/3/16.
 */
public class App extends Application {

    private RefWatcher mRefWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        Base.initialize(this);
        KLog.init(BuildConfig.DEBUG);

        // 如果检测到某个 activity 有内存泄露，LeakCanary 就是自动地显示一个通知
        mRefWatcher = LeakCanary.install(this);

        //Stetho
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

    /**
     * 获取内存监控
     *
     * @param context
     * @return
     */
    public static RefWatcher getRefWatcher(Context context) {
        App application = (App) context.getApplicationContext();
        return application.mRefWatcher;
    }
}
