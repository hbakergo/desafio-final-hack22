package com.orbitallpayments.cards.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orbitallpayments.cards.domains.Card;
import com.orbitallpayments.cards.services.CardService;

@RestController
@RequestMapping("/cards")
public class CardController {

	@Autowired
	private CardService cardService;
	
	@GetMapping
	public ResponseEntity<List<Card>> findAll(){
		List<Card> cards = cardService.findAll();
		if(cards.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(cards);
		}
		
	}
	
	@PostMapping
	public ResponseEntity<Card> save(@RequestBody Card card){
		Card savedCard = cardService.save(card);
		
		return new ResponseEntity(savedCard, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id,
								@RequestBody Card card){
		boolean updated = cardService.update(id, card);
		if(!updated) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		if(!cardService.delete(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Card> findById(@PathVariable Long id){
		Optional<Card> fetchedCard = cardService.findById(id);
		
		if(!fetchedCard.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Card>(fetchedCard.get(), HttpStatus.OK);
		}
	}
	
	@GetMapping("/paginationAndSorting")
	public Page<Card> paginationAndSorting(){
		return cardService.paginationAndSorting();
	}
	
}

	
	