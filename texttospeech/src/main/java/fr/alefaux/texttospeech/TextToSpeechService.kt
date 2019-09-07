package fr.alefaux.texttospeech

import android.speech.tts.SynthesisCallback
import android.speech.tts.SynthesisRequest
import android.speech.tts.TextToSpeechService

class TextToSpeechService: TextToSpeechService() {
    override fun onGetLanguage(): Array<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadLanguage(p0: String?, p1: String?, p2: String?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onIsLanguageAvailable(p0: String?, p1: String?, p2: String?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSynthesizeText(p0: SynthesisRequest?, p1: SynthesisCallback?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}