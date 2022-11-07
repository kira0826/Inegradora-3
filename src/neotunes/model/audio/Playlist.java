package neotunes.model.audio;

import java.util.ArrayList;

public class Playlist {

    private ArrayList<Audio> audios = new ArrayList<Audio>();
    private String name;
    private String code;

    public Playlist( String name) {

        this.name = name;

    }

    public void addAudio(Audio audio){
        getAudios().add(audio);
    }
    public void deleteAudio(Audio audio){
        getAudios().remove(audio);
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
