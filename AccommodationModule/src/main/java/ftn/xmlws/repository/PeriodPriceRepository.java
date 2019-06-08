package ftn.xmlws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.xmlws.model.PeriodPrice;

@Repository
public interface PeriodPriceRepository extends JpaRepository<PeriodPrice, Long> {

}
