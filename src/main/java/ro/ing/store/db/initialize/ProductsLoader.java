package ro.ing.store.db.initialize;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class ProductsLoader {

	@Autowired
	ResourceLoader resourceLoader;

	private static final String JSON_FILE = "products.json";

	@Bean
	public ProductsDTO loadFromFile() throws JsonMappingException, JsonProcessingException {
		Resource resource = resourceLoader.getResource("classpath:"  + "/" + JSON_FILE);
		String content = null;
		try {
			content = getContentAsString(resource);
		} catch (IOException e) {
			log.error("Fail to read the content of " + resource.getFilename(), e);
			return null;
		}
		ProductsDTO productsDTO =  new ObjectMapper().readValue(content, ProductsDTO.class);
		return productsDTO;
	}

	private String getContentAsString(Resource resource) throws IOException {
		Reader reader = new InputStreamReader(resource.getInputStream());
		return FileCopyUtils.copyToString(reader);
	}


}
