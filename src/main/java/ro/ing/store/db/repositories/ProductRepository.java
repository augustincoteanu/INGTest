package ro.ing.store.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.ing.store.db.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
