package com.example.admin.mypplication;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.util.LruCache;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

/**
 * Created by zq on 2017/1/18.
 */

public class MyGlideModule implements GlideModule {

    private LruCache lruCache;

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
//        int maxMemory = (int) getRuntime().maxMemory();//获取系统分配给应用的总内存大小
//        int memoryCacheSize = maxMemory / 8;//设置图片内存缓存占用八分之一
//        builder.setMemoryCache(new LruResourceCache(memoryCacheSize));//设置内存缓存大小
//
//        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
//        int defaultCacheSize = calculator.getMemoryCacheSize();
//        int defaultPoolSize = calculator.getBitmapPoolSize();
//
//        File cacheDir = context.getExternalCacheDir();//指定的数据缓存地址
//        int diskCacheDirSize = 1024 * 1024 * 30;//最多可以缓存多少字节的数据
//        //设置磁盘缓存大小
//        builder.setDiskCache(new DiskLruCacheFactory(cacheDir.getPath(), "glide", diskCacheDirSize));
//        //存放在data/data/xxxx/cache
//        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, "glide", diskCacheDirSize));
//        //存放在外置文件浏览器
//        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, "glide", defaultCacheSize));
//        //设置图片解码格式
//        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
//        builder.setBitmapPool(new LruBitmapPool(defaultCacheSize));


        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int mMaxCache = maxMemory / 8;
        lruCache = new LruCache<String, Drawable>(mMaxCache) {
            @Override
            protected int sizeOf(String key, Drawable value) {
                if (value instanceof BitmapDrawable) {
                    Bitmap bitmap = ((BitmapDrawable) value).getBitmap();
                    return bitmap == null ? 0 : bitmap.getByteCount();
                }
                return super.sizeOf(key, value);
            }
        };

    }

    // 添加Drawable到内存缓存
    private void addDrawableToMemoryCache(String key, Bitmap bitmap) {
        if (getDrawableMemoryCache(key) == null && bitmap != null) {
            lruCache.put(key, bitmap);
        }
    }


    // 从内存缓存中获取一个Drawable
    private Drawable getDrawableMemoryCache(String key) {
        return (Drawable) lruCache.get(key);
    }

    //从内存缓存中移除一个Drawable

    private void removeDrawableFormMemoryCache(String key) {
        lruCache.remove(key);
    }
    // 清理内存缓存
    private void cleanMemoryCache()
    {
        lruCache.evictAll();
    }







    @Override

    public void registerComponents(Context context, Glide glide) {
        glide.register(IDataModel.class, InputStream.class, new MyDataLoader.Factory());
    }
}
