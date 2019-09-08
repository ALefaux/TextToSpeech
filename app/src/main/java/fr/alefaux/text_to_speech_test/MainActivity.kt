package fr.alefaux.text_to_speech_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.text.HtmlCompat
import fr.alefaux.text_to_speech_test.extensions.hide
import fr.alefaux.text_to_speech_test.extensions.show
import fr.alefaux.texttospeech.TextToSpeech
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.*

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(BuildConfig.DEBUG) {
            et_main_url.setText("https://fr.wikipedia.org/wiki/Ford_Mustang")
            scrap()
        }

        textToSpeech = TextToSpeech(this, android.speech.tts.TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                textToSpeech.language = Locale.FRENCH
                textToSpeech.setSpeechRate(1.6f)
            }
        })

        bt_main_scrap.setOnClickListener {
            scrap()
        }

        bt_main_start.setOnClickListener {
            val textToRead = tv_main_text.text.toString()
            textToSpeech.read(textToRead)
            bt_main_start.hide()
            bt_main_pause.show()
            bt_main_stop.show()
        }

        bt_main_pause.setOnClickListener {
            textToSpeech.pause()
            bt_main_pause.hide()
            bt_main_start.show()
        }

        bt_main_stop.setOnClickListener {
            textToSpeech.stop()
            bt_main_stop.hide()
            bt_main_pause.hide()
            bt_main_start.show()
        }

        viewModel.articleLiveData.observe(this, androidx.lifecycle.Observer {
            if(it.isNotBlank()) {
                val text = HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY)
                tv_main_text.text = text
                bt_main_start.isEnabled = true
            } else {
                bt_main_start.isEnabled = false
            }
        })
    }

    private fun scrap() {
        val urlToScrap = et_main_url.text.toString()
        Timber.d("Perform click $urlToScrap")
        viewModel.scrapWikipedia(urlToScrap)
    }

}
