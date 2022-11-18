package neotunes.model;

import neotunes.model.audio.*;
import neotunes.model.user.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class NeotunesController {

    private ArrayList<Audio> audios = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    /**This method identifies and reports the top 5  content creators with the highest number of plays. In case there are less than 5
     * users identified, the size of the top is adjusted to the number of objects.
     * @return A String with the report of the top Content Creators.
     */
    //Stats
    public String top5ContentCreator(){

        ArrayList<ContentCreator> temporalTop = new ArrayList<>();

        for (int i = 0; i < getUsers().size(); i++) {
            if (getUsers().get(i) instanceof ContentCreator){
                temporalTop.add((ContentCreator) getUsers().get(i));
            }
        }
        ContentCreator temporalContentCreator;
        for (int i = 0; i< temporalTop.size();i++){
            for (int j = i; j< temporalTop.size();j++){
                if (temporalTop.get(i).getNumReproduction() < temporalTop.get(j).getNumReproduction()){
                    temporalContentCreator = temporalTop.get(i);
                    temporalTop.set(i,temporalTop.get(j));
                    temporalTop.set(j,temporalContentCreator);
                }
            }
        }

        String message= "Creadores de contenido: \n";

        for (int i = 0; i< temporalTop.size();i++){
            if (i <= 5){
                message += i +".) " + temporalTop.get(i).getName() +" | Reproducciones: " + temporalTop.get(i).getNumReproduction() + ". \n ";
            }else break;
        }
        return message;
    }

    /**This method identifies and reports the top 5  artist with the highest number of plays. In case there are less than 5
     * users identified, the size of the top is adjusted to the number of objects.
     * @return A String with the report of the top artists.
     */
    public String top5Artist(){

        ArrayList<Artist> temporalTop = new ArrayList<>();

        for (int i = 0; i < getUsers().size(); i++) {
            if (getUsers().get(i) instanceof Artist){
                temporalTop.add((Artist) getUsers().get(i));
            }
        }
        Artist temporalArtist;
        for (int i = 0; i< temporalTop.size();i++){
            for (int j = i; j< temporalTop.size();j++){
                if (temporalTop.get(i).getNumReproduction() < temporalTop.get(j).getNumReproduction()){
                    temporalArtist = temporalTop.get(i);
                    temporalTop.set(i,temporalTop.get(j));
                    temporalTop.set(j,temporalArtist);
                }
            }
        }

        String message= "Artistas: \n";

        for (int i = 0; i< temporalTop.size();i++){
            if (i <= 5){
                message += i +".) " + temporalTop.get(i).getName() +" | Reproducciones: " + temporalTop.get(i).getNumReproduction() + ". \n";
            }else break;
        }
        return message;
    }



    /**This method identifies the favorite podcast category of the global program.
     * @return If there is at least one podcast reproduced, returns a string with the information of the favorite category, otherwise
     * return a string indicating that there are not songs.
     */
    public String favoriteGlobalPodcastCategory(){
        if (isTherePodcast()){
            int counter [] = new int[Category.values().length];
            //Fill process
            for (int i = 0; i < counter.length; i++) {
                counter[i] = 0;
            }
            // Count process
            for (int  i  =0 ; i < Category.values().length;i++){
                for (int j = 0; j< getAudios().size(); j++) {
                    if (getAudios().get(j) instanceof Podcast){
                        if (((Podcast) getAudios().get(j)).getCategory().equals(Category.values()[i])){
                            counter[i] = counter[i] + getAudios().get(j).getNumReproduction();
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
            String message = "Categoría favorita:  " + Category.values()[position]
                    + " | Número de reproducciones: " + counter[position];

            return message;
        }
        return "No hay podcast.";
    }


    /**This method identifies the UserConsumer that want to know his favorite podcast category.
     * @param userPosition Storages the user consumer position.
     *  @return A String with the information of the favorite podcast category of a specific user.
     */
    public String favoriteUserPodcastCategory(int userPosition){
        return ((UserConsumer)getUsers().get(userPosition)).favoriteUserPodcastCategory();
    }
    /**This method identifies the UserConsumer that want to know his favorite song genre.
     * @param userPosition Storages the user consumer position.
     *  @return A String with the information of the favorite song genre of a specific user.
     */
    public String favoriteUserSongGenre(int userPosition){
        return ((UserConsumer)getUsers().get(userPosition)).favoriteUserSongGenre();
    }
    /**This method identifies the favorite song genre of the global program.
     * @return If there is at least one song reproduced, returns a string with the information of the favorite genre, otherwise
     * return a string indicating that there are not songs.
     */
    public String favoriteGlobalSongGenre(){
        if (isThereSong()){
            int counter [] = new int[Genre.values().length];
            //Fill process
            for (int i = 0; i < counter.length; i++) {
                counter[i] = 0;
            }
            // Count process
            for (int  i  =0 ; i < Genre.values().length;i++){
                for (int j = 0; j< getAudios().size(); j++) {
                    if (getAudios().get(j) instanceof Song){
                        if (((Song) getAudios().get(j)).getGenre().equals(Genre.values()[i])){
                            counter[i] = counter[i] + getAudios().get(j).getNumReproduction();
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
        return "No hay canciones.";
    }

    /**This method identifies the top-selling song
     * @return A String with the information of the top-selling song.
     */
    public String topSellingSong(){
        Song temporal = null;
        boolean isFistTime = true;
        for(int i = 0; i < getAudios().size();i++ ){
            if (getAudios().get(i) instanceof Song){
                if (isFistTime){
                    temporal = ((Song)getAudios().get(i));
                    isFistTime = false;
                }
                if (temporal.getNumSales() < ((Song) getAudios().get(i)).getNumSales()) temporal = ((Song) getAudios().get(i));
            }
        }

        return "Canción más vendida:  " + temporal.getName() + " | Ventas: " + temporal.getNumSales() + " | Ingresos: "
                + temporal.getNumSales()* temporal.getPrice();
    }

    /**This method counts the quantity of songs reproductions.
     * @return An intenger with the quantity of songs reproductions.
     */
    public String songsReproductions(){
        int numReproductions = 0;
        for(int i = 0; i< getAudios().size();i++){
            if (getAudios().get(i) instanceof Song){
                numReproductions += getAudios().get(i).getNumReproduction();
            }

        }

        return "REPRODUCCIONES DE CANCIONES: " + numReproductions;
    }

    /**This method counts the quantity of podcast reproductions.
     * @return An intenger with the quantity of podcast reproductions.
     */
    public String podcastReproductions(){
        int numReproductions = 0;
        for(int i = 0; i< getAudios().size();i++){
            if (getAudios().get(i) instanceof Podcast){
                numReproductions += getAudios().get(i).getNumReproduction();
            }

        }

        return "REPRODUCCIONES DE PODCAST: " + numReproductions;
    }


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

    /**
     * This method receives the necessary information in order to create a Song object.
     *
     * @param name          Storages the name of the song.
     * @param urlCover      Storages the url of the cover photo.
     * @param duration      Storages the duration of the song.
     * @param author        Storages the position of the song author.
     * @param price         Storages the price of the song.
     * @param album         Storages the name of the album related with the song.
     * @param genrePosition Storages the position of the genre related with the song.
     * @return Return  a string indicating that the song was created.
     */

    //Register audios
    public String registerSong(String name, String urlCover, String duration, int author, double price, String album, int genrePosition) {

        Song song = new Song(name, urlCover, convertToSeconds(duration), (Artist) getUsers().get(author), price, album, Genre.values()[genrePosition]);
        getAudios().add(song);
        return "Canción registrada";
    }

    /**
     * This method receives the necessary information in order to create a Podcast object.
     *
     * @param name             Storages the name of the podcast.
     * @param urlCover         Storages the url of the cover photo.
     * @param duration         Storages the duration of the podcast.
     * @param author           Storages the position of the podcast author.
     * @param description      Storages the name of the album related with the podcast.
     * @param categoryPosition Storages the position of the category related with the podcast.
     * @return Return a string indicating that the podcast was created.
     */
    public String registerPodcast(String name, String urlCover, String duration, int author, String description, int categoryPosition) {

        Podcast podcast = new Podcast(name, urlCover, convertToSeconds(duration), (ContentCreator) getUsers().get(author), description, Category.values()[categoryPosition]);
        getAudios().add(podcast);
        return "Podcast Registrado";
    }

    //Playlist Staff

    /**
     * This method identifies the UserConsumer that will be uses to storages the new playlist that will be created.
     *
     * @param name         Storages the playlist name
     * @param userPosition Storages the user consumer position
     * @return A String indicating if the playlist was added or not.
     */

    public String createPlaylist(String name, int userPosition) {

        if (((UserConsumer) getUsers().get(userPosition)).addPlaylist(name)) {
            return "Playlist agregada";
        }
        return "No se pudo agregar la playlist";
    }

    /**
     * This method is used to identify the user that want to share a playlist.
     *
     * @param userIndex Storages the user index position.
     * @param playlist  Storages the playlist index position
     * @return The code that will be used to share the playlist.
     */
    public String sharePlaylist(int userIndex, int playlist) {
        return ((UserConsumer) getUsers().get(userIndex)).sharePlaylist(playlist);
    }

    /**
     * This method is used to identify the user and audio selected in order to add an audio to a playlist of the user identified.
     *
     * @param audioIndex    Storages the index position of the audio that will be added.
     * @param userIndex     Storages the user index positin of the user consumer that has the playlist.
     * @param playlistIndex Storages the index position of the user playlist that will be used to save the audio.
     * @return Return true if the audio was suscesfully added,  otherwise,  returns false.
     */
    public boolean addAudio(int audioIndex, int userIndex, int playlistIndex) {
        return ((UserConsumer) getUsers().get(userIndex)).addAudio(getAudios().get(audioIndex), playlistIndex);
    }

    /**
     * This method is used to identify the user selected in order to delete an audio to a playlist of the user identified.
     *
     * @param audioIndex    Storages the index position of the audio that will be deleted.
     * @param userIndex     Storages the user index positin of the user consumer that has the playlist.
     * @param playlistIndex Storages the index position of the user playlist that will be used to delete the audio.
     * @return Return true if the audio was suscesfully deleted,  otherwise,  returns false.
     */
    public boolean deleteAudio(int audioIndex, int userIndex, int playlistIndex) {
        return ((UserConsumer) getUsers().get(userIndex)).deleteAudio(playlistIndex, audioIndex);
    }

    /**
     * This method is used to identify the user and audio selected in order reproduce an audio.
     *
     * @param audioPosition Storages the index position of the audio that will be reproduced.
     * @param userPosition  Storages the user index positin of the user consumer that want to reproduce an audio.
     * @return Returns a string simulating the reproduction of an audio.
     */
    public String reproduceAudio(int userPosition, int audioPosition) {
        String message = "";
        message += getAudios().get(audioPosition).reproduce();
        message += ((UserConsumer) getUsers().get(userPosition)).reproduceAudio(getAudios().get(audioPosition));
        return message;
    }

    /**
     * This method is used to identify the user and the sellable selected in order make a purchase.
     *
     * @param sellablePosition Storages the index position of the Sellable that will be sold.
     * @param userPosition     Storages the user index positin of the user consumer that want to purchase a Sellable.
     * @return Returns true when the purchase was done, otherwise, returns false.
     */
    public boolean purchaseSellable(int userPosition, int sellablePosition) {

        return (((UserConsumer) getUsers().get(userPosition)).purchaseSellable((Sellable) getAudios().get(sellablePosition)));

    }

    // Isthere

    // User

    /**
     * This method verifies if there are Artist already registered.
     *
     * @return Returns true if exist at least one Artist, otherwise, return false.
     */
    public boolean isThereArtist() {
        for (int i = 0; i < getUsers().size(); i++) {
            if (getUsers().get(i) instanceof Artist) return true;
        }
        return false;
    }

    /**
     * This method verifies if there are ContentsCreators already registered.
     *
     * @return Returns true if exist at least one Artist, otherwise, return false.
     */
    public boolean isThereContentCreators() {
        for (int i = 0; i < getUsers().size(); i++) {
            if (getUsers().get(i) instanceof ContentCreator) return true;
        }
        return false;
    }

    /**
     * This method verifies if there are UserConsumers already registered.
     *
     * @return Returns true if exist at least one UserConsumer, otherwise, return false.
     */
    public boolean isThereConsumers() {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof UserConsumer) return true;
        }
        return false;
    }

    // Audios

    /**
     * This method is used to identify the user that will be used to check if there is audios in a specific playlist of him.
     *
     * @return Returns true if exist at least one Audio in the selected playlist, otherwise, return false.
     */
    public boolean isThereAudiosInPlaylist(int userPosition, int playlistPosition) {
        return ((UserConsumer) getUsers().get(userPosition)).isThereAudiosInPlaylist(playlistPosition);
    }

    /**
     * This method verifies if there are Audios already registered.
     *
     * @return Returns true if exist at least one Audio, otherwise, return false.
     */
    public boolean isThereAudios() {
        if (getAudios().size() > 0) return true;
        return false;
    }

    /**This method verifies if there are Songs objects already registered.
     * @return Returns true if exist at least one Song object, otherwise, return false.
     */
    public boolean isThereSong(){
        for(int i = 0; i< getAudios().size();i++){
            if (getAudios().get(i) instanceof Song) return true;

        }
        return false;
    }

    /**This method verifies if there are Podcast objects already registered.
     * @return Returns true if exist at least one Podcast object, otherwise, return false.
     */
    public boolean isTherePodcast(){
        for(int i = 0; i< getAudios().size();i++){
            if (getAudios().get(i) instanceof Podcast) return true;
        }
        return false;
    }


    /**This method verifies if there are Sellables objects already registered.
     * @return Returns true if exist at least one Sellable object, otherwise, return false.
     */
    public boolean isThereSellables(){
        for(int i = 0; i< getAudios().size();i++){
            if (getAudios().get(i) instanceof Sellable) return true;

        }
        return false;
    }
    /**This method verifies if there are playlist already registered in one specific user consumer.
     * @return Returns true if exist at least one playlist, otherwise, return false.
     */

    public boolean isTherePlaylist (int userConsumerPosition){
        return ((UserConsumer)getUsers().get(userConsumerPosition)).hasPlaylist();
    }


    // Concatenate Information
    /**This method concatenates the Sellables info with their correspondent position.
     * @return A string with the Sellables names, duration and position.rep
     */
    public String concatenateSellableInfo (){
        String message = "Vendibles info: \n";
        for (int i = 0; i < getAudios().size(); i++) {
            if (getAudios().get(i) instanceof Sellable) message += i+".)" + getAudios().get(i).getName() +" - Duración : " + getAudios().get(i).durationFormat()+". \n";
        }
        return message;
    }

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
