package com.sri.springboot.mydiary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sri.springboot.mydiary.entity.Entry;

public interface EntryRepository extends JpaRepository<Entry, Long> {
	@Query(value="select * from entries where id=:id",nativeQuery = true)
	public List<Entry> findByUserId(long id);
}
