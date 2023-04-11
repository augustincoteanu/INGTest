package ro.ing.store.db.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.ing.store.db.entities.Cart;
import ro.ing.store.db.repositories.CartRepository;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository cartRepo;

	@Override
	public Cart saveCart(Cart cart) {
		return cartRepo.save(cart);
	}

	@Override
	public Cart getCartByUserName(String userName) {
		Optional<Cart> cartOpt = cartRepo.findByUsername(userName);
		return cartOpt.get();
	}

}
