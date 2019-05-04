package orderProcessing.orderProcessingService;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity
class Product {

    private @Id @GeneratedValue Long id;
    private String name;
    private int availableQuantity;
    private double price;

    Product() {}

    Product(String name, int availableQuantity, double price) {
        this.name = name;
        this.availableQuantity = availableQuantity;
        this.price = price;
    }
}