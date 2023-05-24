package com.project.silbaram.etc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImageFileReader {

    public static byte[] getImageBytesFromUrl(String imageUrl) throws IOException {
        //url의 이미지 파일을 byteArray로 변환
        //
        URL url = new URL(imageUrl);
        try (InputStream in = url.openStream();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int n;
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
            return out.toByteArray();
        }
    }

}
