package ro.ing.store.db.initialize;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import ro.ing.store.db.entities.Product;

@Setter
@Getter
public class ProductsDTO {

	public List<Product> products;
	
}
