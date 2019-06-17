package ftn.xmlws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.xmlws.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{

	List<Message> findAllByReservation_id(Long reservation);
	
	
}
