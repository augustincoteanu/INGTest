package ro.ing.store.controller;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import ro.ing.store.db.entities.Product;
import ro.ing.store.db.services.MessageDetails;
import ro.ing.store.db.services.ProductService;

@RestController
@RequestMapping(value = "/api/admin")
@Log4j2
public class AdminController {

	@Autowired
	private ProductService prodService;

	@PostMapping("/addProduct")
	//@PreAuthorize("hasRole('ADMIN')")
	private MessageDetails addProduct(@RequestBody @Valid Product product) {
		Product savedProd = prodService.saveProduct(product);
		if (savedProd != null) {
			return new MessageDetails(new Date(), "SUCCESS", "Product saved with success!");
		}
		return new MessageDetails(new Date(), "ERROR", "Fail to save product!");
	}

	@DeleteMapping("/deleteProduct")
	//@PreAuthorize("hasRole('ADMIN')")
	private MessageDetails deleteProduct(@RequestParam Long id) {
		prodService.deleteProduct(id);
		return new MessageDetails(new Date(), "SUCCESS", "Product deleted with success!");
	}

	@PutMapping("/updateProductPrice")
	//@PreAuthorize("hasRole('ADMIN')")
	private MessageDetails updateProductPrice(@RequestParam Long id, @RequestParam Float price) {
		Optional<Product> prodOpt = prodService.getProduct(id);
		Product prod = prodOpt.orElseThrow();
		prod.setPrice(price);
		prodService.saveProduct(prod);
		return new MessageDetails(new Date(), "SUCCESS", "Product price updated with success!");
	}	
}
