package com.sist.web.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.entity.*;
import com.sist.web.dao.*;

@Controller
public class FoodController {
	@Autowired
    private FoodHouseDAO dao;
	
	@GetMapping("/")
	public String food_main(String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(rowSize*curpage)-rowSize; // rownum=1 , limit=0 부터 시작
		List<FoodHouseData> list=dao.foodListData(start);
		// => count() : SELECT COUNT(*) FROM food_house
		int count=(int)dao.count();
		int totalpage=(int)(Math.ceil(count/12.0));
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "main";
	}
	
	@GetMapping("/food/detail")
	public String food_detail(int fno,Model model)
	{
		FoodHouseEntity vo=dao.findByFno(fno);
		model.addAttribute("vo", vo);
		return "food/detail";
	}
}
