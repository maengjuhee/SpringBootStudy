package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;

import com.sist.web.entity.FoodHouseData;
import com.sist.web.entity.FoodHouseEntity;
/*
 *   MySql
 *    1. 페이징 => LIMIT 시작, 갯수
 *    2. LIKE => '%'||?||'%' => CONCAT('%',?,'%')
 *    3. DATE => DATETIME => sysdate : now()
 *    4. NVL => isnull
 *    
 *    => 오라클     mysql(mariadb) => 3306(driver 동일)
 *       NUMBER     int, double
 *       VARCHAR2   varchar
 *       CLOB       text
 *       DATE       datatime
 */
@Repository // 메모리 할당
public interface FoodHouseDAO extends JpaRepository<FoodHouseEntity, Integer>{
    // 목록 출력
	@Query(value = "SELECT fno,poster,name FROM food_house ORDER BY fno ASC "
			+"LIMIT :start,12",nativeQuery = true) // 모두 받으려면 Entity / 원하는 것만 가져오려면 Data로 받아와
	                                               // nativeQuery = true 꼭 써줘야 해
	// #{start} => : 로 바뀜
	public List<FoodHouseData> foodListData(@Param("start") int start);
	// 상세보기
	public FoodHouseEntity findByFno(int fno); // => SELECT * FROM food_house WHERE fno=? 자동 생성
	// Hit 증가 = UPDATE (save() 함수로 되어 있어 SQL 안 써)
	// 검색 ...
	// CRUD => 게시판
	
}
