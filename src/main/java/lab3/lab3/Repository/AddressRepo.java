package lab3.lab3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lab3.lab3.Model.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {

}
