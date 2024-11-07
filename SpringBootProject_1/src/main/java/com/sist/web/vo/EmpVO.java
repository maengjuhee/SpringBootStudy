package com.sist.web.vo;
import java.util.*;

import lombok.Data;
// Spring => Spring-Boot
// 최대한 : XML파일이 없게 => 어노테이션
// FrameWork => Spring-Boot에 포함
// => 소스를 최소화
@Data
public class EmpVO {
   private int empno,sal;
   private String ename,job,dbday;
   private Date hiredate;
}
