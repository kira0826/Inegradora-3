package neotunes.model.user;

import neotunes.model.audio.Audio;

public interface Reproduce {
    String reproduceAudio(Audio audio);

    boolean wasReproduced(Audio audio);

}
