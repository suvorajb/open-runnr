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
public class MngrGoalsDaoImpl implements MngrGoalsDao {

	@Autowired
	private EntityManager em;

	@Override
	public List<Goals> getAllGoalsByCategory(String category) {
		return null;
	}

	@Override
	public Goals findByGoalId(Long id) {
		return em.find(Goals.class, id);
	}

	public void saveGoal(Goals goal) {
		em.persist(goal);
	}

	@Override
	public List<Goals> getAllGoals() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Goals> criteria = cb.createQuery(Goals.class);
		Root<Goals> goal = criteria.from(Goals.class);

		criteria.select(goal).orderBy(cb.asc(goal.get("goal_title")));

		return em.createQuery(criteria).getResultList();

	}

	@Override
	public void sveGoalByMngr(Goals goal) {
		em.persist(goal);
	}

	@Override
	public List<EnrolledGoals> getAllGoalsSubmittedForReview() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<EnrolledGoals> criteria = cb.createQuery(EnrolledGoals.class);
		Root<EnrolledGoals> reviewgoal = criteria.from(EnrolledGoals.class);

		criteria.select(reviewgoal).where(cb.equal(reviewgoal.get("submitted_for_review"), 1));
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public EnrolledGoals getGoalSubmttdRvw(Long id) {
		return em.find(EnrolledGoals.class, id);
	}

	@Override
	public void submitAppraisal(EnrolledGoals goal) {
		em.merge(goal);
	}

	@Override
	public List<EnrolledGoals> getAllCompletdGoals(String empl_email) {
	
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<EnrolledGoals> criteria = cb.createQuery(EnrolledGoals.class);
		Root<EnrolledGoals> goal = criteria.from(EnrolledGoals.class);
		
		criteria.select(goal)
				.where(cb.equal(goal.get("empl_email"), empl_email))
				.where(cb.equal(goal.get("flag_completed"), 1));
		
		return em.createQuery(criteria).getResultList();
	
	}
}
