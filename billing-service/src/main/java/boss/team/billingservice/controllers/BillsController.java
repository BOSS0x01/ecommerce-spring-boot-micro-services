package boss.team.billingservice.controllers;

import boss.team.billingservice.entities.Bill;
import boss.team.billingservice.feign.CustomerRestClient;
import boss.team.billingservice.feign.ProductRestClient;
import boss.team.billingservice.models.Customer;
import boss.team.billingservice.repositories.BillRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BillsController {
    CustomerRestClient customerRestClient;
    ProductRestClient productRestClient;
    BillRepository billRepository;

    @GetMapping("/bills")
    public List<Bill> bills() {
        List<Bill> bills = billRepository.findAll();
        bills.forEach(bill -> {
            bill.setCustomer(customerRestClient.customer(bill.getCustomerId()));


            bill.getProductItems().forEach(productItem -> {
                productItem.setProduct(productRestClient.getProductById(productItem.getProductId()));
            });

        });
        return bills;
    }

    @GetMapping("/bills/{id}")
    public Bill bill(@PathVariable Long id) {
       Bill bill = billRepository.findById(id).orElseThrow();
        bill.setCustomer(customerRestClient.customer(bill.getCustomerId()));
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(productRestClient.getProductById(productItem.getProductId()));
        });
        return bill;
    }
}
