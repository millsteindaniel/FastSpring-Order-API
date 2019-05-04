package orderProcessing.orderProcessingService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderRepository oRepository;
    private final ProductRepository pRepository;

    OrderController(OrderRepository oRepository, ProductRepository pRepository) {

        this.oRepository = oRepository;
        this.pRepository = pRepository;

    }

    // Aggregate root

    @GetMapping("/orders")
    List<ProductOrder> allOrders() {
        return oRepository.findAll();
    }

    @PostMapping("/orders")
    ProductOrder newOrder(@RequestBody ProductOrder newOrder) {
        return oRepository.save(newOrder);
    }

    // Single item

    @GetMapping("/orders/{id}")
    ProductOrder oneOrders(@PathVariable Long id) {

        return oRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @PutMapping("/orders/{id}")
    ProductOrder replaceOrder(@RequestBody ProductOrder newOrder, @PathVariable Long id) {

        return oRepository.findById(id)
                .map(order -> {
                    order.setProduct(newOrder.getProduct());
                    order.setQuantity(newOrder.getQuantity());
                    order.setPrice(newOrder.getPrice());
                    order.setStatus(newOrder.getStatus());
                    return oRepository.save(order);
                })
                .orElseGet(() -> {
                    newOrder.setId(id);
                    return oRepository.save(newOrder);
                });
    }

    @DeleteMapping("/orders/{id}")
    void deleteOrder(@PathVariable Long id) {
        oRepository.deleteById(id);
    }

    @GetMapping("/products")
    List<Product> allProducts() {
        return pRepository.findAll();
    }

    @PostMapping("/products")
    Product newProduct(@RequestBody Product newProduct) {
        return pRepository.save(newProduct);
    }

    // Single item

    @GetMapping("/products/{id}")
    Product oneProducts(@PathVariable Long id) {

        return pRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @PutMapping("/products/{id}")
    Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {

        return pRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setAvailableQuantity(newProduct.getAvailableQuantity());
                    product.setPrice(newProduct.getPrice());
                    return pRepository.save(product);
                })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return pRepository.save(newProduct);
                });
    }

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable Long id) {
        pRepository.deleteById(id);
    }
}
