package ca.lambtoncollege.addressbook.entity.repository;

import ca.lambtoncollege.addressbook.entity.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {    
}
