package fr.alefaux.text_to_speech_test

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
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

                    Log.d("MainViewModel", html)

                    articleLiveData.postValue(html)
                }
            }
        } catch (e: Exception) {
            Log.e("Jsoup", "$e")
        }
    }
}