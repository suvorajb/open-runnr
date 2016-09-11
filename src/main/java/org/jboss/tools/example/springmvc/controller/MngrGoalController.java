package org.jboss.tools.example.springmvc.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.tools.example.springmvc.data.MngrGoalsDao;
import org.jboss.tools.example.springmvc.data.ScoreComparator;
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
	
	@RequestMapping(value = "/leaderboards", method = RequestMethod.GET)
	public String displayLeaderboards(HttpServletRequest req, Model uiModel) {

		List<UsrInfo> teammembers = new ArrayList<UsrInfo>();
		List<EnrolledGoals> statgoals = null;
		
		UsrInfo u1 = new UsrInfo();
		u1.setUsremail("sofia@acme.com");
		u1.setUsrnm("Sofia Swift");
		u1.setUsrimg("/resources/gfx/sofia_swift.jpg");
		statgoals = goalsDao.getAllCompletdGoals(u1.getUsremail());
		int totscore1 = 0;
		for(EnrolledGoals goal : statgoals) {
			totscore1 += goal.getMngr_score_awarded();
		}
		u1.setTotscore(totscore1);
		
		UsrInfo u2 = new UsrInfo();
		u2.setUsremail("nicki@acme.com");
		u2.setUsrnm("Nicki Taylor");
		u2.setUsrimg("/resources/gfx/nicki_taylor.jpg");
		statgoals = null;
		statgoals = goalsDao.getAllCompletdGoals(u2.getUsremail());
		int totscore2 = 0;
		if(statgoals!=null) {
			for(EnrolledGoals goal : statgoals) {
				totscore2 += goal.getMngr_score_awarded();
			}
		}
		u2.setTotscore(totscore2);
		
		UsrInfo u3 = new UsrInfo();
		u3.setUsremail("chris@acme.com");
		u3.setUsrnm("Chris Grocott");
		u3.setUsrimg("/resources/gfx/chris_grocott.jpg");
		statgoals = null;
		statgoals = goalsDao.getAllCompletdGoals(u3.getUsremail());
		int totscore3 = 0;
		if(statgoals!=null) {
			for(EnrolledGoals goal : statgoals) {
				totscore3 += goal.getMngr_score_awarded();
			}
		}
		u3.setTotscore(totscore3);
		
		UsrInfo u4 = new UsrInfo();
		u4.setUsremail("jack@acme.com");
		u4.setUsrnm("Jack Floyd");
		u4.setUsrimg("/resources/gfx/Jack_Floyd.jpg");
		statgoals = null;
		statgoals = goalsDao.getAllCompletdGoals(u4.getUsremail());
		int totscore4 = 0;
		if(statgoals!=null) {
			for(EnrolledGoals goal : statgoals) {
				totscore4 += goal.getMngr_score_awarded();
			}
		}
		u4.setTotscore(totscore4);
		
		UsrInfo u5 = new UsrInfo();
		u5.setUsremail("ricky@acme.com");
		u5.setUsrnm("Ricky Lee");
		u5.setUsrimg("/resources/gfx/Ricky_Lee.jpg");
		statgoals = null;
		statgoals = goalsDao.getAllCompletdGoals(u5.getUsremail());
		int totscore5 = 0;
		if(statgoals!=null) {
			for(EnrolledGoals goal : statgoals) {
				totscore5 += goal.getMngr_score_awarded();
			}
		}
		u5.setTotscore(totscore5);		
		
		teammembers.add(u1);
		teammembers.add(u2);
		teammembers.add(u3);
		teammembers.add(u4);
		teammembers.add(u5);
		System.out.println("teammembers: " + teammembers);
		
		Collections.sort(teammembers, new ScoreComparator());

		uiModel.addAttribute("teammembers", teammembers);
		return "leaderboards";
	}

}
