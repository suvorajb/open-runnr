package org.jboss.tools.example.springmvc.model;

import java.io.Serializable;
import java.util.List;

public class UsrInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String usrnm = "";
	private String usremail = "";
	private String usrimg = "";
	private boolean loginstatus;
	private boolean mngrflag;
	private List<EnrolledGoals> goals;
	private int totscore;
	

	
	@Override
	public String toString() {
		return "UsrInfo [usrnm=" + usrnm + ", usremail=" + usremail + ", usrimg=" + usrimg + ", loginstatus="
				+ loginstatus + ", mngrflag=" + mngrflag + ", goals=" + goals + ", totscore=" + totscore + "]";
	}
	public String getUsrnm() {
		return usrnm;
	}
	public void setUsrnm(String usrnm) {
		this.usrnm = usrnm;
	}
	public String getUsremail() {
		return usremail;
	}
	public void setUsremail(String usremail) {
		this.usremail = usremail;
	}
	public String getUsrimg() {
		return usrimg;
	}
	public void setUsrimg(String usrimg) {
		this.usrimg = usrimg;
	}
	public boolean isLoginstatus() {
		return loginstatus;
	}
	public void setLoginstatus(boolean loginstatus) {
		this.loginstatus = loginstatus;
	}
	public boolean isMngrflag() {
		return mngrflag;
	}
	public void setMngrflag(boolean mngrflag) {
		this.mngrflag = mngrflag;
	}
	public List<EnrolledGoals> getGoals() {
		return goals;
	}
	public void setGoals(List<EnrolledGoals> goals) {
		this.goals = goals;
	}
	public int getTotscore() {
		return totscore;
	}
	public void setTotscore(int totscore) {
		this.totscore = totscore;
	}
}
