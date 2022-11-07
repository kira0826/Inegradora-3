package neotunes.model.audio;

import neotunes.model.user.*;

public abstract class Audio implements Reproducible {

    private String name;
    private String urlCover;
    private int duration;
    private int numReproduction;
    private UserProducer author;

    public Audio(String name, String urlCover, int duration, UserProducer author) {
        this.name = name;
        this.author = author;
        this.urlCover = urlCover;
        this.duration = duration;
        this.numReproduction = 0;
    }
}
