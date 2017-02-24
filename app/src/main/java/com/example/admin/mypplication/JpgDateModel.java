package com.example.admin.mypplication;

/**
 * Created by zq on 2017/1/18.
 */

public class JpgDateModel implements IDataModel {

    String dataModelUrl;

    public JpgDateModel(String dataModelUrl) {
        this.dataModelUrl = dataModelUrl;
    }

    @Override
    public String bulidDataModelUrl(int width, int height) {
        //http://78re52.com1.z0.glb.clouddn.com/resource/gogopher.jpg?imageView2/1/w/200/h/200/format/jpg
        return String.format("%s?imageView2/1/w/%d/h/%d/format/jpg", dataModelUrl, width, height);
    }
}
