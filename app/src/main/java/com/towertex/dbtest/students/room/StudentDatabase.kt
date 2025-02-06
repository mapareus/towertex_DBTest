package com.towertex.dbtest.students.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.towertex.dbtest.students.model.Program
import com.towertex.dbtest.students.model.Scholarship
import com.towertex.dbtest.students.model.Student

@Database(
    entities = [
        Student::class,
        Program::class,
        Scholarship::class
    ],
    version = 1,
    exportSchema = false
)
abstract class StudentDatabase : RoomDatabase() {
    abstract val dao: StudentDao

    companion object {
        private const val DATABASE_NAME = "MyStudentDatabase"

        fun buildDatabase(context: Context): StudentDatabase = Room
            .databaseBuilder(context, StudentDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

        suspend fun buildDatabaseWithData(context: Context) = buildDatabase(context).apply {
            dao.insertStudents(listOf(
                Student(201, "Shivans", "Mahajan", 8.79f, "2021-09-01 09:30", "Computer Science"),
                Student(202, "Umesh", "Sharma", 8.44f, "2021-09-01 08:30", "Mathematics"),
                Student(203, "Rakesh", "Kumar", 5.6f, "2021-09-01 10:00", "Biology"),
                Student(204, "Radha", "Sharma", 9.2f, "2021-09-01 12:45", "Chemistry"),
                Student(205, "Kush", "Kumar", 7.85f, "2021-09-01 08:30", "Physics"),
                Student(206, "Prem", "Chopra", 9.56f, "2021-09-01 09:24", "History"),
                Student(207, "Pankaj", "Vats", 9.78f, "2021-09-01 02:30", "English"),
                Student(208, "Navleen", "Kaur", 7f, "2021-09-01 06:30", "Mathematics"),
            ))
            dao.insertPrograms(listOf(
                Program(201, "Computer Science", "2021-09-01"),
                Program(202, "Mathematics", "2021-09-01"),
                Program(203, "Biology", "2021-09-01"),
                Program(204, "Chemistry", "2021-09-01"),
                Program(205, "Physics", "2021-09-01"),
                Program(206, "History", "2021-09-01"),
                Program(207, "Psychology", "2021-09-01"),
                Program(208, "Mathematics", "2021-09-01"),
            ))
            dao.insertScholarships(listOf(
                Scholarship(201, 5000, "2021-10-15"),
                Scholarship(202, 4500, "2022-08-18"),
                Scholarship(203, 3000, "2022-01-25"),
                Scholarship(204, 4000, "2021-10-15"),
            ))
        }
    }
}