package com.towertex.dbtest

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.towertex.dbtest.students.room.StudentDatabase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StudentDatabaseTest {

    private lateinit var appContext: Context
    private lateinit var db: StudentDatabase

    @Before
    fun setup() = runBlocking {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        db = StudentDatabase.buildDatabaseWithData(appContext)
    }

    @Test
    fun dummyTest() {
        // Context of the app under test.
        assertEquals("com.towertex.dbtest", appContext.packageName)
    }

    @Test
    fun getFirstNamesOfStudentsInUppercaseTest() = runBlocking {
        val firstNames = db.dao.getFirstNamesOfStudentsInUppercase()
        assertEquals(8, firstNames.size)
        assertEquals("SHIVANS", firstNames[0])
        assertEquals("UMESH", firstNames[1])
        assertEquals("RAKESH", firstNames[2])
        assertEquals("RADHA", firstNames[3])
        assertEquals("KUSH", firstNames[4])
        assertEquals("PREM", firstNames[5])
        assertEquals("PANKAJ", firstNames[6])
        assertEquals("NAVLEEN", firstNames[7])
    }

    @Test
    fun getDistinctMajorsTest() = runBlocking {
        val majors = db.dao.getDistinctMajors()
        assertEquals(7, majors.size)
        val majorsUsingGroupBy = db.dao.getDistinctMajorsUsingGroupBy()
        assertEquals(7, majorsUsingGroupBy.size)
        assertTrue(majors.containsAll(majorsUsingGroupBy))
    }

    @Test
    fun getFirst3LettersOfStudentNamesTest() = runBlocking {
        val first3Letters = db.dao.getFirst3LettersOfStudentNames()
        assertEquals(8, first3Letters.size)
        assertEquals("Shi", first3Letters[0])
        assertEquals("Ume", first3Letters[1])
        assertEquals("Rak", first3Letters[2])
        assertEquals("Rad", first3Letters[3])
        assertEquals("Kus", first3Letters[4])
        assertEquals("Pre", first3Letters[5])
        assertEquals("Pan", first3Letters[6])
        assertEquals("Nav", first3Letters[7])
    }

    @Test
    fun getFirstOccurrenceOfALetterInFirstNameForGivenLastNameTest() = runBlocking {
        val firstOccurrence = db.dao.getFirstOccurrenceOfALetterInFirstNameForGivenLastName("Kumar")
        assertEquals(2, firstOccurrence)
    }
}