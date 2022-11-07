package neotunes.model.user;

import neotunes.model.audio.*;


import java.time.LocalDate;

public class Standard extends UserConsumer implements Advertiseable{

    private Playlist [] playlists = new Playlist[20];
    private Purchase [] purchases = new Purchase[100];

    public Standard(String name, String id, LocalDate dateOfLinking) {
        super(name, id, dateOfLinking);
    }

    /**This method associate a playlist to the user selected, it's worth to say that, it firstly verifies if there is space to add
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

    /**This method chechks if there is at least one playlist associate to the selected user.
     * @return Returns true if there is at least one playlist, otherwise, return false.
     */
    @Override
    public boolean hasPlaylist() {
        for (int i  = 0; i < playlists.length;i++){
            if (getPlaylists()[i] != null) return true;
        }
        return false;
    }

    /**This method concatenate the name and position of each playlist registered in the Standard user.
     * @return A string with the name and position of each playlist registered in the Standard user.
     */
    @Override
    public String concatenatePlaylistInfo() {
        String message = "listas de " + this.getName() +"\n";
        for (int i = 0; i< getPlaylists().length;i++){
            if (getPlaylists()[i] == null) continue;
            message += i + ".)" + getPlaylists()[i].getName() +"." +"\n";
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
        getPlaylists()[playlistPosition].addAudio(audio);
        return true;
    }

    @Override
    public boolean deleteAudio(int playlistPosition, Audio audio) {
        getPlaylists()[playlistPosition].deleteAudio(audio);
        return true;
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
