package neotunes.model.audio;

import java.util.ArrayList;

public class Playlist {

    private ArrayList<Audio> audios = new ArrayList<Audio>();
    private String name;
    private String code;

    public Playlist( String name) {

        this.name = name;

    }

    /**This method add an audio into a specific arraylist.
     * @param audio Storages the audio that will aggregate.
     */
    public boolean addAudio(Audio audio){
        getAudios().add(audio);
        return true;
    }
    /**This method delete an audio into a specific arraylist.
     * @param audio Storages the audio that will aggregate.
     */
    public boolean deleteAudio(int audio){
        getAudios().remove(audio);
        return true;
    }

    /**This method concatenates the information of each audio stored in the audios arraylist.
     * @return Returns a string with the name, duration and index position of each audio found.
     */
    public String displayAudios (){
        String message ="Audios de Playlist " + this.getName() + ".\n";
        for (int i = 0; i < this.getAudios().size(); i++) {
            message += i + ".)" + getAudios().get(i).getName() + "- Duración : " + getAudios().get(i).durationFormat() + ". \n";
        }
        return message;
    }

    /**This method verifies if there is at least one Audio in the arraylist.
     * @return Returns true if there is at least one Audio aggregate,  otherwise, returns false.
     */
    public boolean isThereAudios(){

        for (int i = 0; i < getAudios().size(); i++) {
            if (getAudios().get(i) != null) return true;
        }
        return false;
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
