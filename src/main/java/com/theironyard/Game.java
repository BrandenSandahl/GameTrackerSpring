package com.theironyard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by branden on 3/8/16 at 11:35.
 */
@Entity //this tells Class and DB to sync up
public class Game {
    @Id //this is an IDENTITY(SERIAL)
    @GeneratedValue //this sets up auto-increment
            int id;


    String name;
    String platform;
    int releaseYear;

    public Game() {
    }

    public Game(String name, String platform, int releaseYear) {
        this.name = name;
        this.platform = platform;
        this.releaseYear = releaseYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

}
