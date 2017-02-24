package com.example.admin.mypplication;

import android.content.Context;

import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;

import java.io.InputStream;

/**
 * Created by zq on 2017/1/18.flavor
 * 配置 apk 的版本信息，可以为每一个版本指定不同的 applicationId 和版本名称。关于 applicationId ，可以把它也理解为包名，不过和 Manifest 文件中的包名作用不同，它是用来给应用商店和设备区分不同的 app ，而 Manifest 中的 pakage 属性用来在源代码中引用 R 类和其他类。即同一份代码 applicationId 可以让它变成不同的 app 。示例配置如下：
 */

public class MyDataLoader extends BaseGlideUrlLoader<IDataModel> {
    public MyDataLoader(Context context) {
        super(context);
    }

    public MyDataLoader(ModelLoader<GlideUrl, InputStream> urlLoader) {
        super(urlLoader, null);
    }

    @Override
    protected String getUrl(IDataModel model, int width, int height) {
        return model.bulidDataModelUrl(width,height);
    }

    /**
     */
    public static class Factory implements ModelLoaderFactory<IDataModel, InputStream> {

        @Override
        public ModelLoader<IDataModel, InputStream> build(Context context, GenericLoaderFactory factories) {
            return new MyDataLoader(factories.buildModelLoader(GlideUrl.class, InputStream.class));
        }

        @Override
        public void teardown() {
        }
    }
}
