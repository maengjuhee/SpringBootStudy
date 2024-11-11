package com.sist.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
// 클래스명과 테이블명이 동일하면 => table명을 지정하지 않아도 된다
@Entity
@Data
public class Emp {
	@Id
    private int empno;
	private String ename,job,hiredate,comm,mgr;
	private int sal;
	@Column(insertable = false,updatable = false)
	private int deptno;
	// csv => 날짜, 값 중에 null 값이 있는 경우 => 문자열 (text)
	@ManyToOne(fetch = FetchType.EAGER)
	// LAZE(지연), EAGER(즉시 로딩)
	@JoinColumn(name="deptno",referencedColumnName = "deptno")
	private Dept dept;
}
