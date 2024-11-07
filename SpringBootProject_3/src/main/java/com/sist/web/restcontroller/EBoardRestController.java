package com.sist.web.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;
import com.sist.web.dao.EBoardRepository;
import com.sist.web.entity.*;

import groovyjarjarantlr4.v4.parse.ANTLRParser.exceptionGroup_return;

import java.text.SimpleDateFormat;
import java.util.*;
@RestController // => 다른 프로그램/언어(자바 스크립트-ajax,react,vue 등)하고 연동할 때 사용
                // => 모바일 (kotlin, flutter)
                // => python
                // => 호환성 (모든 프로그램이 사용할 수 있는) : xml => json
                // => 자동으로 JSON 출력
                // => Rest API => GET(데이터 읽기 : SELECT) 
                // POST(데이터 추가) / PUT(데이터 수정) / DELETE(데이터 삭제)
@CrossOrigin(origins = "*")
public class EBoardRestController {
    @Autowired
    private EBoardRepository bDao;
    
    @GetMapping("eboard/list_react")
    public Map eboard_list(int page){
    	int rowSize=10;
    	Pageable pg=PageRequest.of(page-1, rowSize, Sort.by(Sort.Direction.DESC,"id"));
    	// 페이지 나누기 => Limit (Database) => ElasticSearch
    	Page<EBoard> pList=bDao.findAll(pg); // 정렬 후에 => 데이터를 10개
    	List<EBoard> list=new ArrayList<EBoard>();
    	if(pList!=null && pList.hasContent()) // 값 존재 확인
    	{
    		list=pList.getContent(); // page => List로 변환
    	}
    	
    	int count=(int)bDao.count();
    	int totalpage=(int)(Math.ceil(count/10.0));
    	Map map=new HashMap();
    	map.put("list", list);
    	map.put("curpage", page);
    	map.put("totalpage", totalpage);
    	map.put("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    	// new => 클래스 저장
    	return map;
    }
    
    @PostMapping("eboard/insert_react")
    public String eboard_insert(EBoard vo)
    {
    	String result="";
    	try
    	{
    		// 1. id, 2. hit, 3. regdate
    		vo.setHit(0);
    		vo.setId(idMaxData());
    		vo.setRegdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    		
    		bDao.save(vo);
    		
    		result="yes";
    	}catch(Exception ex)
    	{
    		result=ex.getMessage();
    	}
    	return result;
    }
    // 시퀀스에 해당하는 부분
    public int idMaxData() {
    	int max=0;
    	try
    	{
	    	int rowSize=10;
	    	int start=0;
	    	Pageable pg=PageRequest.of(start, rowSize, Sort.by(Sort.Direction.DESC,"id"));
	    	// 페이지 나누기 => Limit (Database) => ElasticSearch
	    	Page<EBoard> pList=bDao.findAll(pg); // 정렬 후에 => 데이터를 10개
	    	List<EBoard> list=new ArrayList<EBoard>();
	    	if(pList!=null && pList.hasContent()) // 값 존재 확인
	    	{
	    		list=pList.getContent(); 
	    	    max=list.get(0).getId();
	    	}
    	}catch(Exception ex)
    	{
    		max=0;
    	}
    	return max+1;
    }
    // 상세보기
    @GetMapping("eboard/detail_react")
    public EBoard eboard_detail(int id)
    {
    	EBoard vo=bDao.findById(id).get();
    	vo.setHit(vo.getHit()+1);
    	bDao.save(vo);
    	// 조회수 증가
    	vo=bDao.findById(id).get();
    	return vo;
    }
    
    @GetMapping("eboard/update_react")
    public EBoard eboard_update(int id)
    {
    	EBoard vo=bDao.findById(id).get();
    	
    	return vo;
    }
    @PostMapping("eboard/update_ok_react")
    public String board_update_ok(EBoard vo)
    {
    	String result="";
    	EBoard dbvo=bDao.findById(vo.getId()).get();
    	if(dbvo.getPwd().equals(vo.getPwd()))
    	{
    		vo.setHit(dbvo.getHit());
    		vo.setRegdate(dbvo.getRegdate());
    		bDao.save(vo); // 수정
    		result="yes";
    	}
    	else
    	{
    		result="no";
    	}
    	return result;
    }
    @GetMapping("eboard/delete_ok_react")
    public String eboard_delete(int id,String pwd)
    {
    	String result="";
    	EBoard vo=bDao.findById(id).get();
    	if(vo.getPwd().equals(pwd))
    	{
    		bDao.delete(vo);
    		result="yes";
    	}
    	else
    	{
    		result="no";
    	}
    	return result;
    }
}