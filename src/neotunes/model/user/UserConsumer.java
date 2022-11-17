package neotunes.model.user;

import neotunes.model.audio.*;
import java.time.LocalDate;
import java.util.*;

public abstract class UserConsumer extends User implements Reproduce, PlaylistStaff{

    int reproducedSongs;
    ArrayList<Audio> reproducedAudios;

    /**This method identifies the favorite song genre of a specific user.
     * @return If there is at least one song reproduced, returns a string with the information of the favorite genre, otherwise
     * return a string indicating that there are not songs.
     */
    public String favoriteUserSongGenre(){
        if (this.isThereSongReproduced()){
            int counter [] = new int[Genre.values().length];
            //Fill process
            for (int i = 0; i < counter.length; i++) {
                counter[i] = 0;
            }
            // Count process
            for (int  i  =0 ; i < Genre.values().length;i++){
                for (int j = 0; j< getReproducedAudios().size(); j++) {
                    if (getReproducedAudios().get(j) instanceof Song){
                        if (((Song) getReproducedAudios().get(j)).getGenre().equals(Genre.values()[i])){
                            counter[i] = counter[i] + getReproducedAudios().get(j).getNumReproduction();
                        }
                    }
                }
            }
            // Favorite process
            int favorite= counter[0] , position = 0;

            for (int i = 0; i < counter.length; i++) {
                if (favorite < counter[i]){
                    position = i;
                    favorite = counter[i];
                }
            }
            // Message process
            String message = "Género favorito:  " + Genre.values()[position]
                    + " | Número de reproducciones: " + counter[position];

            return message;
        }
        return "No hay canciones reproducidas.";
    }

    /**
     * This method verifies if there is podcast already reproduced.
     * @return Returns true if exist at least one podcasts reproduced, otherwise, return false.
     */
    public boolean isTherePodcastReproduced(){
        for (int i = 0; i < getReproducedAudios().size(); i++) {
            if (getReproducedAudios().get(i) instanceof Song) return true;
        }
        return false;
    }
    /**
     * This method verifies if there is Song object already reproduced.
     * @return Returns true if exist at least one Song object reproduced, otherwise, return false.
     */
    public boolean isThereSongReproduced(){
        for (int i = 0; i < getReproducedAudios().size(); i++) {
            if (getReproducedAudios().get(i) instanceof Song) return true;
        }
        return false;
    }
    public UserConsumer(String name, String id, LocalDate dateOfLinking) {
        super(name, id, dateOfLinking);
    }

    public abstract boolean purchaseSellable(Sellable sellable);
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
