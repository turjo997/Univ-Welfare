package com.suffixit.kieb.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CustomInputStream extends InputStream {

    private final FileInputStream fis;

    public CustomInputStream(FileInputStream fis) {
        this.fis = fis;
    }

    @Override
    public int read() throws IOException {
        return fis.read();
    }

}
