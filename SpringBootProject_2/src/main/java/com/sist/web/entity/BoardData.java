package com.sist.web.entity;
// SELECT no,subject,content,regdate,hit
public interface BoardData {
   public int getNo();
   public String getName();
   public String getSubject();
   public String getContent();
   public String getRegdate();
   public int getHit();
}
