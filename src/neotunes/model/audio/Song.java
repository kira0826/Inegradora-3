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
