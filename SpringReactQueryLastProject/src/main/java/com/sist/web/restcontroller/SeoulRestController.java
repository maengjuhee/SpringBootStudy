package com.sist.web.restcontroller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.*;
import com.sist.web.dao.*;
@RestController
@CrossOrigin(origins = "*")
public class SeoulRestController {
	@Autowired
    private SeoulDAO sDao;
	
	@GetMapping("/seoul/list/{page}")
	public ResponseEntity<Map> seoul_list(@PathVariable("page") int page)
	{
		Map map=new HashMap();
		try
		{
			// SQL 문장없이 처리 과정
			int rowSize=12;
			Pageable pg=PageRequest.of(page-1, rowSize,Sort.by(Sort.Direction.DESC,"no"));
			Page<SeoulEntity> pList=sDao.findAll(pg);
			
			List<SeoulEntity> list=new ArrayList<SeoulEntity>();
			if(pList!=null && pList.hasContent())
			{
				list=pList.getContent();
			}
			
			//총페이지
			int totalpage=(int)(Math.ceil(sDao.count()/12.0));
			
			final int BLOCK=10;
			int startPage=((page-1)/BLOCK*BLOCK)+1;
			int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
			
			if(endPage>totalpage)
				endPage=totalpage;
			
			map.put("sList", list);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
		
			
		}catch(Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			// onError:(res) => {}
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
		// onSuccess:(res) => {}
	}
}
