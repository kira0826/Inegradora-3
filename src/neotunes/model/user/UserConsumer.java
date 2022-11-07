package neotunes.model.user;

import java.time.LocalDate;

public abstract class UserConsumer extends User implements Reproduction, PlaylistStaff{

    public UserConsumer(String name, String id, LocalDate dateOfLinking) {
        super(name, id, dateOfLinking);
    }

}
