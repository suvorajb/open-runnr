package org.jboss.tools.example.springmvc.form;

import java.io.Serializable;

public class LoginForm implements Serializable{
	
	private String emailid;
	private String password;
	
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
