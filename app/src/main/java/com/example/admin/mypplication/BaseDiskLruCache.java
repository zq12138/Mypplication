package com.example.admin.mypplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by zq on 2017/1/19.
 * 基于DiskLruCache实现磁盘缓存
 */
public class BaseDiskLruCache {
    private DiskLruCache diskLruCache;

    //初始化DiskLruCache
    private void initDiskLruCache(Context context) {
        File cacheDirPath = context.getCacheDir();
        int diskCacheSize = 1024 * 1024 * 30;
        int appVersion = 0;
        int vauleCount = 0;
        try {
            diskLruCache = DiskLruCache.open(cacheDirPath, appVersion, vauleCount, diskCacheSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // /添加Bitmap到磁盘缓存

    private void addBitmapToDiskCache(String key, byte[] bytes) {
        OutputStream out = null;
        try {
            DiskLruCache.Editor editor = diskLruCache.edit(key);
            if (editor != null) {
                out = editor.newOutputStream(0);
                if (bytes != null && bytes.length > 0) {
                    out.write(bytes);
                    out.flush();
                    editor.commit();
                } else {
                    editor.abort();
                }
            }
            diskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //从磁盘缓存中读取Drawable
    private Drawable getDrawableToDiskCache(String key) {
        try {
            DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
            if (snapshot != null) {
                InputStream inputStream = snapshot.getInputStream(0);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                //  DiskLruUtils.bitmap2Drawable(bitmap);
                Drawable drawable;


            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }


}
