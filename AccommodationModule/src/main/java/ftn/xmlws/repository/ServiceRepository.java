package ftn.xmlws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.xmlws.model.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

}
