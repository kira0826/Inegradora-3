package neotunes.model.audio;

import neotunes.model.user.UserProducer;

public class Podcast extends Audio{

    private String description;
    private Category category;

    public Podcast(String name, String urlCover, int duration,  UserProducer author, String description, Category category) {
        super(name, urlCover, duration, author);
        this.description = description;
        this.category = category;
    }

    /**This method simulate the podcast reproduction,  and also, increment the number of reproduction and time of reproduction of the author.
     * @return  A String with the information of the podcast reproduced.
     */
    @Override
    public String reproduce() {
        this.getAuthor().setNumReproduction(getAuthor().getNumReproduction()+1);
        this.getAuthor().setTimeReproduced(getAuthor().getTimeReproduced()+ this.getDuration());
        this.setNumReproduction(getNumReproduction() +1);
        return "Reproduciendo: " + this.getName() + " Duracion: " + this.durationFormat() + " - " + this.getAuthor().getName() +"\n";

    }
    /**This method generates a copy of a Podcast object.
     * @return Returns a new song object with the same attributes of the selected podcast.
     */

    @Override
    public Audio copy() {

        return new Podcast(getName(),getUrlCover(),getDuration(), getAuthor(), getDescription(), getCategory());
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }




}
