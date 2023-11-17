package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "category_id")
	private Integer categoryId;
	
	private String name;
	private Integer price;
	
	/**
	 * デフォルトコンストラクタ
	 */
	public Item() {}
	
	/**
	 * コンストラクタ：新規追加対象のインスタンス化用
	 * @param categoryId
	 * @param name
	 * @param price
	 */
	public Item(Integer categoryId, String name, Integer price) {
		this.categoryId = categoryId;
		this.name = name;
		this.price = price;
	}
	
	/**
	 * コンストラクタ：更新対象のインスタンス化用
	 * @param id
	 * @param categoryId
	 * @param name
	 * @param price
	 */
	public Item(Integer id, Integer categoryId, String name, Integer price) {
		this(categoryId, name, price);
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public String getName() {
		return name;
	}
	public Integer getPrice() {
		return price;
	}

}
