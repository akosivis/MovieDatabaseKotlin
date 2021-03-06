package com.viselvis.moviedatabasekotlin.viewmodels

import androidx.lifecycle.ViewModel
import com.viselvis.moviedatabasekotlin.model.Contact

class ContactViewModel: ViewModel() {
    var contactList = emptyList<Contact>()

    init {

    }
}