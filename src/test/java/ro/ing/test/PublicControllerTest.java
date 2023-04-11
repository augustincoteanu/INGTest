package ro.ing.test;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
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
public class PublicControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllRecords_success() throws Exception {        
    	 mockMvc.perform(MockMvcRequestBuilders
                 .get("/api/public/listAllProducts")
                 .contentType(MediaType.APPLICATION_JSON))
                 .andExpect(status().isOk())
                 .andExpect(jsonPath("$", hasSize(10)))
                 .andExpect(jsonPath("$[2].name", is("Televizor LG OLED CX")));
    }
    
    @Test
    public void findProduct_success() throws Exception {
    	mockMvc.perform(MockMvcRequestBuilders
                .get("/api/public/findProduct?id=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.name", is("Telefon Samsung Galaxy S21")));
    }

}
