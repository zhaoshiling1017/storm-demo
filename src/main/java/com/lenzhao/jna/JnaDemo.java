package com.lenzhao.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * Created by lenzhao on 18-9-15.
 */
public class JnaDemo {

    public interface CLibrary extends Library {
        CLibrary INSTANCE = (CLibrary)Native.loadLibrary("c/libadd_c.so", CLibrary.class);

        int add(int a, int b);
    }

    public static void main(String[] args) {
        int sum = CLibrary.INSTANCE.add(3, 6);

        System.out.println(sum);
    }
}
