package com.example.notepadapp.mvvm

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.notepadapp.roomDatabseClasses.AppDataBase
import com.example.notepadapp.roomDatabseClasses.NoteClass
import com.example.notepadapp.roomDatabseClasses.NoteDao
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DataRepo(context: Context) {
    private var mnoteDao: NoteDao
    private var mAllData: LiveData<List<NoteClass>>

    init {
        // db and dao class will combine here
        // initialize db
        val appDb = AppDataBase.getDataBase(context)
        // initialize dao
        this.mnoteDao = appDb.noteDao()
        // get dao method first list
        this.mAllData = mnoteDao.getAllDataOfUser()
    }

    // now create method to get data using doa method
    fun getAllData(): LiveData<List<NoteClass>> {
        return mAllData
    }

    fun searchInDatabase(query : String) : LiveData<List<NoteClass>>{
        return mnoteDao.searchInDatabase(query)
    }

    // You must call this on a non-UI thread or your app will crash
    @OptIn(DelicateCoroutinesApi::class)
    fun insert(note: NoteClass) {
        GlobalScope.launch {
            mnoteDao.insert(note)
        }
//        insertAsyncTask(mnoteDao).execute(note)
    }

//    // this is for background task to insert data in db
//    private class insertAsyncTask(dao: NoteDao) : AsyncTask<NoteClass, Void, Void>() {
//
//        private var mAsyncTask: NoteDao = dao
//
//        @OptIn(DelicateCoroutinesApi::class)
//        override fun doInBackground(vararg p0: NoteClass?): Void? {
//
//            GlobalScope.launch {
//                p0[0]?.let { mAsyncTask.insert(it) }
//            }
//            return null
//        }
//    }

    @OptIn(DelicateCoroutinesApi::class)
    fun deleteNote(note: Long){
        GlobalScope.launch {
            mnoteDao.delete(note)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun deleteNote1(note: NoteClass){
        GlobalScope.launch {
            mnoteDao.deleteNote(note)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun deleteList(note: List<Long>){
        GlobalScope.launch {
            mnoteDao.deleteList(note)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun pin(id : Long, pin : Boolean){
        GlobalScope.launch {
            mnoteDao.pin(id, pin)
        }

        /*

    fun searchDatabase(searchQuery: String): Flow<List<Person>> {
        return personDao.searchDatabase(searchQuery)
    }
         */
    }

//    @OptIn(DelicateCoroutinesApi::class)
//    fun deleteListItems(note: List<NoteClass>){
//        GlobalScope.launch {
//            mnoteDao.deleteListItems(note)
//        }
//    }


//    private class deleteAsyncTask(dao: NoteDao) : AsyncTask<NoteClass, Void, Void>() {
//
//        private var mAsyncTask: NoteDao = dao
//
//        @OptIn(DelicateCoroutinesApi::class)
//        override fun doInBackground(vararg p0: NoteClass?): Void? {
//
//            GlobalScope.launch {
//                p0[0]?.let { mAsyncTask.deleteListItems(it) }
//            }
//            return null
//        }
//    }

    @OptIn(DelicateCoroutinesApi::class)
    fun updateNote(note: NoteClass) {
       // insertAsyncTask(mnoteDao).execute(note)
        GlobalScope.launch {
            mnoteDao.update(note)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun updateValue(id : Long, title : String, detail : String) {
        GlobalScope.launch {
            mnoteDao.updateValues(id,title,detail)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun onTopPin(id : List<Long>, pin: Boolean) {
        GlobalScope.launch {
            mnoteDao.onTopPin(id , pin)
        }
    }

    fun getID(id : Long){
        mnoteDao.getId(id)
    }
}












































/*
    fun searchDatabase(searchQuery: String): Flow<List<Person>> {
       return personDao.searchDatabase(searchQuery)
   }
    */


/*
public class DataRepository {
    private DataDAO mDataDao;
    private LiveData<List<DataItem>> mAllData;

    public DataRepository(Application application) {
        DataRoomDbase dataRoombase = DataRoomDbase.getDatabase(application);
        this.mDataDao = dataRoombase.dataDAO();
        this.mAllData = mDataDao.getAllData();
    }

    LiveData<List<DataItem>> getAllData() {
        return mAllData;
    }

// You must call this on a non-UI thread or your app will crash

    public void insert(DataItem dataItem) {
        new insertAsyncTask(mDataDao).execute(dataItem);
    }

    private static class insertAsyncTask extends AsyncTask<DataItem, Void, Void> {
        private DataDAO mAsyncTaskDao;
        insertAsyncTask(DataDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final DataItem... params) {
            mAsyncTaskDao.insertItem(params[0]);
            return null;
        }
    }

    public void deleteItem(DataItem dataItem) {
        new deleteAsyncTask(mDataDao).execute(dataItem);
    }

    private static class deleteAsyncTask extends AsyncTask<DataItem, Void, Void> {
        private DataDAO mAsyncTaskDao;
        deleteAsyncTask(DataDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final DataItem... params) {
            mAsyncTaskDao.deleteItem(params[0]);
            return null;
        }
    }

    public void deleteItemById(Long idItem) {
        new deleteByIdAsyncTask(mDataDao).execute(idItem);
    }

    private static class deleteByIdAsyncTask extends AsyncTask<Long, Void, Void> {
        private DataDAO mAsyncTaskDao;
        deleteByIdAsyncTask(DataDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Long... params) {
            mAsyncTaskDao.deleteByItemId(params[0]);
            return null;
        }
    }
}
 */