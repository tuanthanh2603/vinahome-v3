package com.bms.vinahome.modules.ModuleTicket.repository;

import com.bms.vinahome.modules.ModuleTicket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findAllByTripId(Long tripId);
}
