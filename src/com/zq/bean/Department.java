package com.zq.bean;

public class Department implements java.io.Serializable{

	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int member_num;
	private String member;
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public int getId() {
		return id;
	}
	public int getMember_num() {
		return member_num;
	}
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
