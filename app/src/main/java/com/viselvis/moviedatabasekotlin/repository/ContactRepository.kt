package com.viselvis.moviedatabasekotlin.repository

import com.viselvis.moviedatabasekotlin.interfaces.ContactDao
import com.viselvis.moviedatabasekotlin.model.Contact
import kotlinx.coroutines.flow.Flow

class ContactRepository(private val contactDao: ContactDao) {

    val allContacts: Flow<List<Contact>> = contactDao.getAll()

    suspend fun insert(contact: Contact) {
        contactDao.insertContact(contact)
    }

}