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


}
