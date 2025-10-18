package boss.team.inventoryservice;

import boss.team.inventoryservice.entities.Product;
import boss.team.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ProductRepository productRepository) {
        return args -> {
            List.of("laptop","phone","desktop","server").forEach(ProductName -> {
                productRepository.save(Product.builder()
                        .id(UUID.randomUUID().toString()).name(ProductName)
                        .price(Math.round(Math.random() * 100)+20).quantity((int)Math.round(Math.random()*25) +1)
                        .build());
            }) ;

            productRepository.findAll().forEach(System.out::println);
        };
    }
}
