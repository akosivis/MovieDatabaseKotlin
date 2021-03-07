package com.viselvis.moviedatabasekotlin.application

import android.app.Application
import com.viselvis.moviedatabasekotlin.database.AppDatabase
import com.viselvis.moviedatabasekotlin.repository.ContactRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MainApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppDatabase.getAppDatabase(this, applicationScope) }
    val contactRepo by lazy { ContactRepository(database.contactDao()) }
}