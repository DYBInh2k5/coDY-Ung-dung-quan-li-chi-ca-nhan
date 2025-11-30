package com.example.personalmanager.data.dao

import androidx.room.*
import com.example.personalmanager.data.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY updatedAt DESC")
    fun getAllNotes(): Flow<List<Note>>
    
    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)
    
    @Update
    suspend fun updateNote(note: Note)
    
    @Delete
    suspend fun deleteNote(note: Note)
}
