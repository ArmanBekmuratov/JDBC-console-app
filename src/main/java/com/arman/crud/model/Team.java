package com.arman.crud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    private Integer id;
    private String name;
    private List<Developer> developers;
    private TeamStatus teamStatus;


}
