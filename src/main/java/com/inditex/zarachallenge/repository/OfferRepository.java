package com.inditex.zarachallenge.repository;

import com.inditex.zarachallenge.model.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    Offer findFirstByProductIdAndValidFromBeforeOrderByValidFromDesc(Long productId, Timestamp currentDate);

}
