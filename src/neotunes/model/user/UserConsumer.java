package neotunes.model.user;

import java.time.LocalDate;

public abstract class UserConsumer extends User implements Reproduction{

    public UserConsumer(String name, String id, LocalDate dateOfLinking) {
        super(name, id, dateOfLinking);
    }

    public abstract boolean addPlaylist(String name);

}
