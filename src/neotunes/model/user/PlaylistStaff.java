package neotunes.model.user;

import neotunes.model.audio.*;

public interface PlaylistStaff {

    boolean addPlaylist(String name);
    boolean hasPlaylist();
    String concatenatePlaylistInfo ();
    boolean addAudio(Audio audio, int playlistPosition);
    boolean deleteAudio(int playlistPosition, Audio audio);

}
