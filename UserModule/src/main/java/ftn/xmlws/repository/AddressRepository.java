package ftn.xmlws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.xmlws.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
