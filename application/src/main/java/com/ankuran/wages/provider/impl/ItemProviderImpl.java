package com.ankuran.wages.provider.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ankuran.wages.mapper.ItemMapper;
import com.ankuran.wages.provider.ItemProvider;
import com.ankuran.wages.repository.ItemRepository;

@Component
public class ItemProviderImpl implements ItemProvider{

	@Autowired
	ItemMapper itemMapper;
	
	@Autowired
	ItemRepository itemRepository;
	
}
