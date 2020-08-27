package com.budd.java.jdkBasic.interfaces.interfaceImpl.extendImplCoexist.shamMultipleExtend;

import java.io.IOException;
import java.nio.CharBuffer;

public class AdapterRandomDoubles extends RandomDoubles implements Readable {

    private int count;

    public AdapterRandomDoubles(int count) {
        this.count = count;
    }

    public int read(CharBuffer cb) throws IOException {
        if (count-- == 0)
            return -1;
        String result = Double.toString(
                next()//
        ) + "";
        cb.append(result);
        return result.length();
    }

}
