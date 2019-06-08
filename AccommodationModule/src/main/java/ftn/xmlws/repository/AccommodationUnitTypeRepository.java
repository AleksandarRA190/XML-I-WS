package ftn.xmlws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.xmlws.model.AccommodationUnitType;

@Repository
public interface AccommodationUnitTypeRepository extends JpaRepository<AccommodationUnitType, Long> {

}
