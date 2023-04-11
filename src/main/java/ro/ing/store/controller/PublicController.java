package ro.ing.store.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ro.ing.store.db.entities.Product;
import ro.ing.store.db.services.ProductService;

@RestController
@RequestMapping(value = "api/public")
public class PublicController {
	
	@Autowired
	private ProductService prodService;

	@GetMapping("/listAllProducts")
	private List<Product> listProducts() {
		List<Product> products = prodService.getAllProducts();
		return products;
	}
	
	@GetMapping("/findProduct")
	private Product findProducts(@RequestParam Long id) {
		Optional<Product> product = prodService.getProduct(id);
		return product.orElse(new Product());
	}
	
}
