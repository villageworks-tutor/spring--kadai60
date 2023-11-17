package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	// 更新画面表示
	@GetMapping("/items/{id}/edit")
	public String edit(
			@PathVariable("id") Integer id,
			Model model) {
		// パスパラメータをもとに更新対象商品を取得
		Item item = itemRepository.findById(id).get();
		// スコープに取得した商品を登録
		model.addAttribute("item", item);
		// 画面遷移
		return "editItem";
	}
	
	// 更新処理
	@PostMapping("/items/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam("categoryId") Integer categoryId,
			@RequestParam("name") String name,
			@RequestParam("price") Integer price,
			Model model) {
		// パスパラメータとリクエストパラメータをもとにItemクラスをインスタンス化
		Item item = new Item(id, categoryId, name, price);
		// 更新処理を実行
		itemRepository.save(item);
		// 画面遷移
		return "redirect:/items";
	}
	
	// 削除処理
	@PostMapping("/items/{id}/delete")
	public String delete(
			@PathVariable("id") Integer id, 
			Model model) {
		// 削除処理を実行
		itemRepository.deleteById(id);
		// 画面遷移
		return "redirect:/items";
	}
	
}
