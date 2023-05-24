package com.project.silbaram.controller;


import com.project.silbaram.etc.ImageFileReader;
import com.project.silbaram.etc.TextFileReader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/google")
public class GCSController {



    @GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("imageName") String imageName) throws IOException {
        String imageUrl = "https://storage.googleapis.com/ebook_sample/" + imageName;
        byte[] imageBytes = ImageFileReader.getImageBytesFromUrl(imageUrl);
        return ResponseEntity.ok().body(imageBytes);
    }

//    @GetMapping(value = "/text/{textName}", produces = MediaType.TEXT_PLAIN_VALUE)
//    public ResponseEntity<String> getText(@PathVariable("textName") String textName) throws IOException {
//        String textUrl = "https://storage.googleapis.com/ebook_sample/" + textName;
//        String text = TextFileReader.readTextFileFromUrl(textUrl);
//        return ResponseEntity.ok().body(text);
//    }

    @GetMapping(value = "/audio/{bookName}")
    public ResponseEntity<String> getAudio(@PathVariable("bookName") String bookName) throws IOException {
        String bookUrl = "https://storage.googleapis.com/ebook_sample/" + bookName;
        String bookData = TextFileReader.readTextFileFromUrl(bookUrl);

        return ResponseEntity.ok(bookData);
    }

}
