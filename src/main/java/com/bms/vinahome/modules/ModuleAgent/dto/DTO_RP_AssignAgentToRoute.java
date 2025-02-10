package com.bms.vinahome.modules.ModuleAgent.dto;

import lombok.Data;

import java.util.List;

@Data
public class DTO_RP_AssignAgentToRoute {
    private Long id;
    private List<Long> routes;
}
