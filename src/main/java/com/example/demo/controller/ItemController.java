package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {
	
	@Autowired
	ItemRepository itemRepository;
	
	// 初期表示
	@GetMapping("/items")
	public String index(Model model) {
		// 商品の全検索を実行
		List<Item> list = itemRepository.findAll();
		// 商品リストをスコープに登録
		model.addAttribute("itemList", list);
		// 画面遷移
		return "items";
	}
	
	// 新規登録画面表示
	@GetMapping("/items/add")
	public String create() {
		return "addItem";
	}
	
}
