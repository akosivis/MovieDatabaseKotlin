package com.viselvis.moviedatabasekotlin.viewmodels

import androidx.lifecycle.ViewModel

class AccountViewModel: ViewModel() {

    var score = 0

    init {

    }

    fun increment() {
        score++
    }
}