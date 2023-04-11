package ro.ing.store.db.services;

import java.util.List;
import java.util.Optional;

import ro.ing.store.db.entities.Product;

public interface ProductService {

	public List<Product> getAllProducts();
	
	public Optional<Product> getProduct(Long id);
	
	public Product saveProduct(Product product);
	
	public void deleteProduct(Long id);
	
}
