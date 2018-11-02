package ru.sberbank.jobstock.help;

import java.util.ArrayList;
import java.util.List;

import ru.sberbank.jobstock.model.JobArea;

public class JobStockHelper {
	public static List<JobArea> getAreas(){
		JobArea IT = new JobArea("IT");
		JobArea finance = new JobArea("Finance");
		
		IT.setChilds(new ArrayList<JobArea>(){{
			add(new JobArea("Networks"));
			add(new JobArea("Java"));
			add(new JobArea("C#"));
			add(new JobArea("SQL"));
		}});
		
		finance.setChilds(new ArrayList<JobArea>(){{
			add(new JobArea("Tech analysis"));
			add(new JobArea("Trade"));			
		}});
		
		return new ArrayList<JobArea>() {{
			add(IT);
			add(finance);			
		}};
	}
}
