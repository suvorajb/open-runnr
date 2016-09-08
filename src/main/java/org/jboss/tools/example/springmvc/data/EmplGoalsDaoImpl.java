package org.jboss.tools.example.springmvc.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.tools.example.springmvc.model.EnrolledGoals;
import org.jboss.tools.example.springmvc.model.Goals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EmplGoalsDaoImpl implements EmplGoalsDao{
	
	@Autowired
	private EntityManager emhandler;
	
	@Override
	public List<Goals> getAllAvailblGoals() {

		CriteriaBuilder cb = emhandler.getCriteriaBuilder();
		CriteriaQuery<Goals> criteria = cb.createQuery(Goals.class);
		Root<Goals> goal = criteria.from(Goals.class);

		criteria.select(goal).orderBy(cb.asc(goal.get("goal_title")));

		return emhandler.createQuery(criteria).getResultList();

	}

	@Override
	public Goals viewGoalById(Long goalid) {
		return emhandler.find(Goals.class, goalid);
	}
	

	@Override
	public void submitEnrollmntForGoal(Long goalid, String empl_email, String empl_name, String empl_image) {
		Goals goal = this.viewGoalById(goalid);
		EnrolledGoals newenrolledgoal = new EnrolledGoals();
		
		newenrolledgoal.setEmpl_email(empl_email);
		newenrolledgoal.setEmpl_name(empl_name);
		newenrolledgoal.setEmpl_image(empl_image);
		
		newenrolledgoal.setChecklist1(goal.getChecklist1());
		newenrolledgoal.setChecklist2(goal.getChecklist2());
		newenrolledgoal.setChecklist3(goal.getChecklist3());
		
		newenrolledgoal.setGoal_title(goal.getGoal_title());
		newenrolledgoal.setGoal_desc(goal.getGoal_desc());
		newenrolledgoal.setGoal_category(goal.getGoal_category());
		newenrolledgoal.setEmpl_goal_start_date(new java.sql.Date(new java.util.Date().getTime()));
		newenrolledgoal.setSubmitted_for_review(0);
		
		emhandler.persist(newenrolledgoal);
	}

	@Override
	public List<EnrolledGoals> getAllMyEnrolledGoals(String empl_email) {
		
		CriteriaBuilder cb = emhandler.getCriteriaBuilder();
		CriteriaQuery<EnrolledGoals> criteria = cb.createQuery(EnrolledGoals.class);
		Root<EnrolledGoals> goal = criteria.from(EnrolledGoals.class);
		
		criteria.select(goal)
							.where(cb.equal(goal.get("empl_email"), empl_email))
							.where(cb.equal(goal.get("submitted_for_review"), 0));
		
		return emhandler.createQuery(criteria).getResultList();
	}

	@Override
	public EnrolledGoals getEnrolledGoal(Long enrolledgoalid) {
		return emhandler.find(EnrolledGoals.class, enrolledgoalid);
	}

	@Override
	public void submitEnrolledGoalForReviewByMngr(EnrolledGoals enrolledgoal) {
		emhandler.merge(enrolledgoal);
		//emhandler.persist(enrolledgoal);
	}

	@Override
	public List<EnrolledGoals> getAllCompletdGoals(String empl_email) {
	
		CriteriaBuilder cb = emhandler.getCriteriaBuilder();
		CriteriaQuery<EnrolledGoals> criteria = cb.createQuery(EnrolledGoals.class);
		Root<EnrolledGoals> goal = criteria.from(EnrolledGoals.class);
		
		criteria.select(goal)
							.where(cb.equal(goal.get("empl_email"), empl_email))
							.where(cb.equal(goal.get("flag_completed"), 1));
		
		return emhandler.createQuery(criteria).getResultList();
	
	}
}
