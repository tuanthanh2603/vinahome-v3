package com.bms.vinahome.modules.ModuleTicket.dto;

import lombok.Data;

import java.util.List;
@Data
public class UpdateTicketsRequestDTO {
    private List<Long> ticketIds;
    private UpdateTicketResquestDTO commonData;
}
