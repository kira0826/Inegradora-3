package neotunes.model.user;

import neotunes.model.audio.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Standard extends UserConsumer implements Advertiseable{

    private Playlist [] playlists = new Playlist[20];
    private Purchase [] purchases = new Purchase[100];

    public Standard(String name, String id, LocalDate dateOfLinking) {
        super(name, id, dateOfLinking);
        this.reproducedSongs = 0;
        this.reproducedAudios = new ArrayList<>();
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
        return getPlaylists()[playlistPosition].addAudio(audio);
    }
    /**This method identifies the playlist selected in order to delete the audio choose.
     * @param audio Storages the audio position.
     * @param playlistPosition Storages the playlist position that will be used to delete the audio selected.
     * @return Returns true when the process was successfully done.
     */
    @Override
    public boolean deleteAudio(int playlistPosition, int audio) {
        return getPlaylists()[playlistPosition].deleteAudio(audio);
    }
    /**This method is used to identify the playlist that will be used to generate the audios report.
     * @param playlistIndex Storages the playlist index position.
     * @return A String with the information of each audio stored in the playlist identified.
     */
    @Override
    public String concatenateAudiosFromPlaylist(int playlistIndex) {
        return getPlaylists()[playlistIndex].displayAudios();
    }
    /**This method checks if there is at least one audio associate to the selected playlist.
     * @return Returns true if there is at least one audio, otherwise, return false.
     */
    public boolean isThereAudiosInPlaylist(int playlistIndex) {
        return getPlaylists()[playlistIndex].isThereAudios();
    }


    /**This method is used to identify the playlist that will be shared.
     * @param playlistIndex Storages the playlist index position.
     * @return The code that will be used to share the playlist.
     */
    @Override
    public String sharePlaylist(int playlistIndex) {
        return getPlaylists()[playlistIndex].sharePlaylist();
    }

    // Reproduce

    /**This method select randomly an advertisement that will be displayed.
     * @return A String with the information of the advertisement.
     */
    @Override
    public String selectAdvertisement() {
        Random random = new Random();
        return Advertiseable.advertisements.get(random.nextInt(advertisements.size()));
    }
    /**This method increment the audio stats and update the consumer listened audios.
     * @param audio Storages the audio that will be used to compared and increment the correspondent stats.
     * @return A string that simulates the reproduction of an audio.
     */
    @Override
    public String reproduceAudio(Audio audio) {
        if (audio instanceof Song) this.setReproducedSongs(this.getReproducedSongs()+1);

        String message = "";
        if (this.getReproducedSongs() ==2) {
            message += "Anuncio: " + selectAdvertisement() + "\n";
            this.setReproducedSongs(0);
        }

        if (wasReproduced(audio)){
            for (int i = 0; i < getReproducedAudios().size(); i++) {
                if (getReproducedAudios().get(i).equals(audio)) {
                    getReproducedAudios().get(i).setNumReproduction(getReproducedAudios().get(i).getNumReproduction()+1);
                }
            }
        }else{
            Audio audio1 = audio.copy();
            audio1.setNumReproduction(1);
            audio1.setId(audio.getId());
            getReproducedAudios().add(audio1);
        }
        return message;
    }

    /**Verify if a specific audio was reproduced before.
     * @param audio Storages the audio that will be used to verify.
     * @return Returns true if was reproduced before, otherwise,  returns false
     */
    public boolean wasReproduced(Audio audio){
        for (int i = 0; i < getReproducedAudios().size(); i++) {
            if (getReproducedAudios().get(i).equals(audio))return true;
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
