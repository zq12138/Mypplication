package com.example.admin.mypplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by zq on 2017/1/16.
 */

public class HttpURLConnectionUtil {

    public static void requestGet(HashMap<String, String> paramsMap) {
        try {
            String baseUrl = "http://v.juhe.cn/chengyu/query";
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }
            String requestUrl = baseUrl + tempParams.toString();
            URL url = new URL(requestUrl);
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            urlCon.setConnectTimeout(1000 * 5);
            urlCon.setReadTimeout(1000 * 5);
            urlCon.setUseCaches(true);
            urlCon.setRequestMethod("GET");
            urlCon.setRequestProperty("Content-Type", "application/json");
            urlCon.setRequestProperty("Connection", "Keep-Alive");
            urlCon.connect();
            if (urlCon.getResponseCode() == 200) {
                String result = staramToString(urlCon.getInputStream());
                Log.i("tag", "请求成功" + result);
            } else {
                Log.i("tag", "请求失败");
            }
            urlCon.disconnect();
        } catch (Exception e) {
            Log.i("tag", e.toString() + "--------");
        }
    }

    private void requestDoPost(HashMap<String, String> paramsMap) {
        try {
            String baseUrl = "";
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos >= 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }
            baseUrl = baseUrl + tempParams.toString();
            URL url = new URL(baseUrl);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setConnectTimeout(1000 * 5);
            urlConn.setReadTimeout(1000 * 5);
            urlConn.setUseCaches(false);
            urlConn.setDoInput(true);
            urlConn.setDoInput(true);
            urlConn.setRequestMethod("POST");
            urlConn.setInstanceFollowRedirects(true);
            urlConn.setRequestProperty("Content-Type", "application/json");
            urlConn.connect();
            if (urlConn.getResponseCode() == 200) {
                InputStream is = urlConn.getInputStream();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                if ((len = is.read(buffer)) != -1) {
                    byteArray.write(buffer, 0, len);
                }
                Gson gson = new Gson();
                gson.fromJson(new String(byteArray.toByteArray()), BaseVo.class);


            }
            urlConn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void requestPost(HashMap<String, String> paramsMap) {
        try {
            String baseUrl = "http://v.juhe.cn/chengyu/query?";
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                Log.e("tag", key + "==" + URLEncoder.encode(paramsMap.get(key), "utf-8"));
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }
            String tempStr = baseUrl + tempParams.toString();
            URL url = new URL(tempStr);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setConnectTimeout(1000 * 5);
            urlConn.setReadTimeout(1000 * 5);
            urlConn.setDoOutput(true);
            urlConn.setDoInput(true);
            urlConn.setUseCaches(false);
            urlConn.setRequestMethod("POST");
            urlConn.setInstanceFollowRedirects(true);
            urlConn.setRequestProperty("Content-Type", "application/json");
            urlConn.connect();
            // 判断请求是否成功
            if (urlConn.getResponseCode() == 200) {
                // 获取返回的数据
                String result = staramToString(urlConn.getInputStream());
                Gson gson = new Gson();
                BaseVo mBaseVo = gson.fromJson(result, BaseVo.class);
                Log.e("tag", mBaseVo.toString() + "  --" + mBaseVo.getResult().toString());
                Log.e("tag", "Post方式请求成功，result--->" + result);
            } else {
                Log.e("tag", "Post方式请求失败");
            }
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void requestPost(HashMap<String, String> paramsMap) {
//
//
//        URL url = null;
//        try {
//            url = new URL("");
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.setRequestMethod("POST");
//            httpURLConnection.setConnectTimeout(5 * 1000);
//            httpURLConnection.setReadTimeout(5 * 1000);
//
//            httpURLConnection.setDoOutput(true);
//            httpURLConnection.setDoInput(true);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }

    private static String staramToString(InputStream inputStream) {
        try {
            byte[] buffer = new byte[1024];
            int len = 0;
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            if ((len = inputStream.read(buffer)) != -1) {
                byteStream.write(buffer, 0, len);
            }
            byteStream.close();
            inputStream.close();
            return new String(byteStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void upLoadFile(String filePath, HashMap<String, String> paramsMap) {
        try {
            String baseUrl = "";
            File file = new File(filePath);
            URL url = new URL(baseUrl);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setConnectTimeout(1000 * 5);
            urlConn.setReadTimeout(1000 * 5);
            urlConn.setRequestMethod("POST");
            urlConn.setUseCaches(false);
            urlConn.setRequestProperty("connection", "Keep-Alive");
            urlConn.setRequestProperty("Accept-Charset", "UTF-8");
            urlConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + "*****");
            urlConn.connect();
            String name = file.getName();
            DataOutputStream dataOutputStream = new DataOutputStream(urlConn.getOutputStream());
            dataOutputStream.writeBytes("--" + "*****" + "\r\n");
            StringBuilder tempParams = new StringBuilder();
            tempParams.append("Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + name + "\"; ");
            int pos = 0;
            int size = paramsMap.size();
            for (String key : paramsMap.keySet()) {
                tempParams.append(String.format("%s=\"%s\"", key, paramsMap.get(key), "utf-8"));
                if (pos < size - 1) {
                    tempParams.append(";");
                }
                pos++;
            }
            tempParams.append("\r\n");
            tempParams.append("Content-Type: application/octet-stream\\r\\n");
            tempParams.append("\r\n");
            String params = tempParams.toString();
            dataOutputStream.writeBytes(params);
            DataInputStream is = new DataInputStream(new FileInputStream(file));
            int byteRead = 0;
            byte[] buffer = new byte[1024];
            if ((byteRead = is.read(buffer)) != -1) {
                dataOutputStream.write(buffer, 0, byteRead);
            }
            dataOutputStream.writeBytes("\r\n");
            dataOutputStream.flush();
            dataOutputStream.writeBytes("--" + "*****" + "--" + "\r\n");
            dataOutputStream.flush();
            if (urlConn.getResponseCode() == 200) {

            }
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private static void downLoadFile(String fileUrl) {
        try {
            URL url = new URL(fileUrl);
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            urlCon.setRequestMethod("GET");
            urlCon.setReadTimeout(1000 * 5);
            urlCon.setConnectTimeout(1000 * 5);
            urlCon.setUseCaches(true);
            urlCon.setRequestProperty("Content-Type", "application/json");
            urlCon.setRequestProperty("Connection", "Keep-Alive");
            urlCon.connect();
            if (urlCon.getResponseCode() == 200) {
                String filePath = "";
                File descFile = new File(filePath);
                FileOutputStream fos = new FileOutputStream(descFile);
                int len = 0;
                byte[] buffer = new byte[1024];
                InputStream inputStream = urlCon.getInputStream();
                while ((len = inputStream.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
            } else {
                Log.e("tag", "文件下载失败");
            }
            urlCon.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取缩放后的本地图片
     *
     * @param filePath
     * @param width
     * @param height
     * @return
     */
    private static Bitmap readBitmapFromFile(String filePath, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        int srcHeight = options.outHeight;
        int srcWidth = options.outWidth;
        int inSampleSize = 1;
        if (srcWidth > width || srcHeight > height) {
            if (srcWidth > srcHeight) {
                inSampleSize = Math.round(srcHeight / height);
            } else {
                inSampleSize = Math.round(srcWidth / width);
            }
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        return BitmapFactory.decodeFile(filePath, options);
    }


    /**
     * 高效获取缩放后的本地图片
     *
     * @param filePath
     * @param width
     * @param height
     * @return
     */
    public static Bitmap readBitmapFromFileDescriptor(String filePath, int width, int height) {
        try {
            FileInputStream fs = new FileInputStream(filePath);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor(fs.getFD(), null, options);
            int srcWidth = options.outWidth;
            int srcHeight = options.outHeight;
            int inSampleSize = 1;
            if (srcWidth > width || srcHeight > height) {
                if (srcWidth > srcHeight) {
                    inSampleSize = Math.round(srcHeight / height);
                } else {
                    inSampleSize = Math.round(srcWidth / width);
                }
            }
            options.inJustDecodeBounds = false;
            options.inSampleSize = inSampleSize;
            BitmapFactory.decodeFileDescriptor(fs.getFD(), null, options);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取缩放后的本地图片
     *
     * @param is
     * @param width
     * @param height
     * @return
     */

    public static Bitmap readBitmapFromInputStream(InputStream is, int width, int height) {

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, options);
        int inSampleSize = 1;
        int srcHeight = options.outHeight;
        int srcWidth = options.outWidth;

        if (srcHeight > height || srcWidth > width) {
            if (srcWidth > srcHeight) {
                inSampleSize = Math.round(srcHeight / height);
            } else {
                inSampleSize = Math.round(srcWidth / width);
            }
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        return BitmapFactory.decodeStream(is, null, options);
    }

    /**
     * 从资源文件中读取文件
     *
     * @param resources
     * @param resourceId
     * @param width
     * @param height
     * @return
     */
    public static Bitmap readBitmapFromResource(Resources resources, int resourceId, int width, int height) {

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;

        BitmapFactory.decodeResource(resources, resourceId, options);

        int inSampleSize = 1;

        int srcWidth = options.outWidth;
        int srcHeight = options.outHeight;

        if (srcWidth > width || srcHeight > height) {
            if (srcWidth > srcHeight) {
                inSampleSize = Math.round(srcHeight / height);
            } else {
                inSampleSize = Math.round(srcWidth / width);
            }
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        return BitmapFactory.decodeResource(resources, resourceId, options);
    }

    /**
     * @param resources
     * @param resourcseId
     * @param width
     * @param height
     * @return
     */
    private static Bitmap readBitmapFromResourceStream(Resources resources, int resourcseId, int width, int height) {
        InputStream inputStream = resources.openRawResource(resourcseId);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        int inSampleSize = 1;
        int srcWidth = options.outWidth;
        int srcHeight = options.outHeight;

        if (srcWidth > width || srcHeight > height) {
            if (srcWidth > srcHeight) {
                inSampleSize = Math.round(srcHeight / height);
            } else {
                inSampleSize = Math.round(srcWidth / width);
            }
        }
        options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(inputStream, null, options);
    }


    /**
     * 压缩图片
     *
     * @param filePath
     * @param bitmap
     * @param quality
     */
    public static void writeBitmapToFile(String filePath, Bitmap bitmap, int quality) {
        try {
            File file = new File(filePath);
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fos);
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 压缩图片
     *
     * @param bitmap
     * @return
     */
    private static Bitmap compressImage(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

        byte[] bytes = bos.toByteArray();
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);

        Bitmap mBitmap = BitmapFactory.decodeStream(bis);
        return mBitmap;
    }


    /**
     * 根据scale生成一张图片
     *
     * @param bitmap
     * @param scale  等比缩放值
     * @return
     */
    private static Bitmap bitmapScale(Bitmap bitmap, int scale) {
        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        Bitmap mbitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return mbitmap;
    }


}



