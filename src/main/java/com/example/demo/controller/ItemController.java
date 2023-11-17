package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	// 新規登録処理（［登録］ボタン押下時）
	@PostMapping("/items/add")
	public String store(
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "price", defaultValue = "") Integer price,
			Model model) {
		// リクエストパラメータをもとにしたItemクラスをインスタンス化
		Item item = new Item(categoryId, name, price);
		// 商品を登録
		itemRepository.save(item);
		// 画面遷移（URL「/items」へのリダイレクト）
		return "redirect:/items";
	}
	
}
