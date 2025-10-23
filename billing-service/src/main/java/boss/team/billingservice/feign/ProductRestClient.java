package boss.team.billingservice.feign;

import boss.team.billingservice.entities.ProductItem;
import boss.team.billingservice.models.Product;
import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("inventory-service")
public interface ProductRestClient {
    @GetMapping("api/products/{id}")
    Product getProductById(@PathVariable String id);

    @GetMapping("api/products")
    PagedModel<Product> listProducts();
}
