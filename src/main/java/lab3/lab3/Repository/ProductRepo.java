package lab3.lab3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lab3.lab3.Model.Catagory;
import lab3.lab3.Model.Product;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByPriceGreaterThan(double minPrice);
    List<Product> findByCatagoryAndPriceLessThan(Catagory cat, double maxPrice);
    List<Product> findByNameContaining(String name);
}
