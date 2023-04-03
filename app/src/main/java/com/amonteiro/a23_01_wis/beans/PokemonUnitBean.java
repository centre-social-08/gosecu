package com.amonteiro.a23_01_wis.beans;

public class PokemonUnitBean {
	private String name;
	private String id;
	private String damageType;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setDamageType(String damageType){
		this.damageType = damageType;
	}

	public String getDamageType(){
		return damageType;
	}
}
