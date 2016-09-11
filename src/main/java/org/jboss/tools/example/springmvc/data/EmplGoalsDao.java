package org.jboss.tools.example.springmvc.data;

import java.util.List;

import org.jboss.tools.example.springmvc.model.EnrolledGoals;
import org.jboss.tools.example.springmvc.model.Goals;

public interface EmplGoalsDao {

	public List<Goals> getAllAvailblGoals();
	
    public Goals viewGoalById(Long goalid);
    
    public void submitEnrollmntForGoal(Long goalid, String empl_email, String empl_name, String empl_image);
    
    
    
    public List<EnrolledGoals> getAllMyEnrolledGoals(String empl_email);
    
    public EnrolledGoals getEnrolledGoal(Long enrolledgoalid);
    
    public void submitEnrolledGoalForReviewByMngr(EnrolledGoals goal);
    
    public List<EnrolledGoals> getAllCompletdGoals(String empl_email);
}