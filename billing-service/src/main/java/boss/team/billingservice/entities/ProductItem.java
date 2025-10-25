package boss.team.billingservice.entities;


import boss.team.billingservice.models.Customer;
import boss.team.billingservice.models.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private double price;
    private int quantity;
    private String productId;
    @Transient
    private Product product;
    @ManyToOne
    private Bill bill;
}
