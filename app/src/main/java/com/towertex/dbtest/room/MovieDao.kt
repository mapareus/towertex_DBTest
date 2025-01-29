package com.towertex.dbtest.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.towertex.dbtest.model.Actor
import com.towertex.dbtest.model.Movie
import com.towertex.dbtest.model.Vote

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<Movie>

    @Query("DELETE FROM movies WHERE id IN (:ids)")
    suspend fun deleteItems(ids: List<Int>)

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(items: List<Movie>)

    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun getMovie(id: Int): Movie

////////////////////////////////////////////////////////////

    @Query("SELECT * FROM actors")
    suspend fun getActors(): List<Actor>

    @Query("DELETE FROM actors WHERE id IN (:ids)")
    suspend fun deleteActors(ids: List<Int>)

    @Query("DELETE FROM actors")
    suspend fun deleteAllActors()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActors(items: List<Actor>)

    @Query("SELECT * FROM actors WHERE id = :id")
    suspend fun getActor(id: Int): Actor

////////////////////////////////////////////////////////////

    @Query("SELECT * FROM votes")
    suspend fun getVotes(): List<Vote>

    @Query("DELETE FROM votes WHERE id IN (:ids)")
    suspend fun deleteVotes(ids: List<Int>)

    @Query("DELETE FROM votes")
    suspend fun deleteAllVotes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVotes(items: List<Vote>)

    @Query("SELECT * FROM votes WHERE id = :id")
    suspend fun getVote(id: Int): Vote

////////////////////////////////////////////////////////////

    @Query("SELECT actor_ids FROM movies WHERE id = :movieId")
    suspend fun getActorIdsForMovie(movieId: Int): String

    @Query("SELECT movies.title AS actor, actors.name AS movies FROM movies JOIN actors ON movies.actor_ids LIKE '%' || CAST(actors.id AS CHAR) || '%'")
    suspend fun getMapOfActorsToMovieNamesByTheirId(): List<ActorNameWithMoviesNames>

    @Query("SELECT * FROM movies JOIN actors ON movies.actor_ids LIKE '%' || CAST(actors.id AS CHAR) || '%'")
    suspend fun getMapOfActorsToMovieNamesByTheirId_asMap(): Map<Movie, List<Actor>>

    @Query("SELECT actors.name AS actor, movies.title AS movies FROM movies JOIN actors ON movies.overview LIKE '%' || actors.nick_name || '%'")
    suspend fun getMapOfActorsToMovieNamesByTheirNickName(): List<ActorNameWithMoviesNames>

    class ActorNameWithMoviesNames(
        val actor: String,
        val movies: String
    )

    @Query("SELECT movies.title AS movie, AVG(votes.vote) AS voteAverage FROM movies JOIN votes ON movies.id = votes.movie_id GROUP BY movies.id")
    suspend fun getMapOfMoviesToAverageVotes(): List<MovieNameWithVoteAverage>

    class MovieNameWithVoteAverage(
        val movie: String,
        val voteAverage: Float
    )
}