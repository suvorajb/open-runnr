package org.jboss.tools.example.springmvc.data;

import java.util.List;

import org.jboss.tools.example.springmvc.model.EnrolledGoals;
import org.jboss.tools.example.springmvc.model.Goals;

public interface MngrGoalsDao {
	
	public List<Goals> getAllGoals();
	
    public List<Goals> getAllGoalsByCategory(String category);

    public Goals findByGoalId(Long id);
    
    public void sveGoalByMngr(Goals goal);
    
    public List<EnrolledGoals> getAllGoalsSubmittedForReview();
    
    public EnrolledGoals getGoalSubmttdRvw(Long id);
    
    public void submitAppraisal(EnrolledGoals goal);
    
    public List<EnrolledGoals> getAllCompletdGoals(String empl_email);
}