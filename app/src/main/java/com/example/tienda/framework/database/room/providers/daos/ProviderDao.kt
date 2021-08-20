package com.example.tienda.framework.database.room.providers.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tienda.framework.database.room.providers.entities.Provider

@Dao
interface ProviderDao {
    @get:Query("SELECT * FROM providers")
    val all: LiveData<List<Provider>>

    @Query("SELECT * FROM providers WHERE id = :id LIMIT 1")
    fun getById(id: Int): LiveData<Provider?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(provider: Provider)

    @Update
    fun update(provider: Provider)

    @Delete
    fun delete(provider: Provider)
}