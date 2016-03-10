package com.theironyard.controllers;

import com.theironyard.Entities.Game;
import com.theironyard.Services.GameRepository;
import com.theironyard.Entities.User;
import com.theironyard.Services.UserRepository;
import com.theironyard.Utils.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

/**
 * Created by branden on 3/8/16 at 11:33.
 */
@Controller
public class GameTrackerSpringController {
    @Autowired
    GameRepository games; //creates a repository object (see class)
    @Autowired
    UserRepository users;

    @PostConstruct
    public void init() {
        System.out.println("Start it up!");
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model, String genre, Integer releaseYear, String platform) {

        String userName = (String) session.getAttribute("userName");
        User user = users.findFirstByName(userName);
        if (userName != null) {
            model.addAttribute("user", user);
        }

        if (platform != null) {
            model.addAttribute("games", games.findByPlatformStartsWith(platform));
        }
        if (genre != null && releaseYear != null) {
            model.addAttribute("games", games.findByUserAndGenreAndReleaseYearIsGreaterThanEqual(user, genre, releaseYear));
        }
        else if (genre != null) {
            model.addAttribute("games", games.findByUserAndGenre(user, genre));
        }
        else {
            model.addAttribute("games", games.findByUser(user));
        }

    return "home";

    }

    @RequestMapping(path = "/add-game", method = RequestMethod.POST)
    public String addGame(HttpSession session, String gameName, String gamePlatform, String gameGenre, int gameYear) {
        Game g = new Game(gameName, gamePlatform, gameGenre, gameYear); //read in input, create a Game object

        String userName = (String) session.getAttribute("userName");
        User user = users.findFirstByName(userName);

        g.setUser(user);

        games.save(g);
        return "redirect:/";
    }

//    @RequestMapping(path = "/view-game", method = RequestMethod.GET)
//    public String viewGame(int id) {
//        Game g = games.findOne(id);
//
//        return "home";
//    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName, String password) throws Exception {
        User user = users.findFirstByName(userName);

        if (user == null) {
            user = new User(userName, PasswordStorage.createHash(password));
            users.save(user);
        } else if (!PasswordStorage.verifyPassword(password, user.getPasswordHash())){
            throw new Exception("Incorrect password");

        }
        session.setAttribute("userName", userName);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


}