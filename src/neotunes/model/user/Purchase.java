package neotunes.model.user;

import neotunes.model.audio.*;

import java.time.LocalDate;

public class Purchase {

    private LocalDate dateOfPurchase;
    private Sellable product;

    public Purchase(LocalDate dateOfPurchase, Sellable product) {
        this.dateOfPurchase = dateOfPurchase;
        this.product = product;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Sellable getProduct() {
        return product;
    }

    public void setProduct(Sellable product) {
        this.product = product;
    }
}
