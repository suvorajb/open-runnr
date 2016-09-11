package org.jboss.tools.example.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.tools.example.springmvc.form.LoginForm;
import org.jboss.tools.example.springmvc.model.UsrInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {
	
/*	@Autowired
	private EmplGoalsDao emplGoalDao;
	
	@Autowired
	private MngrGoalsDao mngrGoalsDao;*/
	
	@RequestMapping(value = "/")
	public String redirLgnPg(HttpServletRequest req, Model uiModel) {
		return "redirect:/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String displayLoginPage(HttpServletRequest req, Model uiModel) {
		LoginForm loginform = new LoginForm();
		uiModel.addAttribute("loginform", loginform);

		return "login";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutUsrs(HttpServletRequest req, Model uiModel) {
		HttpSession httpsessn = req.getSession();
		UsrInfo uinfo = null;
		httpsessn.setAttribute("uinfo", uinfo);

		return "redirect:/login";

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String validateLogin(@ModelAttribute LoginForm loginform, BindingResult result, Model uiModel,
			HttpServletRequest req) {
		HttpSession httpsessn = req.getSession();
		UsrInfo uinfo = new UsrInfo();
		boolean isMngr = false;
		
		//int totGoals = 0;
		//int myGoals = 0;
		
		if ("john@acme.com".equalsIgnoreCase(loginform.getEmailid())
				&& "1234".equalsIgnoreCase(loginform.getPassword())) {
			uinfo.setUsremail("john@acme.com");
			uinfo.setUsrnm("John Mayer");
			uinfo.setLoginstatus(true);
			uinfo.setMngrflag(true);
			isMngr = true;
		}

		if ("sofia@acme.com".equalsIgnoreCase(loginform.getEmailid())
				&& "6789".equalsIgnoreCase(loginform.getPassword())) {
			uinfo.setUsremail("sofia@acme.com");
			uinfo.setUsrnm("Sofia Swift");
			uinfo.setUsrimg("/resources/gfx/sofia_swift.jpg");
			uinfo.setLoginstatus(true);
			uinfo.setMngrflag(false);
		}

		if ("nicki@acme.com".equalsIgnoreCase(loginform.getEmailid())
				&& "6789".equalsIgnoreCase(loginform.getPassword())) {
			uinfo.setUsremail("nicki@acme.com");
			uinfo.setUsrnm("Nicki Taylor");
			uinfo.setUsrimg("/resources/gfx/nicki_taylor.jpg");
			uinfo.setLoginstatus(true);
			uinfo.setMngrflag(false);
		}

		if ("chris@acme.com".equalsIgnoreCase(loginform.getEmailid())
				&& "6789".equalsIgnoreCase(loginform.getPassword())) {
			uinfo.setUsremail("chris@acme.com");
			uinfo.setUsrnm("Chris Grocott");
			uinfo.setUsrimg("/resources/gfx/chris_grocott.jpg");
			uinfo.setLoginstatus(true);
			uinfo.setMngrflag(false);
		}
		if ("jack@acme.com".equalsIgnoreCase(loginform.getEmailid())
				&& "6789".equalsIgnoreCase(loginform.getPassword())) {
			uinfo.setUsremail("jack@acme.com");
			uinfo.setUsrnm("Jack Floyd");
			uinfo.setUsrimg("/resources/gfx/Jack_Floyd.jpg");
			uinfo.setLoginstatus(true);
			uinfo.setMngrflag(false);
		}
		if ("ricky@acme.com".equalsIgnoreCase(loginform.getEmailid())
				&& "6789".equalsIgnoreCase(loginform.getPassword())) {
			uinfo.setUsremail("ricky@acme.com");
			uinfo.setUsrnm("Ricky Lee");
			uinfo.setUsrimg("/resources/gfx/Ricky_Lee.jpg");
			uinfo.setLoginstatus(true);
			uinfo.setMngrflag(false);
		}

		httpsessn.setAttribute("uinfo", uinfo);

		if (isMngr) {
			return "dashboard-m";
		}
		
		return "dashboard-e";
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String displayDashboard(HttpServletRequest req, Model uiModel) {
		HttpSession httpsessn = req.getSession();
		UsrInfo uinfo = (UsrInfo) httpsessn.getAttribute("uinfo");

		if (uinfo == null) {
			return "login";
		} else if (uinfo.isMngrflag() == true) {
			return "dashboard-m";
		}

		return "dashboard-e";
	}
}
