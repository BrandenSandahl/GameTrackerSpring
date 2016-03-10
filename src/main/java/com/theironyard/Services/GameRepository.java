package com.theironyard.Services;

import com.theironyard.Entities.Game;
import com.theironyard.Entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by branden on 3/8/16 at 11:55.
 */

public interface GameRepository extends CrudRepository<Game, Integer> {
    List<Game> findByUser(User user);
    List<Game> findByUserAndGenre(User user, String genre);  //this is a sorting method. takes type to return, has to be named finByX, and search term
    List<Game> findByUserAndGenreAndReleaseYear(User user, String genre, int releaseYear);
    List<Game> findByUserAndGenreAndReleaseYearIsGreaterThanEqual(User user, String genre, int minYear);

    Game findFirstByGenre(String genre);
    int countByGenre(String genre);
    List<Game> findByGenreOrderByNameAsc(String genre);

    @Query("SELECT g FROM Game g WHERE g.platform LIKE ?1%")
    List<Game> findByPlatformStartsWith(String platform);

}
