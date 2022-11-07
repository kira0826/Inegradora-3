package neotunes.model.user;

import java.time.LocalDate;

public class Artist extends UserProducer{

    public Artist(String name, String id, LocalDate dateOfLinking, String urlPhoto) {
        super(name, id, dateOfLinking, urlPhoto);
    }



}
