package neotunes.model.user;

import java.time.LocalDate;

public abstract class UserProducer extends User{

    private int numReproduction;
    private double timeReproduced;
    private String urlPhoto;

    public UserProducer(String name, String id, LocalDate dateOfLinking, String urlPhoto) {
        super(name, id, dateOfLinking);
        this.numReproduction = 0;
        this.timeReproduced = 0;
        this.urlPhoto = urlPhoto;
    }

    public int getNumReproduction() {
        return numReproduction;
    }

    public void setNumReproduction(int numReproduction) {
        this.numReproduction = numReproduction;
    }

    public double getTimeReproduced() {
        return timeReproduced;
    }

    public void setTimeReproduced(double timeReproduced) {
        this.timeReproduced = timeReproduced;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
}
