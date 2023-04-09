package com.example.foodexpress.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "cart")
public class CartEntity extends BaseEntity{

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cart_offers",
            joinColumns = {
                    @JoinColumn(name = "cart_id", referencedColumnName = "id"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "offer_id", referencedColumnName = "id")
            }
    )
    private List<OfferEntity> offers;

    @Min(0)
    @Column
    private BigDecimal totalPrice;

    public UserEntity getUser() {
        return user;
    }

    public CartEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public List<OfferEntity> getOffers() {
        return offers;
    }

    public CartEntity setOffers(List<OfferEntity> offers) {
        this.offers = offers;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public CartEntity setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }
}
