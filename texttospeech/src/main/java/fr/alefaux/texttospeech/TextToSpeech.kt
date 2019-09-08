package fr.alefaux.texttospeech

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.QUEUE_ADD
import android.speech.tts.UtteranceProgressListener
import timber.log.Timber
import java.util.*

class TextToSpeech(context: Context, initListener: TextToSpeech.OnInitListener) {

    private var sections = mutableListOf<String>()
    private val tts = TextToSpeech(context, initListener)
    var language: Locale = Locale.ENGLISH
        set(value) {
            field = value
            tts.language = value
        }

    init {
        tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onDone(p0: String?) {
                Timber.d("[ALE] onDone : $p0")
                sections.removeAt(0)
            }

            override fun onError(p0: String?) {
                Timber.d("[ALE] onError : $p0")
            }

            override fun onStart(p0: String?) {
                Timber.d("[ALE] onStart : $p0")
            }

        })
    }

    fun setSpeechRate(rate: Float) {
        tts.setSpeechRate(rate)
    }

    fun read(text: String) {
        Timber.d("Read has text ${text.isNotBlank()}")

        if (sections.isEmpty()) {
            sections = text.split("\n").toMutableList()
        }

        Timber.d("Read has text splited ${sections.isNotEmpty()}")

        sections.forEach { word ->
            tts.speak(word, QUEUE_ADD, null, "")
        }
    }

    fun pause() {
        tts.stop()
    }

    fun stop() {
        tts.stop()
        sections.clear()
    }

    fun shutdown() {
        tts.shutdown()
    }

    companion object {
        const val ERROR = TextToSpeech.ERROR
    }
}