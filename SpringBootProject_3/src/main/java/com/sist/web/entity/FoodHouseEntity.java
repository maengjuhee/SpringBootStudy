package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
// findByFno(int fno)
@Entity(name="food_house")
@Data
public class FoodHouseEntity {
	@Id
	private int fno;
    private int jjimcount,likecount,hit,replycount; // 쓰지 않더라도 모두 추가해야 오류가 안 남
    private String name,phone,address,theme,poster,images,time,parking,content,rdays,type;
    private double score;
}
