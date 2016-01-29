package com.lanbots.sqlite02;

public class Student {
	
	public int id;
	public String name;
	public int age;
	public String sex;
	
	public Student(){
		
	}
	
	public Student(String name, int age, String sex) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	public Student(int id, String name, int age, String sex){
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
	}



}
