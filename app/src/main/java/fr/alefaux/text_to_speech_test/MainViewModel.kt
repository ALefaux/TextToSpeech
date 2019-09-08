package fr.alefaux.text_to_speech_test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import timber.log.Timber
import java.lang.Exception

class MainViewModel: ViewModel() {

    val articleLiveData = MutableLiveData<String>()

    fun scrapWikipedia(url: String) {
        try {
            GlobalScope.launch {
                Jsoup.connect(url).get().run {
                    this.select("img").remove()
                    this.select("table").remove()
                    this.select("#toc").remove()
                    this.select(".mw-editsection").remove()
                    this.select(".homonymie").remove()
                    val html = this.select(".mw-parser-output").html()

                    Timber.d(html)

                    articleLiveData.postValue(html)
                }
            }
        } catch (e: Exception) {
            Timber.e("$e")
        }
    }
}