package com.bms.vinahome.modules.ModuleSeat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DTO_RQ_EditSeat {
    Long id;
    Integer floor;
    Integer row;
    Integer column;
    String code;
    String name;
    Integer type;
}
