package org.jboss.tools.example.springmvc.form;

import java.io.Serializable;

public class GoalForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long goalid;
	
	private String goal_title;

    private String goal_desc;
    
    private String goal_category;

    private String checklist1;
    private String answer1;

    private String checklist2;
    private String answer2;    

    private String checklist3;
    private String answer3;
    
    private String mngr_comments;
    private int mngr_score_awarded;
    

	@Override
	public String toString() {
		return "GoalForm [goalid=" + goalid + ", goal_title=" + goal_title + ", goal_desc=" + goal_desc
				+ ", goal_category=" + goal_category + ", checklist1=" + checklist1 + ", answer1=" + answer1
				+ ", checklist2=" + checklist2 + ", answer2=" + answer2 + ", checklist3=" + checklist3 + ", answer3="
				+ answer3 + "]";
	}

	public long getGoalid() {
		return goalid;
	}

	public void setGoalid(long goalid) {
		this.goalid = goalid;
	}

	public String getGoal_title() {
		return goal_title;
	}

	public void setGoal_title(String goal_title) {
		this.goal_title = goal_title;
	}

	public String getGoal_desc() {
		return goal_desc;
	}

	public void setGoal_desc(String goal_desc) {
		this.goal_desc = goal_desc;
	}

	public String getGoal_category() {
		return goal_category;
	}

	public void setGoal_category(String goal_category) {
		this.goal_category = goal_category;
	}

	public String getChecklist1() {
		return checklist1;
	}

	public void setChecklist1(String checklist1) {
		this.checklist1 = checklist1;
	}

	public String getChecklist2() {
		return checklist2;
	}

	public void setChecklist2(String checklist2) {
		this.checklist2 = checklist2;
	}

	public String getChecklist3() {
		return checklist3;
	}

	public void setChecklist3(String checklist3) {
		this.checklist3 = checklist3;
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
