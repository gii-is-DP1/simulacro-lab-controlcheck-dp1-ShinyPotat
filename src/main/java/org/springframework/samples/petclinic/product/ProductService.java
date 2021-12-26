package org.springframework.samples.petclinic.product;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsCheaperThan(double price) {
        return productRepository.findByPriceLessThan(price);
    }

    public ProductType getProductType(String typeName) {
        return productRepository.getProductType(typeName);
    }

    public Collection<ProductType> getAllProductTypes() {
	return productRepository.findAllProductTypes();
    }
    
    @Transactional
    public Product save(Product p) {
        productRepository.save(p);
	return p;
    }

}
