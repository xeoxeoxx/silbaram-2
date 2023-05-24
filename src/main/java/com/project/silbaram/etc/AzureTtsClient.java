package com.project.silbaram.etc;

import com.microsoft.cognitiveservices.speech.*;
import org.springframework.stereotype.Service;

@Service
public class AzureTtsClient {

    private final String subscriptionKey = "b699bc4cf52b472b8b7bdba6dd1f167b";
    private final String subscriptionregion = "eastasia";
    private final SpeechSynthesizer synthesizer;

    public AzureTtsClient() {

        SpeechConfig speechConfig = SpeechConfig.fromSubscription(this.subscriptionKey, this.subscriptionregion);
        this.synthesizer = new SpeechSynthesizer(speechConfig, null);
    }

    public byte[] synthesize(String text, String language) throws Exception {
        SpeechSynthesisResult result = synthesizer.SpeakText(text);
        if (result.getReason() != ResultReason.SynthesizingAudioCompleted) {
            throw new Exception("Speech Synthesis failed : " + result.getReason());
        }
        return result.getAudioData();
        //return이 byteString인거 주의하기
    }
}
