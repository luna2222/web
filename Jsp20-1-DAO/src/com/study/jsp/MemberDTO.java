package com.study.jsp;

public class MemberDTO {
	private String id;
	private String pw;
	private String name;
	private String phone;
	private String gender;
	
	public MemberDTO() {}
	
	public MemberDTO(String id, String pw, String name, String phone, String gender) {
		this.setId(id);
		this.setPw(pw);
		this.setName(name);
		this.setPhone(phone);
		this.setGender(gender);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
