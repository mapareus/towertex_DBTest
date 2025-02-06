package com.towertex.dbtest.students.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.towertex.dbtest.students.model.Program
import com.towertex.dbtest.students.model.Scholarship
import com.towertex.dbtest.students.model.Student

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudents(items: List<Student>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrograms(items: List<Program>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScholarships(items: List<Scholarship>)

    @Query("SELECT upper(FIRST_NAME) as STUDENT_NAME FROM STUDENTS")
    suspend fun getFirstNamesOfStudentsInUppercase(): List<String>

    @Query("SELECT distinct MAJOR FROM STUDENTS")
    suspend fun getDistinctMajors(): List<String>
    @Query("SELECT MAJOR FROM STUDENTS GROUP BY(MAJOR)")
    suspend fun getDistinctMajorsUsingGroupBy(): List<String>

    @Query("SELECT substring(FIRST_NAME,1,3) FROM STUDENTS")
    suspend fun getFirst3LettersOfStudentNames(): List<String>

    @Query("SELECT instr(FIRST_NAME, 'a') FROM STUDENTS WHERE LAST_NAME = :lastName")
    suspend fun getFirstOccurrenceOfALetterInFirstNameForGivenLastName(lastName: String): Int
}