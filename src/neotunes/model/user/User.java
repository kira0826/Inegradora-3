package neotunes.model.user;

import neotunes.model.audio.*;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class User {

    private String name;
    private String id;
    private LocalDate dateOfLinking;

    private ArrayList<Audio> listenedAudios = new ArrayList<Audio>();

    public User(String name, String id, LocalDate dateOfLinking) {
        this.name = name;
        this.id = id;
        this.dateOfLinking = dateOfLinking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDateOfLinking() {
        return dateOfLinking;
    }

    public void setDateOfLinking(LocalDate dateOfLinking) {
        this.dateOfLinking = dateOfLinking;
    }
}
