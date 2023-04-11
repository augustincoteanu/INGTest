package ro.ing.store.db.initialize;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.extern.log4j.Log4j2;
import ro.ing.store.db.services.ProductService;

@Service
@Log4j2
public class DBInitializer {

	@Autowired 
	private ProductsLoader productsLoader;
	
	@Autowired
	private ProductService service;
	
	@PostConstruct
	public void init() {
		try {
			ProductsDTO products = productsLoader.loadFromFile();
			products.getProducts().forEach(s -> service.saveProduct(s));
		} catch (JsonMappingException e) {
			log.error("Error deserializing products json : " + e.getMessage(), e);
		} catch (JsonProcessingException e) {
			log.error("Error deserializing products json : " + e.getMessage(), e);
		}

	}

}
