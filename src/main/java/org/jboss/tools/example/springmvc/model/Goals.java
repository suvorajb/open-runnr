package org.jboss.tools.example.springmvc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Goals", uniqueConstraints = @UniqueConstraint(columnNames = "goal_title"))
public class Goals implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    private String goal_title;

    private String goal_desc;
    
    @NotNull
    private String goal_category;

    private String checklist1;

    private String checklist2;

    private String checklist3;

    
    
	@Override
	public String toString() {
		return "Goals [id=" + id + ", goal_title=" + goal_title + ", goal_desc=" + goal_desc + ", goal_category="
				+ goal_category + ", checklist1=" + checklist1 + ", checklist2=" + checklist2 + ", checklist3="
				+ checklist3 + "]";
	}

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
}
