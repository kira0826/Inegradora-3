package neotunes.model;

import neotunes.model.audio.*;
import neotunes.model.user.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class NeotunesController {

    private ArrayList<Audio> audios = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();



    //User registration
    /**
     * This method verifies if the id given by the user is unique.
     *
     * @param id This variable storages the id that will be checked in order to confirm if that one is a unique id in the system.
     * @return If the id is unique return true, otherwise, it will return false.
     */
    public boolean isIdUnique(String id) {

        for (int i = 0; i < getUsers().size(); i++) {
            if (getUsers().get(i) != null && getUsers().get(i).getId().equals(id)) return false;
        }
        return true;
    }

    /**
     * This method verifies if the nickname given by the user is unique.
     *
     * @param nickname This variable storages the nickname that will be checked in order to confirm if that one is a unique nickname in the system
     * @return If the nickname is unique return true, otherwise, it will return false.
     */
    public boolean isNicknameUnique(String nickname) {

        for (int i = 0; i < getUsers().size(); i++) {
            if (getUsers().get(i) != null && getUsers().get(i).getName().equals(nickname)) return false;
        }
        return true;
    }



    //Registration

        //Register Users
    /**
     * This method creates and save an Artist object in the program .
     *
     * @param name          Storages the name of the artist that will be created.
     * @param id            Storages the id of the artist that will be created.
     * @param dateOfLinking Storages the date of linking of the artist that will be created.
     * @param urlPhoto      Storages the photo url of the artist that will be created.
     * @return A string indicating that the user was successfully created
     */
    public String registerArtist(String name, String id, String dateOfLinking, String urlPhoto) {

        LocalDate localDate = LocalDate.parse(dateOfLinking);
        Artist artist = new Artist(name, id, localDate, urlPhoto);
        getUsers().add(artist);
        return "Usuario registrado";
    }

    /**
     * This method creates and save a ContentCreator object in the program .
     *
     * @param name          Storages the name of the user that will be created.
     * @param id            Storages the id of the user that will be created.
     * @param dateOfLinking Storages the date of linking of the user that will be created.
     * @param urlPhoto      Storages the photo url of the user that will be created.
     * @return A string indicating that the user was successfully created
     */
    public String registerContentCreator(String name, String id, String dateOfLinking, String urlPhoto) {

        LocalDate localDate = LocalDate.parse(dateOfLinking);
        ContentCreator contentCreator = new ContentCreator(name, id, localDate, urlPhoto);
        getUsers().add(contentCreator);
        return "Usuario registrado";
    }

    /**
     * This method creates and save a Standard object in the program.
     *
     * @param name          Storages the name of the user that will be created.
     * @param id            Storages the id of the user that will be created.
     * @param dateOfLinking Storages the date of linking of the user that will be created.
     * @return A string indicating that the user was successfully created
     */
    public String registerStandardUser(String name, String id, String dateOfLinking) {

        LocalDate localDate = LocalDate.parse(dateOfLinking);
        Standard standard = new Standard(name, id, localDate);
        getUsers().add(standard);
        return "Usuario registrado";
    }

    /**
     * This method creates and save a Premium object in the program.
     *
     * @param name          Storages the name of the user that will be created.
     * @param id            Storages the id of the user that will be created.
     * @param dateOfLinking Storages the date of linking of the user that will be created.
     * @return A string indicating that the user was successfully created
     */
    public String registerPremiumUser(String name, String id, String dateOfLinking) {

        LocalDate localDate = LocalDate.parse(dateOfLinking);
        Premium premium = new Premium(name, id, localDate);
        getUsers().add(premium);
        return "Usuario registrado";
    }

    /**This method receives the necessary information in order to create a Song object.
     * @param name Storages the name of the song.
     * @param urlCover Storages the url of the cover photo.
     * @param duration Storages the duration of the song.
     * @param author Storages the position of the song author.
     * @param price Storages the price of the song.
     * @param album Storages the name of the album related with the song.
     * @param genrePosition Storages the position of the genre related with the song.
     * @return Return  a string indicating that the song was created.
     */

        //Register audios
    public String registerSong (String name, String urlCover, String duration, int author, double price, String album, int genrePosition) {

        Song song = new Song(name,urlCover,convertToSeconds(duration), (Artist) getUsers().get(author),price,album,Genre.values()[genrePosition]);
        getAudios().add(song);
        return "Canción registrada";
    }

    /**This method receives the necessary information in order to create a Podcast object.
     * @param name Storages the name of the podcast.
     * @param urlCover Storages the url of the cover photo.
     * @param duration Storages the duration of the podcast.
     * @param author Storages the position of the podcast author.
     * @param description Storages the name of the album related with the podcast.
     * @param categoryPosition Storages the position of the category related with the podcast.
     * @return Return a string indicating that the podcast was created.
     */
    public String registerPodcast(String name, String urlCover, String duration, int author, String description, int categoryPosition){

        Podcast podcast = new Podcast(name,urlCover,convertToSeconds(duration),(ContentCreator)getUsers().get(author),description,Category.values()[categoryPosition]);
        getAudios().add(podcast);
        return "Podcast Registrado";
    }

        //Playlist Staff

    /**This method identifies the UserConsumer that will be uses to storages the new playlist that will be created.
     * @param name Storages the playlist name
     * @param userPosition Storages the user consumer postion
     * @return A String indicating if the playlist was added or not.
     */

    public String createPlaylist(String name, int userPosition){

        if (((UserConsumer) getUsers().get(userPosition)).addPlaylist(name)){
            return "Playlist agregada";
        }
        return "No se pudo agregar la playlist";
    }

    /**
     * This method is used to identify the user that want to share a playlist.
     * @param userIndex Storages the user index position.
     * @param playlist Storages the playlist index position
     * @return The code that will be used to share the playlist.
     */
    public String sharePlaylist (int userIndex, int playlist){
        return ((UserConsumer)getUsers().get(userIndex)).sharePlaylist(playlist);
    }

    /**This method is used to identify the user and audio selected in order to add an audio to a playlist of the user identified.
     * @param audioIndex Storages the index position of the audio that will be added.
     * @param userIndex Storages the user index positin of the user consumer that has the playlist.
     * @param playlistIndex Storages the index position of the user playlist that will be used to save the audio.
     * @return Return true if the audio was suscesfully added,  otherwise,  returns false.
     */
    public boolean addAudio(int audioIndex, int userIndex, int playlistIndex){
        return ((UserConsumer)getUsers().get(userIndex)).addAudio(getAudios().get(audioIndex),playlistIndex );
    }
    /**This method is used to identify the user selected in order to delete an audio to a playlist of the user identified.
     * @param audioIndex Storages the index position of the audio that will be deleted.
     * @param userIndex Storages the user index positin of the user consumer that has the playlist.
     * @param playlistIndex Storages the index position of the user playlist that will be used to delete the audio.
     * @return Return true if the audio was suscesfully deleted,  otherwise,  returns false.
     */
    public boolean deleteAudio(int audioIndex, int userIndex, int playlistIndex){
        return ((UserConsumer)getUsers().get(userIndex)).deleteAudio(playlistIndex,audioIndex);
    }

    // Isthere

        // User

    /**This method verifies if there are Artist already registered.
     * @return Returns true if exist at least one Artist, otherwise, return false.
     */
    public boolean isThereArtist(){
        for (int i = 0; i< getUsers().size();i++){
            if (getUsers().get(i) instanceof Artist) return true;
        }
        return false;
    }

    /**This method verifies if there are ContentsCreators already registered.
     * @return Returns true if exist at least one Artist, otherwise, return false.
     */
    public boolean isThereContentCreators(){
        for (int i = 0; i< getUsers().size();i++){
            if (getUsers().get(i) instanceof ContentCreator) return true;
        }
        return false;
    }

    /**This method verifies if there are UserConsumers already registered.
     * @return Returns true if exist at least one UserConsumer, otherwise, return false.
     */
    public boolean isThereConsumers(){

        for(int i = 0; i< users.size();i++){
            if (users.get(i) instanceof UserConsumer) return true;
        }
        return false;
    }

            // Audios
    /**This method is used to identify the user that will be used to check if there is audios in a specific playlist of him.
     * @return Returns true if exist at least one Audio in the selected playlist, otherwise, return false.
     */
    public boolean isThereAudiosInPlaylist(int userPosition, int playlistPosition){
        return ((UserConsumer)getUsers().get(userPosition)).isThereAudiosInPlaylist(playlistPosition);
    }

    /**This method verifies if there are Audios already registered.
     * @return Returns true if exist at least one Audio, otherwise, return false.
     */
    public boolean isThereAudios(){
        if (getAudios().size()>0) return true;
        return false;
    }
    /**This method verifies if there are playlist already registered in one specific user consumer.
     * @return Returns true if exist at least one playlist, otherwise, return false.
     */

    public boolean isTherePlaylist (int userConsumerPosition){
        return ((UserConsumer)getUsers().get(userConsumerPosition)).hasPlaylist();
    }


    // Concatenate Information

    /**
     * This method gets the genres saved in the program.
     *
     * @return A String with the Genres available.
     */
    public String getGenres() {
        return Genre.concatenateLiterals();
    }

    /**
     * This method concatenates the Artist users with their correspondent position.
     *
     * @return A string with the Artist name and position.
     */
    public String getArtistInfo() {
        String message = "Artistas: " + "\n";

        for (int i = 0; i < getUsers().size(); i++) {
            if (getUsers().get(i) instanceof Artist) message += i + ") " + getUsers().get(i).getName() + "." + "\n";
        }
        return message;
    }

    /**This method concatenates the ContentCreaator users with their correspondent position.
     * @return A string with the ContentCreator name and position.
     */
    public String getContentCreatorInfo() {
        String message = "Creadores de contenido: " + "\n";

        for (int i = 0; i < getUsers().size(); i++) {
            if (getUsers().get(i) instanceof ContentCreator)
                message += i + ") " + getUsers().get(i).getName() + "." + "\n";
        }
        return message;
    }

    /**
     * This method gets the categories saved in the program.
     * @return A String with the categories available.
     */
    public String getCategories(){
        return Category.concatenateLiterals();
    }

    /**This method concatenates the Audios with their correspondent position.
     * @return A string with the Audios names, duration and position.
     */
    public String concatenateAudiosInfo (){
        String message = "Audios info: \n";
        for (int i = 0; i < getAudios().size(); i++) {
            message += i+".)" + getAudios().get(i).getName() +" - Duración : " + getAudios().get(i).durationFormat()+". \n";
        }
        return message;
    }

    /**This method concatenates the UserConsumers with their correspondent position.
     * @return A string with the UserConsumers names and position.
     */
    public String getUserConsumerInfo(){

        String message = "Consumidores: " + "\n";

        for (int i = 0; i < getUsers().size(); i++) {
            if (getUsers().get(i) instanceof UserConsumer)
                message += i + ") " + getUsers().get(i).getName() + "." + "\n";
        }
        return message;
    }

    /**This method gets the name and positon of each playlist of a specific user.
     * @param userConsumerPosition Storages the index position of the user selected.
     * @return A string with the information of each playlist found.
     */
    public String getUserPlaylistInfo(int userConsumerPosition){

        return  ((UserConsumer)getUsers().get(userConsumerPosition)).concatenatePlaylistInfo();
    }
    /**This method is used to identify the user that will be used to generate the audios report.
     * @param playlistIndex Storages the playlist index position.
     * @param userIndex Storages the user consumer index position.
     * @return A String with the information of each audio stored in the playlist identified.
     */
    public String concatenateAudioFromPlaylist(int userIndex, int playlistIndex){
        return ((UserConsumer)getUsers().get(userIndex)).concatenateAudiosFromPlaylist(playlistIndex);

    }

    // Controller Methods

    /**This method converts a specific string format into seconds. Format : "2:12", "12:12".
     * @param duration Storages the duration in String format
     * @return
     */
    public static int convertToSeconds(String duration){
        int seconds = 0;
        String seccions[];
        if (duration.length()>2){
            seccions = duration.split(":",2);
            seconds = Integer.parseInt(seccions[0]) * 60 + Integer.parseInt(seccions[1]);
        }else seconds+= Integer.parseInt(duration);

        return seconds;
    }


    // Getters and setters

    public ArrayList<Audio> getAudios() {
        return audios;
    }

    public void setAudios(ArrayList<Audio> audios) {
        this.audios = audios;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
