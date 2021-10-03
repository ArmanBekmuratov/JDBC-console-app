package com.arman.crud.model;
import liquibase.pro.packaged.S;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
   private Integer id;
   private String name;

   public Skill(String name) {
      this.name = name;
   }
}
