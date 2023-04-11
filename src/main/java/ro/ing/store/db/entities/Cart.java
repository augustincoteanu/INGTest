package ro.ing.store.db.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String username;

	@OneToMany(orphanRemoval = true,  cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinColumn(name = "cart_id")
	private List<Product> products = new ArrayList<Product>();
	
}
