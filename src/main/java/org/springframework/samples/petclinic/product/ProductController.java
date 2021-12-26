package org.springframework.samples.petclinic.product;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    
    @Autowired
    private ProductService productService;
   
    @ModelAttribute("types")
    public Collection<ProductType> populateProductTypes() {
	return productService.getAllProductTypes();
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
	dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/product/create")
    public String initCreationForm(Map<String, Object> model) {
	Product product = new Product();
	model.put("product", product);
	return "products/createOrUpdateProductForm";
    }

    @PostMapping("/product/create")
    public String processCreationForm(@Valid Product product, BindingResult result) {
	if(result.hasErrors()) {
	    return "products/createOrUpdateProductForm";
	} else {
	    productService.save(product);
	    return "welcome";
	}
    }
}
