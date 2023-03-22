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


    @OptIn(DelicateCoroutinesApi::class)
    fun insert(note: NoteClass) {
        GlobalScope.launch {
            mnoteDao.insert(note)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun updateNote(note: NoteClass) {
        GlobalScope.launch {
            mnoteDao.update(note)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun deleteNote1(note: NoteClass){
        GlobalScope.launch {
            mnoteDao.deleteNote(note)
        }
    }


    @OptIn(DelicateCoroutinesApi::class)
    fun pin(id : Long, pin : Boolean){
        GlobalScope.launch {
            mnoteDao.pin(id, pin)
        }
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