package ru.sberbank.jobstock.model;

import java.util.List;
import java.util.Set;

public class JobArea {
	private String areaName;
	private JobArea parent;
	private List<JobArea> childs;
	private Set<Skill> skills;
	
	public JobArea(String areaName, Set<Skill> skills) {
		super();
		this.areaName = areaName;
		this.skills = skills;
	}

	public JobArea(String areaName) {
		super();
		this.areaName = areaName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	public JobArea getParent() {
		return parent;
	}

	public void setParent(JobArea parent) {
		this.parent = parent;
	}

	public List<JobArea> getChilds() {
		return childs;
	}

	public void setChilds(List<JobArea> childs) {
		this.childs = childs;
		for(JobArea child: childs)
			child.setParent(this);
	}

	@Override
	public String toString() {
		return "JobArea [areaName=" + areaName + "]";
	}
	
	
}
