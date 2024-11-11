package com.sist.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.entity.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
@Controller
public class EmpController {
    @Autowired
    private EntityManager em;
    
    @GetMapping("/emp/list")
    public String emp_list(Model model)
    {
    	// JPQL => JPA 제공하는 SQL => 클래스 객체를 이용해서 JOIN
    	String sql="SELECT s FROM Emp s join fetch s.dept";
    	TypedQuery<Emp> query=em.createQuery(sql, Emp.class);
    	List<Emp> list=query.getResultList();
    	model.addAttribute("list", list);
    	return "emp/list";
    }
    @GetMapping("/emp/detail")
    public String emp_detail(int empno,Model model)
    {
    	String sql="SELECT s FROM Emp s JOIN s.dept d WHERE s.empno=:empno";
    	Emp e=em.createQuery(sql, Emp.class).setParameter("empno", empno)
    			.getSingleResult();
    	model.addAttribute("vo", e);
    	return "emp/detail";
    }
}