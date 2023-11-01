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
import org.springframework.web.bind.annotation.RestController;

import lab3.lab3.Model.Review;
import lab3.lab3.Service.ReviewServ;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewServ reviewServ;

    @PostMapping("/")
    public ResponseEntity<Review> create(@RequestBody Review review) {
        Review created = reviewServ.save(review);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> get(@PathVariable Long id) {
        Optional<Review> review = reviewServ.findById(id);
        return review.map(data -> new ResponseEntity<>(data, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/")
    public List<Review> get() {
        return reviewServ.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> edit(@PathVariable Long id, @RequestBody Review review) {
        try {
            Review updated = reviewServ.update(id, review);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        try {
            reviewServ.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{productId}")
    public List<Review> findByProductId(@PathVariable Long productId) {
        return reviewServ.findByProductId(productId);
    }
}
