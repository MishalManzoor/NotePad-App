package com.example.notepadapp.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notepadapp.roomDatabseClasses.NoteClass

class ViewModel(application: Application) : AndroidViewModel(application) {

    // this class for insert or get data
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

    // to get data
    fun getAllData(): LiveData<List<NoteClass>> {
        return mListLive
    }

    // to insert
    fun insertItem(note: NoteClass) {
        mdataRepo.insert(note)
    }

    fun updateNote(note: NoteClass) {
        mdataRepo.updateNote(note)
    }

    fun deleteNote1(note: NoteClass) {
        mdataRepo.deleteNote1(note)
    }

    fun pin(id : Long, pin : Boolean) {
        mdataRepo.pin(id , pin)
    }
}