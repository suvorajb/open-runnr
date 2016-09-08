package org.jboss.tools.example.springmvc.form;

import java.io.Serializable;

public class EnrolledGoalForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private long goalid;

	private String answer1;
	private String answer2;
	private String answer3;

	private String empl_comments;

	private String mngr_comments;

	private int mngr_score_awarded;

	@Override
	public String toString() {
		return "EnrolledGoalForm [goalid=" + goalid + ", answer1=" + answer1 + ", answer2=" + answer2 + ", answer3="
				+ answer3 + ", empl_comments=" + empl_comments + ", mngr_comments=" + mngr_comments
				+ ", mngr_score_awarded=" + mngr_score_awarded + "]";
	}

	public long getGoalid() {
		return goalid;
	}

	public void setGoalid(long goalid) {
		this.goalid = goalid;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getEmpl_comments() {
		return empl_comments;
	}

	public void setEmpl_comments(String empl_comments) {
		this.empl_comments = empl_comments;
	}

	public String getMngr_comments() {
		return mngr_comments;
	}

	public void setMngr_comments(String mngr_comments) {
		this.mngr_comments = mngr_comments;
	}

	public int getMngr_score_awarded() {
		return mngr_score_awarded;
	}

	public void setMngr_score_awarded(int mngr_score_awarded) {
		this.mngr_score_awarded = mngr_score_awarded;
	}

}
