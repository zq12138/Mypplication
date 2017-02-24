package com.example.admin.mypplication;

/**
 * Created by zq on 2017/2/7.
 */

public class ArrayStack {
    private long[] a;
    private int size;
    private int top;

    public ArrayStack(int maxSize) {
        this.a = new long[maxSize];
        this.size = maxSize;
        this.top = -1;
    }

    /**
     * 判断是否是栈顶
     *VariantGroovy
     * @return
     */
    public boolean isFull() {
        return (top == size - 1);
    }

    /**
     * 判断是否为空def
     */
    public boolean isEmpty() {
        return (top == -1);
    }

    public void push(int value) {
        if (isFull()) {
            System.out.print("栈已满");
            return;
        }
        a[++top] = value;
    }

    /**
     * 编译器将源代码(包括依赖库)转化为DEX文件编译资源文件(res以及assets文件下的资源)
     * Apk Packaget 整合所有的DEX文件和编译过的资源文件，并且对APK进行签名 签名文件必须使用Debug
     * 版或者Release版使用Debug Keystore 生成的app 被用来测试和分析，使用Release Keystore 生成的app可以进行发布其他用户使用
     * 在生成最终的APK之前 APKPackager会使用zipalign 工具优化整个app 以便app在使用的过程中更加节省内存
     * @return
     */
    public long  peek() {
        if (isEmpty()) {
            System.out.print("栈空");
            return 0;
        }
        return a[top];
    }


}
