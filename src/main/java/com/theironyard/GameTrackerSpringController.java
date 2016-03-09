package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by branden on 3/8/16 at 11:33.
 */
@Controller
public class GameTrackerSpringController {
    @Autowired
    GameRepository games; //creates a repository object (see class)

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String genre, Integer releaseYear, String platform) {
        if (genre == null && releaseYear == null && platform == null) {
            model.addAttribute("games", games.findAll());
        }
        else if (genre != null && releaseYear != null) {
            model.addAttribute("games", games.findByGenreAndReleaseYearIsGreaterThanEqual(genre, releaseYear));
            //model.addAttribute("games", games.findByGenreAndReleaseYear(genre, releaseYear));
        }
        else if (genre != null){
            model.addAttribute("games", games.findByGenre(genre));
        }
        else if (platform != null) {
            model.addAttribute("games", games.findByPlatformStartsWith(platform));
        }
        return "home";
    }

    @RequestMapping(path = "/add-game", method = RequestMethod.POST)
    public String addGame(String gameName, String gamePlatform, String gameGenre, int gameYear) {
        Game g = new Game(gameName, gamePlatform, gameGenre, gameYear); //read in input, create a Game object
        games.save(g);
        return "redirect:/";
    }

    @RequestMapping(path = "/view-game", method = RequestMethod.GET)
    public String viewGame(int id) {
        Game g = games.findOne(id);

        return "home";
    }


}