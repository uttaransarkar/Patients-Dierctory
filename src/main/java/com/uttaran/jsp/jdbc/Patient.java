package com.uttaran.jsp.jdbc;

public class Patient {
	
	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private String gender;
	private String pres;
	private String status;
	private String time;
	
	public Patient(int id, String time, String firstName, String lastName, int age, String gender, String pres, String status) {
		super();
		this.id = id;
		this.time = time;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.pres = pres;
		this.status = status;
	}

	public Patient(String time, String firstName, String lastName, int age, String gender, String pres, String status) {
		super();
		this.time = time;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.pres = pres;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPres() {
		return pres;
	}

	public void setPres(String pres) {
		this.pres = pres;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", gender=" + gender + ", pres=" + pres + ", status=" + status + "]";
	}

}
