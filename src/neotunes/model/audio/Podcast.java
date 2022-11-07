package neotunes.model.audio;

import neotunes.model.user.UserProducer;

public class Podcast extends Audio{

    private String description;
    private Category category;

    public Podcast(String name, String urlCover, int duration,  UserProducer author, String description, Category category) {
        super(name, urlCover, duration, author);
        this.description = description;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
