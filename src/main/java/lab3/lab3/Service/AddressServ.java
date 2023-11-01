package lab3.lab3.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lab3.lab3.Model.Address;
import lab3.lab3.Repository.AddressRepo;

@Service
public class AddressServ {
    @Autowired
    private AddressRepo addressRepo;

    @Transactional
    public Address save(Address address) {
        return addressRepo.save(address);
    }

    public Optional<Address> findById(Long id) {
        return addressRepo.findById(id);
    }

    public List<Address> findAll() {
        return addressRepo.findAll();
    }

    @Transactional
    public Address update(Long id, Address address) {
        Optional<Address> optionalToBeUpdated = addressRepo.findById(id);
        if (optionalToBeUpdated.isPresent()) {
            Address toBeUpdated = optionalToBeUpdated.get();
            if (address.getCity() != null) {
                toBeUpdated.setCity(address.getCity());
            }
            if (address.getStreet() != null) {
                toBeUpdated.setStreet(address.getStreet());
            }
            if (address.getZip() != null) {
                toBeUpdated.setZip(address.getZip());
            }
            return addressRepo.save(toBeUpdated);
        } else {
            throw new IllegalArgumentException("Address with id " + id + " not found.");
        }
    }

    public void deleteById(Long id) {
        addressRepo.deleteById(id);
    }
}
