package com.towertex.dbtest

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.towertex.dbtest.movies.model.Actor
import com.towertex.dbtest.movies.model.Movie
import com.towertex.dbtest.movies.model.Vote
import com.towertex.dbtest.movies.room.MovieDatabase
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 */
@RunWith(AndroidJUnit4::class)
class MovieDatabaseTest {

    private lateinit var appContext: Context
    private lateinit var db: MovieDatabase

    @Before
    fun setup() = runBlocking {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        db = MovieDatabase.buildDatabase(appContext)
        db.dao.insertMovies(listOf(
            Movie(
                false,
                "A movie with grown up smurfs",
                2020,
                listOf("Animation", "Adventure", "Fantasy"),
                101,
                "Avatar",
                listOf(201, 202, 203)
            ),
            Movie(
                false,
                "They will assemble",
                2010,
                listOf("Action", "Adventure", "Sci-Fi"),
                102,
                "Avengers",
                listOf(203, 204, 205)
            ),
            Movie(
                true,
                "Those Hawkmen are scary",
                1990,
                listOf("Action", "Adventure", "Fantasy"),
                103,
                "Beastmaster",
                listOf(205, 206, 201)
            )
        ))
        db.dao.insertActors(listOf(
            Actor(2000, 201, "Sam Worthington", "smurf"),
            Actor(2002, 202, "Zoe Saldana"),
            Actor(1980, 203, "Robert Downey Jr."),
            Actor(1985, 204, "Chris Evans"),
            Actor(1975, 205, "Marc Singer", "scary"),
            Actor(2004, 206, "Tanya Roberts"),
        ))
        db.dao.insertVotes(listOf(
            Vote(301, 101, 5f), //Avatar
            Vote(302, 102, 4f), //Avengers
            Vote(303, 103, 3f), //Beastmaster
            Vote(304, 101, 4f), //Avatar
            Vote(305, 101, 3f), //Avatar
            Vote(306, 102, 2f), //Avengers
        ))
    }

    @Test fun dummyTest() {
        // Context of the app under test.
        assertEquals("com.towertex.dbtest", appContext.packageName)
    }

    @Test fun getMovies() = runBlocking {
        val movies = db.dao.getMovies()
        assertEquals(3, movies.size)
        assertEquals("Avatar", movies[0].title)
        assertEquals("Avengers", movies[1].title)
        assertEquals("Beastmaster", movies[2].title)
    }

    @Test fun getActors() = runBlocking {
        val actors = db.dao.getActors()
        assertEquals(6, actors.size)
        assertEquals("Sam Worthington", actors[0].name)
        assertEquals("Zoe Saldana", actors[1].name)
        assertEquals("Robert Downey Jr.", actors[2].name)
        assertEquals("Chris Evans", actors[3].name)
        assertEquals("Marc Singer", actors[4].name)
        assertEquals("Tanya Roberts", actors[5].name)
    }

    @Test fun getVotes() = runBlocking {
        val votes = db.dao.getVotes()
        assertEquals(6, votes.size)
        assertEquals(5f, votes[0].vote)
        assertEquals(4f, votes[1].vote)
        assertEquals(3f, votes[2].vote)
        assertEquals(4f, votes[3].vote)
        assertEquals(3f, votes[4].vote)
        assertEquals(2f, votes[5].vote)
    }

    @Test fun getActorIdsForMovie() = runBlocking {
        val ids = db.dao.getActorIdsForMovie(101)
        assertEquals("[201,202,203]", ids)
    }

    @Test fun testGetMapOfActorsToMovieNamesByTheirNickName() = runBlocking {
        val map = db.dao.getMapOfActorsToMovieNamesByTheirNickName()
        assertEquals(2, map.size)
        assertEquals("Sam Worthington", map[0].actor)
    }

    @Test fun testGetMapOfActorsToMovieNamesByTheirId() = runBlocking {
        val map = db.dao.getMapOfActorsToMovieNamesByTheirId()
        assertEquals(9, map.size)
//        assertEquals("Sam Worthington", map[0].actor)
//        assertEquals("Avatar", map[0].movies)
    }

    @Test fun testGetMapOfActorsToMovieNamesByTheirId_asMap() = runBlocking {
        val map = db.dao.getMapOfActorsToMovieNamesByTheirId_asMap()
        assertEquals(3, map.size)
        assertTrue(map.keys.any { it.title == "Avatar" })
        assertEquals(map.get(map.keys.first { it.title == "Avatar" })?.map { it.name }, listOf("Sam Worthington", "Zoe Saldana", "Robert Downey Jr."))
    }

    @Test fun getMapOfMoviesToAverageVotes() = runBlocking {
        val map = db.dao.getMapOfMoviesToAverageVotes()
        assertEquals(3, map.size)
        assertEquals("Avatar", map[0].movie)
        assertEquals(4f, map[0].voteAverage)
        assertEquals("Avengers", map[1].movie)
        assertEquals(3f, map[1].voteAverage)
        assertEquals("Beastmaster", map[2].movie)
        assertEquals(3f, map[2].voteAverage)
    }
}