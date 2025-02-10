package com.bms.vinahome.modules.ModuleSeat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DTO_RQ_CreateSeatChart {
    String name;
    Integer floor;
    Integer row;
    Integer column;
    List<DTO_RQ_CreateSeat> seats;
    Long companyId;
}
