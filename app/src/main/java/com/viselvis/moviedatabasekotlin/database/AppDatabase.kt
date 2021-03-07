package com.viselvis.moviedatabasekotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.viselvis.moviedatabasekotlin.interfaces.ContactDao
import com.viselvis.moviedatabasekotlin.model.Contact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Contact::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    private class ContactDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let {
                appDatabase ->
                scope.launch {
                    populateDb(appDatabase.contactDao())
                }
            }
        }

        suspend fun populateDb(contactDao: ContactDao) {
            contactDao.deleteAll()

            val cont1 = Contact("Vis1", "0910391")
            contactDao.insertContact(cont1)
            val cont2 = Contact("Vis2", "091220391")
            contactDao.insertContact(cont2)
        }

    }

    companion object {
        @Volatile
        var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context, scope: CoroutineScope) : AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                   context.applicationContext,
                   AppDatabase::class.java,
                    "app_database"
                ).addCallback(ContactDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                instance
            }
        }

        fun destroyDatabase() {
            INSTANCE = null
        }
    }
}