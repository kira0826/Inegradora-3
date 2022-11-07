package neotunes.model.user;

import neotunes.model.audio.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Premium extends UserConsumer {

    private ArrayList<Playlist> playlists = new ArrayList<>();
    private ArrayList<Purchase> purchases = new ArrayList<>();

    public Premium(String name, String id, LocalDate dateOfLinking) {
        super(name, id, dateOfLinking);
    }

    /**This method associate a playlist to this user.
     * @param name Storages the playlist name
     * @return Return true if the playlist was created.
     */
    @Override
    public boolean addPlaylist(String name) {
        Playlist playlist = new Playlist(name);
        playlists.add(playlist);
        return true;
    }

    /**This method chechks if there is at least one playlist associate to the selected user.
     * @return Returns true if there is at least one playlist, otherwise, return false.
     */
    @Override
    public boolean hasPlaylist() {
        if (playlists.size()>0) return true;
        return false;
    }
    /**This method concatenate the name and position of each playlist registered in the Premium user.
     * @return A string with the name and position of each playlist registered in the Premium user.
     */

    @Override
    public String concatenatePlaylistInfo() {
        String message = "Playlist de " + this.getName() +": \n";
        for (int i = 0; i < playlists.size();i++){
            message += i+".)" + getPlaylists().get(i).getName() +". \n";
        }
        return message;
    }

    /**This method identifies the playlist selected in order to add the audio received.
     * @param audio Storages the audio to add.
     * @param playlistPosition Storages the playlist position that will be used to add the audio selected.
     * @return Returns true when the process was successfully done.
     */

    @Override
    public boolean addAudio(Audio audio, int playlistPosition) {
        getPlaylists().get(playlistPosition).addAudio(audio);
        return true;
    }

    @Override
    public boolean deleteAudio(int playlistPosition, Audio audio) {
        return false;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(ArrayList<Purchase> purchases) {
        this.purchases = purchases;
    }
}
