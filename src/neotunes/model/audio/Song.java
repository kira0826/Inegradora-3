package neotunes.model.audio;

import neotunes.model.user.UserProducer;

public class Song extends Audio implements Sellable{

    private double price;
    private Genre genre;
    private int numSales;
    private String album;

    public Song(String name, String urlCover, int duration, UserProducer author, double price, String album, Genre genre) {
        super(name, urlCover, duration,author);
        this.price = price;
        this.numSales = 0;
        this.album = album;
        this.genre = genre;
    }

    /**This method generates a copy of a Song object.
     * @return Returns a new song object with the same attributes of the selected song.
     */
    @Override
    public Audio copy() {
        return new Song(getName(), getUrlCover(),getDuration(),getAuthor(),getPrice(),
                getAlbum(),getGenre());
    }

    /**This method simulate a Song reproduction,  and also, increment the number of reproduction and time of reproduction of the author.
     * @return  A String with the information of the Song reproduced.
     */

    @Override
    public String reproduce() {

        this.getAuthor().setNumReproduction(getAuthor().getNumReproduction() + 1);
        this.getAuthor().setTimeReproduced(getAuthor().getTimeReproduced()+this.getDuration());
        this.setNumReproduction(getNumReproduction() +1);
        return "Reproduciendo: " + this.getName() + " Duracion: " + this.durationFormat() + " - " + this.getAuthor().getName() +"\n";
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumSales() {
        return numSales;
    }

    public void setNumSales(int numSales) {
        this.numSales = numSales;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }


}
