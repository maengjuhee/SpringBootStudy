package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.ChefEntity;

@Repository
public interface ChefDAO extends JpaRepository<ChefEntity, String>{
   public ChefEntity findByChef(String chef);
}
