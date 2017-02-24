package com.example.admin.mypplication;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by zq on 2017/1/16.
 */
public class ResultVo implements Serializable {


    private String bushou;

    private String head;

    private String pinyin;

    private String chengyujs;

    private String from_;

    private String example;

    private String yufa;

    public String getBushou() {
        return bushou;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getChengyujs() {
        return chengyujs;
    }

    public void setChengyujs(String chengyujs) {
        this.chengyujs = chengyujs;
    }

    public String getFrom_() {
        return from_;
    }

    public void setFrom_(String from_) {
        this.from_ = from_;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getYufa() {
        return yufa;
    }

    public void setYufa(String yufa) {
        this.yufa = yufa;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getCiyujs() {
        return ciyujs;
    }

    public void setCiyujs(String ciyujs) {
        this.ciyujs = ciyujs;
    }

    public String getYinzhengjs() {
        return yinzhengjs;
    }

    public void setYinzhengjs(String yinzhengjs) {
        this.yinzhengjs = yinzhengjs;
    }

    public String[] getTongyi() {
        return tongyi;
    }

    public void setTongyi(String[] tongyi) {
        this.tongyi = tongyi;
    }

    public String[] getFanyi() {
        return fanyi;
    }

    @Override
    public String toString() {
        return "ResultVo{" +
                "bushou='" + bushou + '\'' +
                ", head='" + head + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", chengyujs='" + chengyujs + '\'' +
                ", from_='" + from_ + '\'' +
                ", example='" + example + '\'' +
                ", yufa='" + yufa + '\'' +
                ", ciyujs='" + ciyujs + '\'' +
                ", yinzhengjs='" + yinzhengjs + '\'' +
                ", tongyi=" + Arrays.toString(tongyi) +
                ", fanyi=" + Arrays.toString(fanyi) +
                '}';
    }

    public void setFanyi(String[] fanyi) {
        this.fanyi = fanyi;
    }

    public void setBushou(String bushou) {
        this.bushou = bushou;
    }

    private String ciyujs;

    private String yinzhengjs;

    private String [] tongyi;
    private String [] fanyi;


}
