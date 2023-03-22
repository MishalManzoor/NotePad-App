package com.example.notepadapp.roomDatabseClasses

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note : NoteClass)

    @Update
    suspend fun update(note : NoteClass)

    @Delete
    suspend fun deleteNote(id : NoteClass)

    @Query("UPDATE note SET pin = :pin WHERE id = :id")
    suspend fun pin(id : Long, pin : Boolean)

    @Query("SELECT * FROM note WHERE title LIKE :query OR detail LIKE :query")
    fun searchInDatabase(query : String) : LiveData<List<NoteClass>>

    //Get all items
    @Query("SELECT * FROM note ORDER BY id DESC")
    fun getAllDataOfUser() : LiveData<List<NoteClass>>
}











































//    //Delete one item by id
//    @Query("SELECT * FROM note WHERE roll_no LIKE :roll LIMIT 8")
//    suspend fun deleteBYItemId(id : Long)

/*
@Query("delete from timestamp where id = :idList")
fun deleteDataInTimestamp(idList: List<Int>)
 */
/*
    public void updateTour(int id, String end_address) {
       Tour tour = getTour(id);
       tour.end_address = end_address;
       updateTour(tour);
   }
   */
//
//   fun updateNote(id: Long,title: String,detail: String){
//       val note = getId(id)
//       note.title = title
//       note.detail = detail
//       updateNote(id, title, detail)
//   }

//    @Query("UPDATE note SET title=:title,detail=:detail WHERE id LIKE :id")
//    suspend fun update1(id : Long , title : String, detail : String)

//    @Query("SELECT * FROM Tour WHERE id == :id")
//    public abstract Tour getTour(int id);




/*

@Dao
interface EmployeeDao{
    @Query("SELECT * FROM employeeTbl ORDER BY id DESC")
    fun allEmplyees(): LiveData<List<Emplyee>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student: Emplyee)
}
 */

































/*

@Dao
interface StudentDao {

    @Query("SELECT * FROM student")
    fun getAll(): List<StudentClass>

    /* @Query("SELECT * FROM student_table WHERE uid IN (:userIds)")
     fun loadAllByIds(userIds: IntArray): List<Student>*/

    @Query("SELECT * FROM student WHERE roll_no LIKE :roll LIMIT 1")
    suspend fun findByRoll(roll: Int): StudentClass

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(student: StudentClass)

    @Delete
    suspend fun delete(student: StudentClass)

    @Query("DELETE FROM student")
    suspend fun deleteAll()

    @Query("UPDATE student SET first_name=:firstName,last_name=:lastName WHERE roll_no LIKE :roll")
    suspend fun update(firstName : String, lastName : String, roll: Int)
}
 */




/*
@Dao
public interface DataDAO {

    //Insert one item
    @Insert(onConflict = IGNORE)
    void insertItem(DataItem item);

    // Delete one item
    @Delete
    void deleteItem(DataItem person);

    //Delete one item by id
    @Query("DELETE FROM dataitem WHERE id = :itemId")
    void deleteByItemId(long itemId);

    //Get all items
    @Query("SELECT * FROM DataItem")
    LiveData<List<DataItem>> getAllData();

    //Delete All
    @Query("DELETE FROM DataItem")
    void deleteAll();
}
 */






/*
@Dao
interface StudentDao {

    @Query("SELECT * FROM student")
    fun getAll(): List<StudentClass>

    /* @Query("SELECT * FROM student_table WHERE uid IN (:userIds)")
     fun loadAllByIds(userIds: IntArray): List<Student>*/

    @Query("SELECT * FROM student WHERE roll_no LIKE :roll LIMIT 1")
    suspend fun findByRoll(roll: Int): StudentClass

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(student: StudentClass)

    @Delete
    suspend fun delete(student: StudentClass)

    @Query("DELETE FROM student")
    suspend fun deleteAll()

    @Query("UPDATE student SET first_name=:firstName,last_name=:lastName WHERE roll_no LIKE :roll")
    suspend fun update(firstName : String, lastName : String, roll: Int)
}
 */