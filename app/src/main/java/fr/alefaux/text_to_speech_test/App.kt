package fr.alefaux.text_to_speech_test

import android.app.Application
import fr.alefaux.text_to_speech_test.di.mainModule
import org.koin.android.ext.android.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(mainModule))
    }
}