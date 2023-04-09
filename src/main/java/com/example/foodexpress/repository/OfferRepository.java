package com.example.foodexpress.repository;

import com.example.foodexpress.domain.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
     Long countById(Long id);

}
