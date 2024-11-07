package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/*
 *  CHEF text 
	POSTER text 
	MEM_CONT1 text 
	MEM_CONT3 text 
	MEM_CONT7 text 
	MEM_CONT2 text
 */
@Entity(name="chef")
@Data
public class ChefEntity {
   @Id
   private String chef;
   private String poster;
   private String mem_cont1,mem_cont3,mem_cont7,mem_cont2;
}
