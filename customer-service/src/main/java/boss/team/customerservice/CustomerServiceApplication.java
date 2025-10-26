package boss.team.customerservice;

import boss.team.customerservice.config.CustomerConfigParams;
import boss.team.customerservice.entities.Customer;
import boss.team.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties({CustomerConfigParams.class})

public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            List.of("Ahmed","Sare","Omar").forEach(c ->
                    customerRepository.save(Customer.builder()
                            .name(c).email(c + "@gmail.com")
                            .build())
            );

            customerRepository.findAll().forEach(System.out::println);

        };

    }
}
