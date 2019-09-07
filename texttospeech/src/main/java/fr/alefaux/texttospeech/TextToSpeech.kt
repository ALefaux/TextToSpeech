package fr.alefaux.texttospeech

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log

class TextToSpeech(context: Context, initListener: OnInitListener): TextToSpeech(context, initListener) {

    private var words = mutableListOf<String>()

    init {
        setOnUtteranceProgressListener(object: UtteranceProgressListener() {
            override fun onDone(p0: String?) {
                Log.d("SetOnUtterancePrgsLtnr", "[ALE] onDone : $p0")
            }

            override fun onError(p0: String?) {
                Log.d("SetOnUtterancePrgsLtnr", "[ALE] onError : $p0")
            }

            override fun onStart(p0: String?) {
                Log.d("SetOnUtterancePrgsLtnr", "[ALE] onStart : $p0")
            }

        })
    }

    fun read(text: String) {
        Log.d("TextToSpeech", "Read has text ${text.isNotBlank()}")
        words = text.split(" ").filter { word -> word.isNotBlank() }.toMutableList()

        Log.d("TextToSpeech", "Read has text splited ${words.isNotEmpty()}")

        words.forEach { word ->
            speak(word, QUEUE_ADD, null, "")
        }
    }

    companion object {
        const val ERROR = TextToSpeech.ERROR
    }
}