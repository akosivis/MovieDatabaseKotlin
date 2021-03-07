package com.viselvis.moviedatabasekotlin.interfaces

import androidx.room.*
import com.viselvis.moviedatabasekotlin.model.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Query("SELECT * FROM contact_table")
    fun getAll(): Flow<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertContact(vararg contacts: Contact)

    @Delete
    fun delete(contact: Contact)

    @Query("DELETE FROM contact_table")
    suspend fun deleteAll()
}