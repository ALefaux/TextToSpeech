package fr.alefaux.text_to_speech_test.di

import fr.alefaux.text_to_speech_test.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val mainModule = module {
    viewModel { MainViewModel() }
}