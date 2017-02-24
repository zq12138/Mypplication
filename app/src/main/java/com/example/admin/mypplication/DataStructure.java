package com.example.admin.mypplication;

/**
 * Created by zq on 2017/2/7.
 */

public class DataStructure {
    /**
     * 数组的创建
     */
    int c[] = {1, 2, 3, 4, 5};
    int[] b = new int[10];

    public void array() {
        String[] names;
        names = new String[5];
        names[0] = "aaa";
        names[1] = "bbb";
        names[2] = "ccc";
        for (int i = 0; i < names.length; i++) {
            System.out.print(names[i]);
        }
    }

    /**
     * 数组插入
     * 5   3
     */

    public int[] insert(int[] old, int values, int index) {
        for (int h = old.length - 1; h > index; h--)
            old[h] = old[h - 1];
        old[index] = values;
        return old;
    }

    /**
     * 数组删除
     */
    public int[] delete(int[] old, int index) {
        for (int h = index; h < old.length - 1; h++) {
            old[h] = old[h + 1];

        }
        old[old.length - 1] = 0;
        return old;
    }


}
