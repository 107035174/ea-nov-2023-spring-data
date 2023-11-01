package lab3.lab3.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lab3.lab3.Model.Review;
import lab3.lab3.Repository.ReviewRepo;

@Service
public class ReviewServ {
    @Autowired
    private ReviewRepo reviewRepo;

    @Transactional
    public Review save(Review review) {
        return reviewRepo.save(review);
    }

    public Optional<Review> findById(Long id) {
        return reviewRepo.findById(id);
    }

    public List<Review> findAll() {
        return reviewRepo.findAll();
    }

    @Transactional
    public Review update(Long id, Review review) {
        Optional<Review> optionalToBeUpdated = reviewRepo.findById(id);
        if (optionalToBeUpdated.isPresent()) {
            Review toBeUpdated = optionalToBeUpdated.get();
            if (review.getComment() != null) {
                toBeUpdated.setComment(review.getComment());
            }
            return reviewRepo.save(toBeUpdated);
        } else {
            throw new IllegalArgumentException("Catagory with id " + id + " not found.");
        }
    }

    public void deleteById(Long id) {
        reviewRepo.deleteById(id);
    }

    public List<Review> findByProductId(Long id) {
        return reviewRepo.findByProductId(id);
    }
}
