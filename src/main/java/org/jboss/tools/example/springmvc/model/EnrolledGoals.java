package org.jboss.tools.example.springmvc.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "EnrolledGoals", uniqueConstraints = @UniqueConstraint(columnNames = {"empl_email", "goal_title"}))
public class EnrolledGoals implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    
    private String goal_title;
    private String goal_desc;
    private String goal_category;

    private String checklist1;
    private String answer1;

    private String checklist2;
    private String answer2;    

    private String checklist3;
    private String answer3;
    
    private String empl_name;
    private String empl_email;
    private String empl_image;
    
    private Date empl_goal_start_date;
    private Date empl_goal_end_date;
    
    private String empl_comments;
    
    private String mngr_comments;
    private int mngr_score_awarded;
    
    private int submitted_for_review;
    
    private int flag_completed;
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getChecklist2() {
		return checklist2;
	}

	public void setChecklist2(String checklist2) {
		this.checklist2 = checklist2;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getChecklist3() {
		return checklist3;
	}

	public void setChecklist3(String checklist3) {
		this.checklist3 = checklist3;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getEmpl_name() {
		return empl_name;
	}

	public void setEmpl_name(String empl_name) {
		this.empl_name = empl_name;
	}

	public String getEmpl_email() {
		return empl_email;
	}

	public void setEmpl_email(String empl_email) {
		this.empl_email = empl_email;
	}

	public String getEmpl_image() {
		return empl_image;
	}

	public void setEmpl_image(String empl_image) {
		this.empl_image = empl_image;
	}

	public Date getEmpl_goal_start_date() {
		return empl_goal_start_date;
	}

	public void setEmpl_goal_start_date(Date empl_goal_start_date) {
		this.empl_goal_start_date = empl_goal_start_date;
	}

	public Date getEmpl_goal_end_date() {
		return empl_goal_end_date;
	}

	public void setEmpl_goal_end_date(Date empl_goal_end_date) {
		this.empl_goal_end_date = empl_goal_end_date;
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

	public int getSubmitted_for_review() {
		return submitted_for_review;
	}

	public void setSubmitted_for_review(int submitted_for_review) {
		this.submitted_for_review = submitted_for_review;
	}

	public int getFlag_completed() {
		return flag_completed;
	}

	public void setFlag_completed(int flag_completed) {
		this.flag_completed = flag_completed;
	}
}
