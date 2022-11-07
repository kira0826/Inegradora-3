package neotunes.model.user;

import java.time.LocalDate;

public class ContentCreator extends UserProducer{

    public ContentCreator(String name, String id, LocalDate dateOfLinking, String urlPhoto) {
        super(name, id, dateOfLinking, urlPhoto);
    }
}
