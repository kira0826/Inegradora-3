package neotunes.model.audio;

import neotunes.model.user.*;

import java.util.Objects;

public abstract class Audio implements Reproducible {

    private String name;
    private int id;
    private String urlCover;
    private int duration;
    private int numReproduction;
    private UserProducer author;

    private static int audiosCounter = 0;

    public Audio(String name, String urlCover, int duration, UserProducer author) {
        this.name = name;
        this.author = author;
        this.urlCover = urlCover;
        this.duration = duration;
        this.numReproduction = 0;
        audiosCounter +=1;
        this.id = audiosCounter;
    }



    /**This method convert the duration of the audio into a format of minutes. Format: 12:02
     * @return A string with the duration in minutes format.
     */
    public  String durationFormat(){
        String message ="";
        if (duration <60){
            message = String.valueOf(duration);
        }else {
            message = String.valueOf(duration/60) + ":";
            message += (String.valueOf(duration%60).length() < 2)? "0"+ String.valueOf(duration%60): String.valueOf(duration%60) ;
        }
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Audio audio = (Audio) o;
        return this.getId() == audio.getId();

    }

    public abstract Audio copy();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlCover() {
        return urlCover;
    }

    public void setUrlCover(String urlCover) {
        this.urlCover = urlCover;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getNumReproduction() {
        return numReproduction;
    }

    public void setNumReproduction(int numReproduction) {
        this.numReproduction = numReproduction;
    }

    public UserProducer getAuthor() {
        return author;
    }

    public void setAuthor(UserProducer author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getAudiosCounter() {
        return audiosCounter;
    }

    public static void setAudiosCounter(int audiosCounter) {
        Audio.audiosCounter = audiosCounter;
    }
}

