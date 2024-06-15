package com.sri.springboot.mydiary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sri.springboot.mydiary.entity.Entry;
import com.sri.springboot.mydiary.repository.EntryRepository;

@Service
public class EntryServiceImpl implements EntryService {

	@Autowired
	 private EntryRepository entryRepository;
	@Override
	public Entry saveEntry(Entry entry) {
		return entryRepository.save(entry);
	}

	@Override
	public Entry updateEntry(Entry entry) {
		return entryRepository.save(entry);
	}

	@Override
	public void deleteEntry(Entry entry) {
		 entryRepository.delete(entry);
	}

	@Override
	public Entry insertEntry(Entry entry) {
		return entryRepository.save(entry);
	}

	@Override
	public Entry findbyIdEntry(long id) {
		return entryRepository.findById(id).get();
	}

	@Override
	public List<Entry> findAll() {
		return entryRepository.findAll();
	}

	@Override
	public List<Entry> findByUserId(long id) {
		return entryRepository.findByUserId(id);
	}

}
  