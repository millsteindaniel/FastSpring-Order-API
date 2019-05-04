package orderProcessing.orderProcessingService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Product("Orange", 100, 10.0)));
            log.info("Preloading " + repository.save(new Product("Apple", 100, 10.0)));
            log.info("Preloading " + repository.save(new Product("Kiwi", 100, 10.0)));
            log.info("Preloading " + repository.save(new Product("Bananas", 0, 10.0)));
        };
    }
}