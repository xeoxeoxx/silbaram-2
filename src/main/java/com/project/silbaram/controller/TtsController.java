package com.project.silbaram.controller;

import com.project.silbaram.etc.AzureTtsClient;
import com.project.silbaram.etc.TextFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TtsController {

    @Autowired
    private AzureTtsClient ttsClient;

    @GetMapping("/synthesize/{bookName}")
    public ResponseEntity<byte[]> synthesize(@PathVariable("bookName") String bookName){
        String bookUrl = "https://storage.googleapis.com/ebook_sample/" + bookName;
        String language = "en-US";

        try{
            String bookData = TextFileReader.readTextFileFromUrl(bookUrl);
            byte[] audioDataStream = ttsClient.synthesize(bookData, language);

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "audio/wav")
                    .body(audioDataStream.clone());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
