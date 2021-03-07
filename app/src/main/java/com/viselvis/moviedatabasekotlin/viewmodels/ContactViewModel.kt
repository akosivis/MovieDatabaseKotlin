package com.viselvis.moviedatabasekotlin.viewmodels

import androidx.lifecycle.*
import com.viselvis.moviedatabasekotlin.model.Contact
import com.viselvis.moviedatabasekotlin.repository.ContactRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ContactViewModel(private val repo: ContactRepository): ViewModel() {
    val allContacts: LiveData<List<Contact>> = repo.allContacts.asLiveData()

    fun insert(contact: Contact) = viewModelScope.launch {
        repo.insert(contact)
    }
}

class ContactViewModelFactory(private val repo: ContactRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ContactViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel Class")
    }
}