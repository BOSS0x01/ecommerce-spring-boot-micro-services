package boss.team.billingservice;

import boss.team.billingservice.entities.Bill;
import boss.team.billingservice.entities.ProductItem;
import boss.team.billingservice.feign.CustomerRestClient;
import boss.team.billingservice.feign.ProductRestClient;
import boss.team.billingservice.models.Customer;
import boss.team.billingservice.models.Product;
import boss.team.billingservice.repositories.BillRepository;
import boss.team.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

@EnableFeignClients
@SpringBootApplication
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BillRepository billRepository ,
                                        ProductItemRepository productItemRepository ,
                                        CustomerRestClient customerRestClient ,
                                        ProductRestClient productRestClient) {
        return args -> {
            Collection<Customer> customers = customerRestClient.listCustomers().getContent();
            Collection<Product> products = productRestClient.listProducts().getContent();


            customers.forEach(customer -> {
                Bill savedBill =  billRepository.save(Bill.builder()
                                .billingDate(new Date())
                                .customer(customer)
                                .customerId(customer.getId())
                        .build());

                products.forEach(product -> {
                    productItemRepository.save(ProductItem.builder()
                            .price(product.getPrice())
                            .quantity(new Random().nextInt(10)+1)
                            .productId(product.getId())
                            .bill(savedBill)
                            .build());
                });


            });

        };
    }
}
