package neotunes.model.user;

import neotunes.model.audio.Playlist;

import java.time.LocalDate;

public class Standard extends UserConsumer implements Advertiseable{

    private Playlist [] playlists = new Playlist[20];
    private Purchase [] purchases = new Purchase[100];

    public Standard(String name, String id, LocalDate dateOfLinking) {
        super(name, id, dateOfLinking);
    }

    /**This method associate a playlist to the user selected, it's worth to say that, it firsly verifies if there is space to add
     * a new playlist.
     * @param name Storages the playlist name
     * @return Return true if the playlist was created.
     */
    @Override
    public boolean addPlaylist(String name) {
        for (int i = 0; i < playlists.length; i++){
            if (playlists[i] == null){
                playlists[i]= new Playlist(name);
                return true;
            }
        }
        return false;
    }


    public Playlist[] getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Playlist[] playlists) {
        this.playlists = playlists;
    }

    public Purchase[] getPurchases() {
        return purchases;
    }

    public void setPurchases(Purchase[] purchases) {
        this.purchases = purchases;
    }
}
