package org.sid.walletservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Currency {
    @Id
    private String code;
    private String name;
    private String symbol;
    private Double salePrice;
    private Double purchasePrice;
}
