package com.example.admin.mypplication;

/**
 * Created by zq on 2017/1/18.
 */

public class WebpDataModel implements IDataModel {
    String dataModelUrl;

    public WebpDataModel(String dataModelUrl) {
        this.dataModelUrl = dataModelUrl;
    }

    @Override
    public String bulidDataModelUrl(int width, int height) {
        //http://78re52.com1.z0.glb.clouddn.com/resource/gogopher.jpg?imageView2/1/w/200/h/200/format/webp
        return String.format("%s?imageView2/1/w/%d/h/%d/format/webp", dataModelUrl, width, height);
    }
}
