package spring;

import java.time.LocalDateTime;

public class Member {
	private Long id;
	private String email;
	private String password;
	private String name;
	private LocalDateTime registerDateTime;
	
	public Member() {}
	
	public Member(String email, String password, String name, LocalDateTime registerDateTime) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.registerDateTime = registerDateTime;
	}
	//set
	void setId(Long id) { this.id = id; }
	
	//get
	public Long getId()         				{ return this.id; }
	public String getEmail()    				{ return email; }
	public String getPassword() 				{ return password; }
	public String getName()     				{ return name; }
	public LocalDateTime getRegisterDateTime()  { return registerDateTime; }
	
	public void changePassword(String oldPassword, String newPassword) {
		if(!this.password.equals(oldPassword)) {
			throw new WrongPasswordException();
		}
		this.password = newPassword;
	}
	
	
}
