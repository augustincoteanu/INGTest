package ro.ing.store.controller;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import ro.ing.store.db.entities.Cart;
import ro.ing.store.db.entities.Product;
import ro.ing.store.db.services.CartService;
import ro.ing.store.db.services.ProductService;

@RestController
@RequestMapping(value = "api/user")
@Log4j2
public class UserController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService prodService;
	
	@GetMapping(value = "/addToCart")
	//@PreAuthorize("hasRole('USER')")
	private Cart addToCart(@RequestParam(value = "id", required = true) Long id, @AuthenticationPrincipal UserDetails userDetails) {
		
		String loggedUser = userDetails.getUsername();
		Cart userCart = null;
		try {
			userCart = cartService.getCartByUserName(loggedUser);
		} catch (NoSuchElementException e) {
			log.info("An user cart cannot be found! It will be created.");
			userCart = new Cart();
			userCart.setUsername(loggedUser);
			userCart = cartService.saveCart(userCart);
		}
		
		Optional<Product> productOpt = prodService.getProduct(id);
		if (productOpt.isPresent()) {
			userCart.getProducts().add(productOpt.get());
			userCart = cartService.saveCart(userCart);
		}
		
		return userCart;
	}
	
	@GetMapping(value = "/listCart")
	private Cart listCart(@AuthenticationPrincipal UserDetails userDetails) {
		String loggedUser = userDetails.getUsername();
		Cart userCart = null;
		try {
			userCart = cartService.getCartByUserName(loggedUser);
		} catch (NoSuchElementException e) {
			log.info("An user cart cannot be found! It will be created.");
			userCart = new Cart();
			userCart.setUsername(loggedUser);
			userCart = cartService.saveCart(userCart);
		}
		
		return userCart;
	}

}
