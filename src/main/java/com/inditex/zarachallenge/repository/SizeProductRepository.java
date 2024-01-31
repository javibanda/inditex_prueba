package com.inditex.zarachallenge.repository;

import com.inditex.zarachallenge.model.entity.SizeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeProductRepository extends JpaRepository<SizeProduct, Long> {

    SizeProduct findBySizeId(Long sizeId);

}
