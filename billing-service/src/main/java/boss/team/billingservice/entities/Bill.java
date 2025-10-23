package boss.team.billingservice.entities;

import boss.team.billingservice.models.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Date billingDate;
    private Long customerId;
    @Transient
    private Customer customer;
    @OneToMany(mappedBy = "bill")
    private List<ProductItem> productItem;

}
