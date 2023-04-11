package ro.ing.store.db.services;

import ro.ing.store.db.entities.Cart;

public interface CartService {
	
	public Cart saveCart(Cart cart);
	
	public Cart getCartByUserName(String userName);

}
