package com.olo.apimonolithic.feature.productmanagement.infrastructure.entity;

import com.olo.apimonolithic.feature.categorymanagement.infrastructure.entity.CategoryEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal value;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_fk"),
            inverseJoinColumns = @JoinColumn(name = "category_fk")
    )
    private Set<CategoryEntity> categories = new HashSet<>();
}
