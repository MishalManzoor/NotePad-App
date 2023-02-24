package com.example.notepadapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.notepadapp.mvvm.ViewModel
import com.example.notepadapp.roomDatabseClasses.NoteClass
import java.text.SimpleDateFormat
import java.util.*

class WriteNotes : AppCompatActivity() {

    private lateinit var msave: ImageView
    private lateinit var mbackButton: ImageView
    private lateinit var mtitle: EditText
    private lateinit var mdetail: EditText
    private lateinit var model: ViewModel
    private var mid: Long = -1

    @SuppressLint("MissingInflatedId", "SimpleDateFormat", "QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_notes)

        msave = findViewById(R.id.wsave)
        mtitle = findViewById(R.id.wtitle)
        mdetail = findViewById(R.id.wdetail)
        mbackButton = findViewById(R.id.back_btn)

        actionBar?.hide()

        val simpleDate =
            SimpleDateFormat("EEE, d MMM yyyy HH:mm a")
        val date = Date()

        // get model initialize it
        model = ViewModelProvider(this)[ViewModel::class.java]

        val noteType = intent.getStringExtra("noteType")

        if (noteType.equals("Edit")) {

            mid = intent.getLongExtra("mid", -1)
            val title1 = intent.getStringExtra("mt").toString()
            val detail1 = intent.getStringExtra("md").toString()

            mtitle.setText(title1)
            mdetail.setText(detail1)
        }

        msave.setOnClickListener {

            val title = mtitle.text.toString()
            val detail = mdetail.text.toString()

            if (noteType.equals("Edit")) {
                if (title.isNotEmpty() || detail.isNotEmpty()) {

                    val note =
                        NoteClass(mid, title, detail, simpleDate.format(date))

                    model.updateNote(note)
                    Toast.makeText(
                        this,
                        "Note Updated..",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            } else {
                if (title.isNotEmpty() || detail.isNotEmpty()) {
                    val note =
                        NoteClass(0, title, detail, simpleDate.format(date))
                    model.insertItem(note)
                    Toast.makeText(
                        this,
                        "Note Inserted..",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            val intent = Intent(
                this, MainActivity::class.java
            )
            startActivity(intent)
            this.finish()
        }
        mbackButton.setOnClickListener {
            sendIntent()

        }
    }

    private fun sendIntent() {
        val intent1 = Intent(
            this, MainActivity::
            class.java
        )
        startActivity(intent1)
    }
}