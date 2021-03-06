package orderProcessing.orderProcessingService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {
    //ProductRepository pRepository
    @Bean
    CommandLineRunner initDatabase(ProductRepository pRepository) {
        return args -> {

            log.info("Preloading " + pRepository.save(new Product("Orange", 100, 10.0)));
            log.info("Preloading " + pRepository.save(new Product("Apple", 100, 10.0)));
            log.info("Preloading " + pRepository.save(new Product("Kiwi", 100, 10.0)));
            log.info("Preloading " + pRepository.save(new Product("Bananas", 0, 10.0)));

            //log.info("Preloading " + oRepository.save(new Order(2.0,"Apple", 10, "Placed")));
            //log.info("Preloading " + oRepository.save(new Order("Processed","Orange", 10, 10.0)));
            //log.info("Preloading " + oRepository.save(new Order("Delivered","Kiwi", 10, 10.0)));
            //log.info("Preloading " + oRepository.save(new Order("In Transit","Bananas", 5, 10.0)));

        };
    }
}