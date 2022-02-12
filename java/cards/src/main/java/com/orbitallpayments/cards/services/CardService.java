package com.orbitallpayments.cards.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.orbitallpayments.cards.domains.Card;
import com.orbitallpayments.cards.repositories.CardRepository;

@Service
public class CardService {

	@Autowired
	private CardRepository cardRepository;
	
	public Card save(Card card) {
		return cardRepository.save(card);
	}
	
	public List<Card> findAll(){
		List<Card> cards = new ArrayList<>();
		cardRepository.findAll()
					  .forEach(cards :: add);
		return cards;
	}
	
	public Optional<Card> findById(Long id){
		return cardRepository.findById(id);
	}
	
	public boolean update(Long id, Card cardNew){
		Optional<Card> cardOld = cardRepository.findById(id);
		if(!cardOld.isPresent()) {
			return false; 
		} else {
			cardNew.setId(cardOld.get().getId());
			cardRepository.save(cardNew);
			return true;
		}
	}
	
	public boolean delete(Long id) {
		Optional<Card> cardDel = cardRepository.findById(id);
		if(!cardDel.isPresent()) {
			return false; 
		} else {
			cardRepository.delete(cardDel.get());
			return true;
		}
	}
	
	public Page<Card> paginationAndSorting() {
		return cardRepository.findAll(PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "customerName")));
	}
	
}
