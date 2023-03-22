package com.example.notepadapp.roomDatabseClasses

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteClass::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase(){

    abstract fun noteDao() : NoteDao

    companion object{
        @Volatile
        private var INSTANCE : AppDataBase? = null

        fun getDataBase(context : Context) : AppDataBase {

            if(INSTANCE == null) {
                //   synchronized(this) {
                // we will create database
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java, "note")
                    .build()
            }
                return INSTANCE as AppDataBase
        }
    }
}









































/*
@Database(entities = [StudentClass::class], version = 1 ,  exportSchema = false)
abstract class AppDataBase : RoomDatabase(){

    abstract fun studentDao() : StudentDao

    companion object{

        @Volatile
        private var INSTANCE : AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                // we will create database
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java, // this class
                    "app_database"
                ).build() //create
                INSTANCE = instance
                return instance
            }
        }
    }
 */


/*
@Database(entities = [StudentClass::class], version = 1 ,  exportSchema = false)
abstract class AppDataBase : RoomDatabase(){
    abstract fun studentDao() : StudentDao
    companion object{
        @Volatile
        private var INSTANCE : AppDataBase? = null
        fun getDatabase(context: Context): AppDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                // we will create database
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java, // this class
                    "app_database"
                ).build() //create
                INSTANCE = instance
                return instance
            }
        }
    }
}
 */