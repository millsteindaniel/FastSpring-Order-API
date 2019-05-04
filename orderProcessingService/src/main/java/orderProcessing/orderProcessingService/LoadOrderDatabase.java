package orderProcessing.orderProcessingService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadOrderDatabase {
    //ProductRepository pRepository
    @Bean
    CommandLineRunner initOrderDatabase(OrderRepository oRepository) {
        return args -> {
            /*
            log.info("Preloading " + pRepository.save(new Product("Orange", 100, 10.0)));
            log.info("Preloading " + pRepository.save(new Product("Apple", 100, 10.0)));
            log.info("Preloading " + pRepository.save(new Product("Kiwi", 100, 10.0)));
            log.info("Preloading " + pRepository.save(new Product("Bananas", 0, 10.0)));
            */
            log.info("Preloading " + oRepository.save(new ProductOrder(2.0,"Apple", 10, "Placed")));
            log.info("Preloading " + oRepository.save(new ProductOrder(10.0,"Processed",10,"Orange")));
            log.info("Preloading " + oRepository.save(new ProductOrder(10.0,"Kiwi",10,"Delivered")));
            log.info("Preloading " + oRepository.save(new ProductOrder(10.0,"Bananas",2,"In Transit")));

        };
    }
}