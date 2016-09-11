package org.jboss.tools.example.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.tools.example.springmvc.data.EmplGoalsDao;
import org.jboss.tools.example.springmvc.form.EnrolledGoalForm;
import org.jboss.tools.example.springmvc.form.GoalForm;
import org.jboss.tools.example.springmvc.model.EnrolledGoals;
import org.jboss.tools.example.springmvc.model.Goals;
import org.jboss.tools.example.springmvc.model.UsrInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmplGoalController {

	@Autowired
	private EmplGoalsDao emplGoalDao;
	
	@RequestMapping(value = "/employee/allgoals", method = RequestMethod.GET)
	public String displayEmplAllAvlblGoals(HttpServletRequest req, Model uiModel) {
		List<Goals> goals = emplGoalDao.getAllAvailblGoals();
		
		uiModel.addAttribute("goals", goals);
		return "empl_view_all_goals";
	}
	
	
	@RequestMapping(value = "/employee/mygoals", method = RequestMethod.GET)
	public String displayMyEnrolledGoals(HttpServletRequest req, Model uiModel) {
		UsrInfo uinf = (UsrInfo)req.getSession().getAttribute("uinfo");
		
		List<EnrolledGoals> mygoals = emplGoalDao.getAllMyEnrolledGoals(uinf.getUsremail());
		
		uiModel.addAttribute("mygoals", mygoals);
		return "empl_mygoals";
	}
	
	@RequestMapping(value = "/employee/viewgoal", method = RequestMethod.GET)
	public String displayViewGoalPage(HttpServletRequest req, Model uiModel) {
		long goalid = Long.valueOf(req.getParameter("goalid"));
		
		Goals goal = emplGoalDao.viewGoalById(goalid);
		GoalForm goalform = new GoalForm();
		
		goalform.setGoalid(goal.getId());
		goalform.setGoal_title(goal.getGoal_title());
		goalform.setGoal_desc(goal.getGoal_desc());

		uiModel.addAttribute("goal", goal);
		uiModel.addAttribute("goalform", goalform);
		
		return "empl_viewgoal";
	}
	
	@RequestMapping(value = "/employee/viewmygoal", method = RequestMethod.GET)
	public String displayMyEnrolledGoal(HttpServletRequest req, Model uiModel) {
		//UsrInfo uinf = (UsrInfo)req.getSession().getAttribute("uinfo");
		long enrolledgoalid = Long.valueOf(req.getParameter("enrolledgoalid"));
		
		EnrolledGoals myenrolledgoal = emplGoalDao.getEnrolledGoal(enrolledgoalid);
		EnrolledGoalForm enrolledform = new EnrolledGoalForm();
		enrolledform.setGoalid(enrolledgoalid);
		
		uiModel.addAttribute("myenrolledgoal", myenrolledgoal);
		uiModel.addAttribute("enrolledform", enrolledform);
		
		return "empl_view_enrolledgoal";
	}
	
	@RequestMapping(value = "/employee/enrollgoal", method = RequestMethod.POST)
	public String saveGoalByManager(@ModelAttribute GoalForm goalform, BindingResult result, Model uiModel,
			HttpServletRequest req) {

		Long goalid = goalform.getGoalid();

		System.out.println(goalid);
		
		UsrInfo uinfo = (UsrInfo)req.getSession().getAttribute("uinfo");
		emplGoalDao.submitEnrollmntForGoal(goalid, uinfo.getUsremail(), uinfo.getUsrnm(), uinfo.getUsrimg());

		return "redirect:/employee/mygoals";
	}
	
	@RequestMapping(value = "/employee/mngrreview", method = RequestMethod.POST)
	public String submitEnrlldGoalForMngrRevw(@ModelAttribute EnrolledGoalForm enrolledform, BindingResult result, Model uiModel,
			HttpServletRequest req) {

		Long enrolledgoalid = enrolledform.getGoalid();

		System.out.println(enrolledform);
		
		// get the enrolled goal first
		EnrolledGoals myenrolledgoal = emplGoalDao.getEnrolledGoal(enrolledgoalid);
		
		//update the fields updated by employee like checkpoint answers & comments
		myenrolledgoal.setAnswer1(enrolledform.getAnswer1());
		myenrolledgoal.setAnswer2(enrolledform.getAnswer2());
		myenrolledgoal.setAnswer3(enrolledform.getAnswer3());
		myenrolledgoal.setEmpl_comments(enrolledform.getEmpl_comments());
		myenrolledgoal.setEmpl_goal_end_date(new java.sql.Date(new java.util.Date().getTime()));
		myenrolledgoal.setSubmitted_for_review(1);
		
		emplGoalDao.submitEnrolledGoalForReviewByMngr(myenrolledgoal);

		return "redirect:/employee/mygoals";
	}


	@RequestMapping(value = "/employee/completedgoals", method = RequestMethod.GET)
	public String displayCompletdGoals(HttpServletRequest req, Model uiModel) {
		UsrInfo uinf = (UsrInfo)req.getSession().getAttribute("uinfo");
		
		List<EnrolledGoals> completedgoals = emplGoalDao.getAllCompletdGoals(uinf.getUsremail());
		
		uiModel.addAttribute("completedgoals", completedgoals);
		return "empl_mygoals";
	}
	
	
	@RequestMapping(value = "/employee/goalstat", method = RequestMethod.GET)
	public String displayGoalsStat(HttpServletRequest req, Model uiModel) {
		UsrInfo uinf = (UsrInfo)req.getSession().getAttribute("uinfo");
		
		List<EnrolledGoals> statgoals = emplGoalDao.getAllCompletdGoals(uinf.getUsremail());
		int totscore = 0;
		
		UsrInfo udata = new UsrInfo();
		udata.setUsremail(uinf.getUsremail());
		udata.setUsrnm(uinf.getUsrnm());
		udata.setGoals(statgoals);
		
		for(EnrolledGoals goal : statgoals) {
			totscore += goal.getMngr_score_awarded();
		}
		udata.setTotscore(totscore);
		
		uiModel.addAttribute("udata", udata);
		return "empl_goalstat";
	}	
}
