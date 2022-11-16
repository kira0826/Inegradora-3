package neotunes.model.user;

import java.util.ArrayList;
import java.util.Arrays;

public interface Advertiseable {

     ArrayList <String> advertisements = new ArrayList<>
            (Arrays.asList("Nike - Just Do It.",
                    "Coca-Cola - Open Happiness.",
            "M&Ms - Melts in Your Mouth, Not in Your Hands"));

     String selectAdvertisement();
}
