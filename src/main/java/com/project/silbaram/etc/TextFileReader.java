package com.project.silbaram.etc;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class TextFileReader {

    public static String readTextFileFromUrl(String textUrl) throws IOException {
        //db에서 가져오는 text 파일 url을 String으로 변경해서 리턴
        //여기서 변경된 String을 tts에 입력하면 됨
        URL url = new URL(textUrl);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            return sb.toString();
        }
    }
}
