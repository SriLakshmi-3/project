package com.sri.springboot.mydiary.service;

import java.util.List;

import com.sri.springboot.mydiary.entity.Entry;

public interface EntryService {

	public Entry saveEntry(Entry entry);
	public Entry updateEntry(Entry entry);
	public void deleteEntry(Entry entry);
	public Entry insertEntry(Entry entry);
	public Entry findbyIdEntry(long id);
	public List<Entry> findAll();
	public List<Entry> findByUserId(long id);
}
