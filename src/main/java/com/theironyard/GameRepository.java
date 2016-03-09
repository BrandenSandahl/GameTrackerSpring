package com.theironyard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by branden on 3/8/16 at 11:55.
 */

public interface GameRepository extends CrudRepository<Game, Integer> {
    List<Game> findByGenre(String genre);  //this is a sorting method. takes type to return, has to be named finByX, and search term
    List<Game> findByGenreAndReleaseYear(String genre, int releaseYear);
    List<Game> findByGenreAndReleaseYearIsGreaterThanEqual(String genre, int minYear);

    Game findFirstByGenre(String genre);
    int countByGenre(String genre);
    List<Game> findByGenreOrderByNameAsc(String genre);

    @Query("SELECT g FROM Game g WHERE g.platform LIKE ?1%")
    List<Game> findByPlatformStartsWith(String platform);

}
