package ca.lambtoncollege.addressbook.controller;

import ca.lambtoncollege.addressbook.entity.Address;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.lambtoncollege.addressbook.entity.repository.AddressRepository;


@Controller
public class AddressController {
	
	private static int totalCount = 0; 
	private final AddressRepository addressBookRepository;

    @Autowired
    public AddressController(AddressRepository addressBookRepository) {
        this.addressBookRepository = addressBookRepository;
    }
    
    @GetMapping("/")
    public String showAddressList(Model model) {
    	totalCount++;
    	model.addAttribute("addresss", addressBookRepository.findAll());
    	  
        return "index";
    }
    @GetMapping("/getByName/{name}")
    public String getbyName(@PathVariable("name") String name,Model model) {
    	totalCount++;    	
    	List<Address> listAddress = new ArrayList<Address>();
    	addressBookRepository.findAll().forEach(add -> listAddress.add(add));
    	List<Address> resultList = listAddress.stream().filter(address -> address.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
    	model.addAttribute("addresss", resultList);
        return "index";
    }
    
    @GetMapping("/addaddress")
    public String showAddressForm(Address address , Model model) {
    	totalCount++;    	
        return "add-address";
    }
    
    @PostMapping("/addaddress")
    public String addAddress(@Valid Address address, BindingResult result, Model model) {
    	totalCount++;
        if (result.hasErrors()) {
            return "add-address";
        }        
        addressBookRepository.save(address);        
        return "redirect:/";
    }
    
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
    	totalCount++;
    	Address address = addressBookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid address Id:" + id));
        model.addAttribute("address", address);                
        return "update-address";
    }
    
    @PostMapping("/update/{id}")
    public String updateAddress(@PathVariable("id") long id, @Valid Address addressBook, BindingResult result, Model model) {
    	totalCount++;
        if (result.hasErrors()) {
            addressBook.setId(id);
            return "update-address";
        }        
        addressBookRepository.save(addressBook);
        return "redirect:/";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteAddress(@PathVariable("id") long id, Model model) {
    	totalCount++;
        Address addressBook = addressBookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
        addressBookRepository.delete(addressBook);        
        return "redirect:/";
    }
    
    @GetMapping("/countTotal")
    @ResponseBody
    public  String totalCountOnpage(Model model) {

        return totalCount+"";
    }
    
}
