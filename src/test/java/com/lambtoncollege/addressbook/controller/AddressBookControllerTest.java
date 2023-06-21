package com.lambtoncollege.addressbook.controller;

import ca.lambtoncollege.addressbook.controller.AddressController;
import ca.lambtoncollege.addressbook.entity.Address;
import ca.lambtoncollege.addressbook.entity.repository.AddressRepository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = {AddressController.class})
@AutoConfigureMockMvc
public class AddressBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressRepository addressRepository;
    @Autowired
    private AddressController controller;

    
   @Test
   public void loadController() throws Exception {
       assertThat(controller).isNotNull();
   }

   @Test
   public void firstPage() throws Exception {
	   Address address = new Address(0, "Harsh", "harsh@gmail.com", "(123)123-1234");
	   Address address1 = new Address(1, "Harsh", "harsh@gmail.com", "(123)123-1234");
   	List<Address> list = new ArrayList<Address>();
   	list.add(address1);
   	list.add(address);
		Mockito.when(addressRepository.findAll()).thenReturn(list);
		
       this.mockMvc.perform(get("/")).andExpect(status().isOk());
   }
    
	@Test
	public void addAdderss() throws Exception {
		Address address = new Address(0, "Harsh", "harsh@gmail.com", "(123)123-1234");
		Mockito.when(addressRepository.save(Mockito.any(Address.class))).thenReturn(address);

		this.mockMvc.perform(post("/addaddress")
				.content(new ObjectMapper().writeValueAsString(address))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isFound());
	}
    
	@Test
	public void showUpdateForm() throws Exception {
		Optional<Address> address = Optional.of(new Address(0, "Harsh", "harsh@gmail.com", "(123)123-1234"));
		Mockito.when(addressRepository.findById(Mockito.anyLong())).thenReturn(address);
	
		this.mockMvc.perform(get("/edit/0")).andExpect(status().isOk());
	
	}
	@Test
	public void updateAddress() throws Exception {
		Address address = new Address(0, "Harsh", "harsh@gmail.com", "(123)123-1234");
		Mockito.when(addressRepository.save(Mockito.any(Address.class))).thenReturn(address);
	
		this.mockMvc.perform(post("/update/0")).andExpect(status().isFound());
	
	}
	
	@Test
	public void deleteAddress() throws Exception {
		Optional<Address> address = Optional.of(new Address(0, "Harsh", "harsh@gmail.com", "(123)123-1234"));		
		Mockito.when(addressRepository.findById(Mockito.anyLong())).thenReturn(address);

		this.mockMvc.perform(get("/delete/0")).andExpect(status().isFound());
	
	}
	
    @Test
    public void totalCountOnpage() throws Exception {
        this.mockMvc.perform(get("/countTotal")).andExpect(status().isOk());
    } 
    


}
