package com.taotao.pojo;

import java.io.Serializable;

/**
 * 
 
select 
a.id,
a.title,
a.sell_point,
a.image,
a.price ,
b.name,
c.item_desc 

from tb_item a
left join tb_item_cat b on a.cid = b.id
left join tb_item_desc c on a.id = c.item_id
where a.status = 1
 *
 */
public class SearchItem  implements Serializable{
	private String id;
	private String  title;
	private String sell_point;
	private String image;
	private long price;
	private String name;
	private String item_desc;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSell_point() {
		return sell_point;
	}
	public void setSell_point(String sell_point) {
		this.sell_point = sell_point;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getItem_desc() {
		return item_desc;
	}
	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	} 
	
	
}
