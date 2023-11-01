package lab3.lab3.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lab3.lab3.Model.Catagory;
import lab3.lab3.Model.Product;
import lab3.lab3.Service.ProductServ;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductServ productServ;

    @PostMapping("/")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product created = productServ.save(product);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable Long id) {
        Optional<Product> product = productServ.findById(id);
        return product.map(data -> new ResponseEntity<>(data, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/")
    public List<Product> get() {
        return productServ.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> edit(@PathVariable Long id, @RequestBody Product product) {
        try {
            Product updated = productServ.update(id, product);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        try {
            productServ.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/price")
    public List<Product> findByPriceGreaterThen(@RequestParam double minPrice) {
        return productServ.findByPriceGreaterThan(minPrice);
    }

    @GetMapping("/search/catagory")
    public List<Product> findByCatagoryAndPriceLessThan(@RequestParam Catagory cat, @RequestParam double maxPrice) {
        return productServ.findByCatagoryAndPriceLessThan(cat, maxPrice);
    }

    @GetMapping("/search/name")
    List<Product> findByNameContaining(@RequestParam String name) {
        return productServ.findByNameContaining(name);
    }

}
