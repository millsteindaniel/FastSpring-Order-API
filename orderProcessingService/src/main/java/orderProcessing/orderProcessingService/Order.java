
package orderProcessing.orderProcessingService;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity
public class Order {
    private @Id @GeneratedValue Long id;
    private String status;
    private String product;
    private int quantity;
    private double price;

    Order() {}

    Order(String status, String product, int quantity, double price) {
        this.status = status;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }
}
