package ro.ing.test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ro.ing.store.IngTestApplication;

@SpringBootTest(classes = IngTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AdminControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void addProduct_success() throws Exception {        
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/admin/addProduct")
				.with(httpBasic("administrator","admin"))
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\": \"Mock Product Name\", \"description\":\"Mock product description\", \"price\": 11.11}"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void deleteProduct_success() throws Exception {        
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/api/admin/deleteProduct?id=10")
				.with(httpBasic("administrator","admin"))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	public void updateProductPrice_success() throws Exception {        
		mockMvc.perform(MockMvcRequestBuilders
				.put("/api/admin/updateProductPrice?id=9&price=7.63")
				.with(httpBasic("administrator","admin"))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	

}
