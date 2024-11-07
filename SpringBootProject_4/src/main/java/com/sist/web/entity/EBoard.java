package com.sist.web.entity;

import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.persistence.Id;
import lombok.Data;
// DB => @Entity , Elasticsearch => @Docuement 
@Document(indexName = "eboard")
@Data
public class EBoard {
  @Id // Primary Key => 검색 
  private int id;
  private int hit;
  private String name,subject,content,pwd,regdate;
}
