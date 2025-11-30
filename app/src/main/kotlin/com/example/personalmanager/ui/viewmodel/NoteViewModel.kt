package com.example.personalmanager.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.personalmanager.data.database.AppDatabase
import com.example.personalmanager.data.model.Note
import com.example.personalmanager.data.repository.NoteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NoteRepository
    val allNotes: StateFlow<List<Note>>
    
    init {
        val noteDao = AppDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        allNotes = repository.allNotes.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }
    
    fun addNote(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }
    
    fun updateNote(note: Note) = viewModelScope.launch {
        repository.update(note.copy(updatedAt = System.currentTimeMillis()))
    }
    
    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.delete(note)
    }
}
