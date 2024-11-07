package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name="recipe")
@Data
public class RecipeEntity {
	@Id
    private int no;
    private String title,poster,chef,link;
    private int hit;
}
