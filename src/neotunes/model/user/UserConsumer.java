package neotunes.model.user;

import neotunes.model.audio.Audio;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class UserConsumer extends User implements Reproduce, PlaylistStaff{

    int reproducedSongs;
    ArrayList<Audio> reproducedAudios;
    public UserConsumer(String name, String id, LocalDate dateOfLinking) {
        super(name, id, dateOfLinking);
    }


    public int getReproducedSongs() {
        return reproducedSongs;
    }

    public void setReproducedSongs(int reproducedSongs) {
        this.reproducedSongs = reproducedSongs;
    }

    public ArrayList<Audio> getReproducedAudios() {
        return reproducedAudios;
    }

    public void setReproducedAudios(ArrayList<Audio> reproducedAudios) {
        this.reproducedAudios = reproducedAudios;
    }
}
