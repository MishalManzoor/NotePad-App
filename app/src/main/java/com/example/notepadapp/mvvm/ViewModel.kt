package com.example.notepadapp.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notepadapp.roomDatabseClasses.NoteClass

class ViewModel(application: Application) : AndroidViewModel(application) {
    // i will use this class only to insert or get data
    private var mdataRepo: DataRepo
    private var mListLive: LiveData<List<NoteClass>>

    init {
        // initialize repo class
        mdataRepo = DataRepo(application)
        // getAllData method from repo
        mListLive = mdataRepo.getAllData()

    }

    fun searchInDatabase(query: String): LiveData<List<NoteClass>> {
        return mdataRepo.searchInDatabase(query)
    }

//    fun searchDatabase(searchQuery: String): LiveData<List<Person>> {
//        return repository.searchDatabase(searchQuery).asLiveData()
//    }

    // to get data
    fun getAllData(): LiveData<List<NoteClass>> {
        return mListLive
    }

    // to insert
    fun insertItem(note: NoteClass) {
        mdataRepo.insert(note)
    }

    fun deleteNote1(note: NoteClass) {
        mdataRepo.deleteNote1(note)
    }

    fun updateNote(note: NoteClass) {
        mdataRepo.updateNote(note)
    }
}