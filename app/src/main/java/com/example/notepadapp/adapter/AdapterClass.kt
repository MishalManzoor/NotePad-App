package com.example.notepadapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.notepadapp.R
import com.example.notepadapp.roomDatabseClasses.NoteClass
import java.util.*
import kotlin.random.Random

class AdapterClass(var context: Context, var onNoteListener: OnNoteListener) :
    RecyclerView.Adapter<AdapterClass.ViewHolder>() {

    var mlist: List<NoteClass> = ArrayList()
    var searchText = ""

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.Title)
        val detail: TextView = itemView.findViewById(R.id.detail)
        val date: TextView = itemView.findViewById(R.id.date)
        var layout: LinearLayout = itemView.findViewById(R.id.linearLayout)
        var delete: ImageView = itemView.findViewById(R.id.delete_note)

        //     var bookmark: ImageView = itemView.findViewById(R.id.pin)
        var cardColor: CardView = itemView.findViewById(R.id.card)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list: NoteClass = mlist[position]
        holder.title.text = list.title
        holder.detail.text = list.detail
        holder.layout.visibility = View.VISIBLE
        holder.delete.visibility = View.VISIBLE
        holder.date.text = list.date

        // add random colors into cards
        holder.cardColor.setCardBackgroundColor(
            holder.layout.resources
                .getColor(randomColor(), null)
        )

        // listener to update note
        holder.layout.setOnClickListener {
            onNoteListener.onUpdateNoteClick(position.toLong(), list)
        }

        // listener to delete note
        holder.delete.setOnClickListener {
            onNoteListener.onDeleteNoteClick(mlist[position])
        }
//
//        holder.bookmark.setOnClickListener {
//            onNoteListener.onBoomMarkNoteClick(list)
//        }
        searchData(holder, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.design, parent, false)
        return ViewHolder(view)
    }

    private fun randomColor(): Int {
        val list1 = ArrayList<Int>()
        list1.add(R.color.color1)
        list1.add(R.color.color2)
        list1.add(R.color.color3)
        list1.add(R.color.color4)
        list1.add(R.color.color5)
        list1.add(R.color.color6)
        list1.add(R.color.color7)
        list1.add(R.color.color8)
        list1.add(R.color.color9)
        list1.add(R.color.color10)
        list1.add(R.color.color11)

        val seed = System.currentTimeMillis().toInt()
        val randomColor = Random(seed).nextInt(list1.size)
        return list1[randomColor]
    }

    override fun getItemCount(): Int {
        return mlist.size
    }

    // interface for listener
    interface OnNoteListener {
        //  fun onNoteClick(position : Long, note : NoteClass)
        fun onUpdateNoteClick(position: Long, note: NoteClass)

        //    fun onLongNoteClick(position : Long, id : Long)
        fun onDeleteNoteClick(position: NoteClass)

        //   fun onBoomMarkNoteClick(note: NoteClass)

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list1: List<NoteClass>) {
        // on below line we are clearing
        // our notes arrayList
        (mlist as ArrayList).clear()
        // on below line we are inserting
        // new list to our all notes list.
        (mlist as ArrayList).addAll(list1)
        notifyDataSetChanged()
    }

    private fun searchData(holder: ViewHolder, position: Int) {


        // to get a noteClass title in string
        val title: String = mlist[position]
            .title.lowercase(Locale.getDefault())

        // get noteClass title into holder title (TextView)
        holder.title.text = mlist[position].title

        if (title.contains(searchText)) {

            // this part is for title
            // start Position of title
            val startPT: Int = title.lowercase(Locale.ROOT)
                .indexOf(searchText)

            // end position of detail
            val endPT = startPT + searchText.length

            // for title ,highlight search text in title
            val spannableT = Spannable.Factory.getInstance()
                .newSpannable(holder.title.text)

            // set color and position for title
            val foreGroundColorT = ForegroundColorSpan(Color.RED)
            spannableT.setSpan(
                foreGroundColorT, startPT, endPT, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            // set in holder
            holder.title.text = spannableT
        }

        // to get a noteClass title in string
        val detail: String = mlist.get(position)
            .detail.lowercase(Locale.getDefault())

        // get noteClass title into holder title (TextView)
        holder.detail.text = mlist.get(position).detail

        if (detail.contains(searchText)) {

            // this part is for detail
            // start Position of detail
            val startPD: Int = detail.lowercase(Locale.ROOT)
                .indexOf(searchText)

            // end position of detail
            val endPD = startPD + searchText.length

            // for detail ,highlight search text in detail
            val spannableD = Spannable.Factory.getInstance()
                .newSpannable(holder.detail.text)

            // set color and position for detail
            val foreGroundColorD = ForegroundColorSpan(Color.RED)
            spannableD.setSpan(
                foreGroundColorD, startPD, endPD, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            // set in holder
            holder.detail.text = spannableD
        }
    }
}