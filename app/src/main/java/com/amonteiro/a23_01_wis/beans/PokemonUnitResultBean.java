package com.amonteiro.a23_01_wis.beans;

import java.util.List;

public class PokemonUnitResultBean{
	private int pageCount;
	private int page;
	private List<PokemonUnitBean> items;
	private int itemCount;

	public void setPageCount(int pageCount){
		this.pageCount = pageCount;
	}

	public int getPageCount(){
		return pageCount;
	}

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}

	public void setItems(List<PokemonUnitBean> items){
		this.items = items;
	}

	public List<PokemonUnitBean> getItems(){
		return items;
	}

	public void setItemCount(int itemCount){
		this.itemCount = itemCount;
	}

	public int getItemCount(){
		return itemCount;
	}
}