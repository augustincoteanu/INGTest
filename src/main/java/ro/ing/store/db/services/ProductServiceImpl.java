package ro.ing.store.db.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.ing.store.db.entities.Product;
import ro.ing.store.db.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	@Override
	public Optional<Product> getProduct(Long id) {
		return productRepo.findById(id);
	}

	@Override
	public Product saveProduct(Product product) {
		return productRepo.save(product);
	}

	@Override
	public void deleteProduct(Long id) {
		productRepo.deleteById(id);
	}

}
