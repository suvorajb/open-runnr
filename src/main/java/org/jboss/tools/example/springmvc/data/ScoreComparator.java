package org.jboss.tools.example.springmvc.data;

import java.util.Comparator;

import org.jboss.tools.example.springmvc.model.UsrInfo;

public class ScoreComparator implements Comparator<UsrInfo>{

	@Override
	public int compare(UsrInfo o1, UsrInfo o2) {
		if(o1.getTotscore()>o2.getTotscore()) {
			return 1;
		} else if(o1.getTotscore()==o2.getTotscore()) {
			return 0;
		}
		
		return -1;
	}
	
}
