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

import lab3.lab3.Model.Catagory;
import lab3.lab3.Service.CatagoryServ;

@RestController
@RequestMapping("/catagories")
public class CatagoryController {
    @Autowired
    private CatagoryServ catagoryServ;

    @PostMapping("/")
    public ResponseEntity<Catagory> create(@RequestBody Catagory catagory) {
        Catagory created = catagoryServ.save(catagory);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Catagory> get(@PathVariable Long id) {
        Optional<Catagory> catagory = catagoryServ.findById(id);
        return catagory.map(data -> new ResponseEntity<>(data, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/")
    public List<Catagory> get() {
        return catagoryServ.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Catagory> edit(@PathVariable Long id, @RequestBody Catagory catagory) {
        try {
            Catagory updated = catagoryServ.update(id, catagory);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        try {
            catagoryServ.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
