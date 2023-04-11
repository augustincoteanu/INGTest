package ro.ing.store.db.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.ing.store.db.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	public Optional<Cart> findByUsername(String username);
	
}
