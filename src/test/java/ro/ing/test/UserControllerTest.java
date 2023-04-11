package ro.ing.test;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
public class UserControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
    @Test
	public void addToCart_success() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/user/addToCart?id=10")
				.with(httpBasic("user1","password1"))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
    
    @Test
	public void listCart_success() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/user/listCart")
				.with(httpBasic("user1","password1"))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.username", is("user1")));
	}

}
