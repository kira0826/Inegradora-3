package neotunes.model.audio;

import java.nio.file.attribute.PosixFileAttributes;
import java.util.ArrayList;
import java.util.Random;

public class Playlist {

    private ArrayList<Audio> audios = new ArrayList<Audio>();
    private String name;
    private String code;

    public Playlist(String name) {

        this.name = name;

    }

    /**
     * This method add an audio into a specific arraylist.
     *
     * @param audio Storages the audio that will aggregate.
     */
    public boolean addAudio(Audio audio) {
        getAudios().add(audio);
        return true;
    }

    /**This method apply a pattern method depending on the playlist type identified.
     * @return A string with the share code.
     */
    public String sharePlaylist(){
        int array [][] = generateArray();
        switch (this.identifyPlaylistType()){

            case "1":
                return podcastAndSongsPattern(array);
            case "2":
                return justSongPattern(array);
            case "3":
                return justPodcastPattern(array);
            default: return "No se pudo generar el codigo.";
        }

    }

    /**This method is used to identify if the playlist just have song, or just podcast or both.
     * @return A String that is used to indicate the type.
     */
    public String identifyPlaylistType(){
        String selection ="";
        boolean hasPodcast = false;
        boolean hasSongs = false;
        for (int i = 0; i< getAudios().size();i++){
            if (getAudios().get(i) instanceof Song) hasSongs = true;
            if (getAudios().get(i) instanceof Podcast) hasPodcast = true;
            if (hasPodcast && hasPodcast) break;
        }
        if (hasSongs)  selection="2";
        if (hasPodcast) selection="3";
        if (hasSongs && hasPodcast) selection=  "1";
        return selection;
    }

    /**
     * This method delete an audio into a specific arraylist.
     *
     * @param audio Storages the audio that will aggregate.
     */
    public boolean deleteAudio(int audio) {
        getAudios().remove(audio);
        return true;
    }

    /**
     * This method concatenates the information of each audio stored in the audios arraylist.
     *
     * @return Returns a string with the name, duration and index position of each audio found.
     */
    public String displayAudios() {
        String message = "Audios de Playlist " + this.getName() + ".\n";
        for (int i = 0; i < this.getAudios().size(); i++) {
            message += i + ".)" + getAudios().get(i).getName() + "- Duración : " + getAudios().get(i).durationFormat() + ". \n";
        }
        return message;
    }

    /**
     * This method verifies if there is at least one Audio in the arraylist.
     *
     * @return Returns true if there is at least one Audio aggregate,  otherwise, returns false.
     */
    public boolean isThereAudios() {

        for (int i = 0; i < getAudios().size(); i++) {
            if (getAudios().get(i) != null) return true;
        }
        return false;
    }

    /**
     * This method generate an array with 6 files and 6 columns,  where each element have a random number
     * among 0 and 9.
     *
     * @return The array with randoms numbers assigned.
     */
    public int[][] generateArray() {
        Random random = new Random();
        int array[][] = new int[6][6];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = random.nextInt(10);
            }
        }
        return array;
    }

    /**
     * This method obtains a string conformed by the numbers located in the "odd" pattern route from the random array.
     *
     * @param array Storages the random array that will be used.
     * @returnThe code used to share the playlist.
     */
    public String podcastAndSongsPattern(int array[][]){

        String code = "";

        for (int i = 0 ; i < array.length;i++){
            for (int j = 0; j < array[i].length;j++){
                if ((i + j) % 2 != 0 && (i+j) > 1 ) code += array[i][j];
            }
            code += " ";
        }
        return code;
    }

    /**
     * This method obtains a string conformed by the numbers located in the "N" pattern route from the random array.
     *
     * @param array Storages the random array that will be used.
     * @return The code used to share the playlist.
     */
    public String justSongPattern(int array[][]) {
        String subCode1 = "";
        String subCode2 = "";
        String subCode3 = "";

        for (int i = 0; i < array.length; i++) {
            subCode1 += array[array.length - (i + 1)][0];
            if (i > 0 && i < array.length - 1) subCode2 += array[i][i];
            subCode3 += array[array.length - (i + 1)][array.length - 1];
        }

        return subCode1 +" "+ subCode2 +" "+ subCode3;

    }

    /**This method obtains a string conformed by the numbers located in the "T" pattern route from the random array.
     * @param array Storages the random array that will be used.
     * @return The code used to share the playlist.
     */
    public String justPodcastPattern (int array[][]){
        String subCode1 = "";
        String subCode2 = "";
        String code ="";

        for (int i = 0; i < array.length; i++) {
            subCode1 += array[i][2];
            subCode2 += array[array.length - (i+1)][3];
        }
        for (int i  = 0; i < array[0].length;i++){
            if (i == 2){
                code += " "+ subCode1;
            } else if (i==3) {
                code += " " + subCode2 + " ";
            }else {
                code += array[0][i];
            }
        }
        return code;
    }



    public ArrayList<Audio> getAudios() {
        return audios;
    }

    public void setAudios(ArrayList<Audio> audios) {
        this.audios = audios;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
