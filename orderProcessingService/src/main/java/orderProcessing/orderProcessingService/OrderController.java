package orderProcessing.orderProcessingService;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/orders/successful")
    List<ProductOrder> allSuccessfulOrders() {
        List<ProductOrder> all = oRepository.findAll();
        List<ProductOrder> res = new ArrayList<ProductOrder>();
        for(int i = 0; i < all.size(); i++){
            if(all.get(i).getStatus() == "Successful"){
                res.add(all.get(i));
            }
        }
        return res;
    }

    @GetMapping("/orders/unsuccessful")
    List<ProductOrder> allUnsuccessfulOrders() {

        List<ProductOrder> all = oRepository.findAll();
        List<ProductOrder> res = new ArrayList<ProductOrder>();
        for(int i = 0; i < all.size(); i++){
            if(all.get(i).getStatus() == "Unsuccessful"){
                res.add(all.get(i));
            }
        }
        return res;
    }

    @GetMapping("/orders/status/{id}")
    String allOrdersStatus(@PathVariable Long id) {
        ProductOrder order = oRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        return order.getStatus()+"\n";
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

    @GetMapping("/orders/place/{id}/{quantity}")
    ProductOrder placeOrder( @PathVariable Long id, @PathVariable int quantity) {
        Product p = pRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));;
        if(quantity <= p.getAvailableQuantity()){
            p.setAvailableQuantity(p.getAvailableQuantity()-quantity);
            pRepository.save(p);
            ProductOrder newOrder =new ProductOrder(p.getPrice(),p.getName(),quantity,"Successful");
            return oRepository.save(newOrder);
        }else{
            ProductOrder newOrder =new ProductOrder(p.getPrice(),p.getName(),quantity,"Unsuccessful");
            return oRepository.save(newOrder);
        }
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
