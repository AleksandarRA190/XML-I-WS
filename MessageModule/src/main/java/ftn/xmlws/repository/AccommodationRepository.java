package ftn.xmlws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.xmlws.model.Accommodation;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

}
