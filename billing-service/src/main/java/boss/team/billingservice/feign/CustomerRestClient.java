package boss.team.billingservice.feign;

import boss.team.billingservice.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("customer-service")
public interface CustomerRestClient {
    @GetMapping("api/customers/{id}")
    Customer customer(@PathVariable Long id);

    @GetMapping("api/customers")
    PagedModel<Customer> listCustomers();
}
