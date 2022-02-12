package com.orbitallpayments.cards.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.orbitallpayments.cards.domains.Card;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

}
