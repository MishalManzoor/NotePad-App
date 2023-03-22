package com.example.notepadapp

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notepadapp.adapter.AdapterClass
import com.example.notepadapp.mvvm.ViewModel
import com.example.notepadapp.roomDatabseClasses.NoteClass
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), AdapterClass.OnNoteListener {

    private lateinit var add: FloatingActionButton
    private lateinit var mAdapterClass: AdapterClass
    private lateinit var mRecyclerView: RecyclerView
    lateinit var note: TextView
    private lateinit var model: ViewModel
    private lateinit var search: SearchView
    private lateinit var selectedNote: NoteClass

    @SuppressLint("NotifyDataSetChanged", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        add = findViewById(R.id.add)
        mRecyclerView = findViewById(R.id.recyclerView)
        search = findViewById(R.id.search)

        actionBar?.hide()

        add.imageTintList = ColorStateList.valueOf(Color.WHITE)
// set recycleView
        mRecyclerView.layoutManager =
            StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
            )

        mAdapterClass = AdapterClass(this)
        mRecyclerView.adapter = mAdapterClass

        // get model initialize it
        model = ViewModelProvider(this)[ViewModel::class.java]

        // bind to LiveData
        model.getAllData().observe(this) { dataItems ->
            dataItems?.let {
                mAdapterClass.updateList(it)
            }
        }

        // listener to send intent to write activity
        add.setOnClickListener {
            val intent1 = Intent(
                this, WriteNotes::class.java
            )
            startActivity(intent1)
            this.finish()
        }
        search()
    }

    override fun onUpdateNoteClick(position: Long, note: NoteClass) {
        // send intent
        val i = Intent(this, WriteNotes::class.java)
        i.putExtra("noteType", "Edit")
        i.putExtra("mid", note.id)
        i.putExtra("mt", note.title)
        i.putExtra("md", note.detail)
        startActivity(i)
        this.finish()
    }

    override fun onDeleteNoteClick(position: NoteClass) {

        model.deleteNote1(position)

        Toast.makeText(
            this,
            "Deleting",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onBoomMarkNoteClick(note: NoteClass, card: CardView) {

        selectedNote = NoteClass(note.id, note.title, note.detail, note.date)
        selectedNote = note

        if (selectedNote.pin) {
            model.pin(note.id, false)
            Toast.makeText(this, "unpinned", Toast.LENGTH_SHORT)
                .show()
        } else {
            model.pin(note.id, true)
            Toast.makeText(this, "pinned", Toast.LENGTH_SHORT)
                .show()
        }

        // bind to LiveData
        model.getAllData().observe(this) { dataItems ->
            dataItems?.let {
                mAdapterClass.updateList(it)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")

    fun search() {
        search.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?)
                    : Boolean {
                // it will triggered when we submit the written text
                return true
            }

            // We have just created this function for searching our database
            override fun onQueryTextChange(newText: String?)
                    : Boolean {
                if (newText != null) {
                    searchDatabase(newText)
                }
                return true
            }
        })
    }

    fun searchDatabase(query: String) {

        val query1 = "%$query%"

        model.searchInDatabase(query1).observe(this) { list ->
            list.let {
                mAdapterClass.updateList(it)
            }
        }
    }
}