package com.bms.vinahome.modules.ModuleTicket.service;

import com.bms.vinahome.exception.AppException;
import com.bms.vinahome.exception.ErrorCode;
import com.bms.vinahome.modules.ModuleTicket.dto.DTO_RP_Ticket;
import com.bms.vinahome.modules.ModuleTicket.entity.Ticket;
import com.bms.vinahome.modules.ModuleTicket.mapper.TicketMapper;
import com.bms.vinahome.modules.ModuleEmployee.repository.EmployeeRepository;
import com.bms.vinahome.modules.ModuleOffice.repository.OfficeRepository;
import com.bms.vinahome.modules.ModuleTicket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    OfficeRepository officeRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    public List<DTO_RP_Ticket> getListTicketByTripId(Long tripId) {
        if (tripId == null) {
            throw new AppException(ErrorCode.INVALID_TRIP_ID);
        }
        List<Ticket> tickets= ticketRepository.findAllByTripId(tripId);
        return tickets.stream()
                .map(TicketMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

//    public List<TicketResponseDTO> getListTicketByTripId(Long tripId) {
//        List<Ticket> tickets = ticketRepository.findAllByTripId(tripId);
//        return tickets.stream()
//                .map(TicketMapper::toResponseDTO)
//                .collect(Collectors.toList());
//    }
//
//
//
//
//    public List<TicketResponseDTO> updateTickets(List<Long> ticketIds, UpdateTicketResquestDTO commonData) {
//        System.out.println(ticketIds);
//        System.out.println(commonData);
//        List<TicketResponseDTO> updatedTickets = new ArrayList<>();
//        for (Long ticketId : ticketIds) {
//            Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
//            if (ticketOptional.isPresent()) {
//                Ticket ticket = ticketOptional.get();
//                ticket.setTicketPhone(commonData.getTicketPhone());
//                ticket.setCustomerName(commonData.getCustomerName());
//                ticket.setTicketPointUp(commonData.getTicketPointUp());
//                ticket.setTicketPointDown(commonData.getTicketPointDown());
//                ticket.setTicketNote(commonData.getTicketNote());
//                ticket.setPaymentType(commonData.getPaymentType());
//                ticket.setTicketPrice(commonData.getTicketPrice());
//                ticket.setBookingStatus(true);
//                Optional<Office> officeOptional = officeRepository.findById(commonData.getOfficeId());
//                officeOptional.ifPresent(ticket::setOffice);
//                Optional<Employee> employeeOptional = employeeRepository.findById(commonData.getEmployeeId());
//                employeeOptional.ifPresent(ticket::setEmployee);
//
//                // Lưu lại thay đổi
//                Ticket updatedTicket = ticketRepository.save(ticket);
//                updatedTickets.add(TicketMapper.toResponseDTO(updatedTicket));
//            }
//        }
//
//        return updatedTickets;
//    }
}
