package com.example.notepadapp.roomDatabseClasses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteClass (
//    @PrimaryKey @ColumnInfo(name = "id") var id: Long = 0,
    @PrimaryKey(autoGenerate = true) var id : Long,
    @ColumnInfo(name = "title") var title : String,
    @ColumnInfo(name = "detail") var detail : String,
    @ColumnInfo(name = "date") var date : String,
    @ColumnInfo(name = "pin") var pin : Boolean = false
)
/*
@Entity(tableName = "employeeTbl")
data class Emplyee(
    @PrimaryKey
    var id:Long?,
    @ColumnInfo(name = "uuid")
    var name: String
)
 */

























/*
@Entity(tableName = "student")
data class StudentClass(

    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "first_name") val firstName : String,
    @ColumnInfo(name = "last_name") val lastName : String,
    @ColumnInfo(name = "roll_no") val rollNo : Int,
)
)
 */



/*
@Entity(tableName = "student")
data class StudentClass(

    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "first_name") val firstName : String,
    @ColumnInfo(name = "last_name") val lastName : String,
    @ColumnInfo(name = "roll_no") val rollNo : Int,
)

 */