package neotunes.model.user;

import neotunes.model.audio.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Premium extends UserConsumer {

    private ArrayList<Playlist> playlists = new ArrayList<>();
    private ArrayList<Purchase> purchases = new ArrayList<>();

    public Premium(String name, String id, LocalDate dateOfLinking) {
        super(name, id, dateOfLinking);
        this.reproducedAudios = new ArrayList<>();

    }

    /**This method associates the sellable object to the user purchase list, and increment the number of sales of the Sellable object.
     * @param sellable Storages the Sellable object that will be bought.
     * @return Returns true when the purchase was done, otherwise, returns false.
     */
    public boolean purchaseSellable(Sellable sellable){
        sellable.incrementNumSales();
        LocalDate localDate  = LocalDate.now();
        Purchase purchase = new Purchase(localDate,sellable);
        getPurchases().add(purchase);
        return true;
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
    /**This method identifies the playlist selected in order to delete the audio choose.
     * @param audio Storages the audio position.
     * @param playlistPosition Storages the playlist position that will be used to delete the audio selected.
     * @return Returns true when the process was successfully done.
     */

    @Override
    public boolean deleteAudio(int playlistPosition, int audio) {
        return getPlaylists().get(playlistPosition).deleteAudio(audio);
    }

    /**This method is used to identify the playlist that will be used to generate the audios report.
     * @param playlistIndex Storages the playlist index position.
     * @return A String with the information of each audio stored in the playlist identified.
     */
    @Override
    public String concatenateAudiosFromPlaylist(int playlistIndex) {
        return getPlaylists().get(playlistIndex).displayAudios();
    }
    /**This method is used to identify the playlist that will be checked to verify if there is at least one audio.
     * @param playlistIndex Storages the playlist index position.
     * @return Returns true if there is at least one aduio in the selected playlist, otherwise, return false.
     */
    @Override
    public boolean isThereAudiosInPlaylist(int playlistIndex) {
        return getPlaylists().get(playlistIndex).isThereAudios();
    }

    /**This method is used to identify the playlist that will be shared.
     * @param playlistIndex Storages the playlist index position.
     * @return The code that will be used to share the playlist.
     */
    @Override
    public String sharePlaylist(int playlistIndex) {
        return getPlaylists().get(playlistIndex).sharePlaylist();
    }

    //Reproduce

    /**This method increment the audio stats and update the consumer listened audios.
     * @param audio Storages the audio that will be used to compared and increment the correspondent stats.
     * @return A string that simulates the reproduction of an audio.
     */
    @Override
    public String reproduceAudio(Audio audio) {
        String message = "";

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
    @Override
    public boolean wasReproduced(Audio audio) {
        for (int i = 0; i < getReproducedAudios().size(); i++) {
            if (getReproducedAudios().get(i).equals(audio))return true;
        }
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
