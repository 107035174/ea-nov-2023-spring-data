package lab3.lab3.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lab3.lab3.Model.Catagory;
import lab3.lab3.Model.Product;
import lab3.lab3.Repository.ProductRepo;

@Service
public class ProductServ {
    @Autowired
    private ProductRepo productRepo;

    @Transactional
    public Product save(Product product) {
        return productRepo.save(product);
    }

    public Optional<Product> findById(Long id) {
        return productRepo.findById(id);
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Transactional
    public Product update(Long id, Product product) {
        Optional<Product> optionalToBeUpdated = productRepo.findById(id);
        if (optionalToBeUpdated.isPresent()) {
            Product toBeUpdated = optionalToBeUpdated.get();
            if (product.getName() != null) {
                toBeUpdated.setName(product.getName());
            }
            if (product.getPrice() != null) {
                toBeUpdated.setPrice(product.getPrice());
            }
            if (product.getRating() != null) {
                toBeUpdated.setRating(product.getRating());
            }
            return productRepo.save(toBeUpdated);
        } else {
            throw new IllegalArgumentException("Address with id " + id + " not found.");
        }
    }

    public void deleteById(Long id) {
        productRepo.deleteById(id);
    }

    public List<Product> findByPriceGreaterThan(double minPrice) {
        return productRepo.findByPriceGreaterThan(minPrice);
    }

    public List<Product> findByCatagoryAndPriceLessThan(Catagory cat, double maxPrice) {
        return productRepo.findByCatagoryAndPriceLessThan(cat, maxPrice);
    }

    public List<Product> findByNameContaining(String name) {
        return productRepo.findByNameContaining(name);
    }
}
