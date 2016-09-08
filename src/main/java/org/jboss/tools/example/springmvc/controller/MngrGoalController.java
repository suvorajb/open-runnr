package org.jboss.tools.example.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.tools.example.springmvc.data.MngrGoalsDao;
import org.jboss.tools.example.springmvc.form.EnrolledGoalForm;
import org.jboss.tools.example.springmvc.form.GoalForm;
import org.jboss.tools.example.springmvc.model.EnrolledGoals;
import org.jboss.tools.example.springmvc.model.Goals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MngrGoalController {

	@Autowired
	private MngrGoalsDao goalsDao;

	@RequestMapping(value = "/manage/goals", method = RequestMethod.GET)
	public String displayManageGoalsPage(HttpServletRequest req, Model uiModel) {
		List<Goals> goals = goalsDao.getAllGoals();

		uiModel.addAttribute("goals", goals);
		return "manage_goals";
	}

	@RequestMapping(value = "/creategoal", method = RequestMethod.GET)
	public String displayCreateGoalPage(HttpServletRequest req, Model uiModel) {
		GoalForm goalform = new GoalForm();

		uiModel.addAttribute("goalform", goalform);
		return "creategoal";
	}

	@RequestMapping(value = "/creategoal", method = RequestMethod.POST)
	public String saveGoalByManager(@ModelAttribute GoalForm goalform, BindingResult result, Model uiModel,
			HttpServletRequest req) {

		Goals newgoal = new Goals();

		System.out.println(goalform);

		newgoal.setGoal_title(goalform.getGoal_title());
		newgoal.setGoal_desc(goalform.getGoal_desc());
		newgoal.setGoal_category(goalform.getGoal_category());

		newgoal.setChecklist1(goalform.getChecklist1());
		newgoal.setChecklist2(goalform.getChecklist2());
		newgoal.setChecklist3(goalform.getChecklist3());

		goalsDao.sveGoalByMngr(newgoal);

		return "redirect:/manage/goals";
	}

	@RequestMapping(value = "/review/goals", method = RequestMethod.GET)
	public String displayMngrReviewGoalsPage(HttpServletRequest req, Model uiModel) {

		List<EnrolledGoals> reviewgoals = goalsDao.getAllGoalsSubmittedForReview();

		uiModel.addAttribute("reviewgoals", reviewgoals);
		return "review_goals";
	}
	
	@RequestMapping(value = "/viewgoal", method = RequestMethod.GET)
	public String viewGoalPage(HttpServletRequest req, Model uiModel) {

		Long goalid = Long.valueOf(req.getParameter("goalid"));
		
		Goals goal = goalsDao.findByGoalId(goalid);
		
		uiModel.addAttribute("goal", goal);
		return "mngr_viewgoal";
	}
	
	@RequestMapping(value = "/updtgoal", method = RequestMethod.GET)
	public String viewSubmttdGoalAndPrvdFdbck(HttpServletRequest req, Model uiModel) {

		Long goalid = Long.valueOf(req.getParameter("goalid"));
		EnrolledGoals goal = goalsDao.getGoalSubmttdRvw(goalid);
		
		uiModel.addAttribute("goal", goal);
		return "review_goals";
	}
	
	
	@RequestMapping(value = "/review/submittdgoals", method = RequestMethod.GET)
	public String rvwSubmttdGoals(HttpServletRequest req, Model uiModel) {

		List<EnrolledGoals> submttdgoals = goalsDao.getAllGoalsSubmittedForReview();
		
		uiModel.addAttribute("submttdgoals", submttdgoals);
		return "review_submttd_goals";
	}	
	
	@RequestMapping(value = "/review/submittdgoal", method = RequestMethod.GET)
	public String reviewSubmittedGoalByEmpl(HttpServletRequest req, Model uiModel) {
		Long goalid = Long.valueOf(req.getParameter("goalid"));
		EnrolledGoals submttdgoal = goalsDao.getGoalSubmttdRvw(goalid);
		GoalForm goalform = new GoalForm();
		goalform.setGoalid(goalid);
		
		uiModel.addAttribute("submttdgoal", submttdgoal);
		uiModel.addAttribute("goalform", goalform);
		
		return "appraise_submttd_goal";
	}
	
	@RequestMapping(value = "/appraisegoal", method = RequestMethod.POST)
	public String submitEnrlldGoalForMngrRevw(@ModelAttribute GoalForm goalform, BindingResult result, Model uiModel,
			HttpServletRequest req) {

		Long goalid = goalform.getGoalid();

		System.out.println(goalid);
		
		// get the enrolled goal first
		EnrolledGoals appraisalgoal = goalsDao.getGoalSubmttdRvw(goalid);
		
		//update the fields updated by employee like checkpoint answers & comments
		appraisalgoal.setMngr_comments(goalform.getMngr_comments());
		appraisalgoal.setMngr_score_awarded(goalform.getMngr_score_awarded());
		appraisalgoal.setFlag_completed(1);
		
		goalsDao.submitAppraisal(appraisalgoal);
		
		return "redirect:/dashboard";
	}	
	

}
