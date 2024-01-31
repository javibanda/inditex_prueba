package com.inditex.zarachallenge.repository;

import com.inditex.zarachallenge.infrastructure.model.ProductAvailabilityEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductAvailabilityRepository extends JpaRepository<ProductAvailabilityEvent, UUID> {

    ProductAvailabilityEvent getProductAvailabilityEventBySizeId(Long sizeId);
}
