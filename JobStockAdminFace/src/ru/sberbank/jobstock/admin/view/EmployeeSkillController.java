package ru.sberbank.jobstock.admin.view;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import ru.sberbank.cib.exchange.domain.Skill;
import ru.sberbank.cib.exchange.domain.SkillLevel;
import ru.sberbank.jobstock.admin.Constants;
import ru.sberbank.jobstock.admin.JobStockAdmin;
import ru.sberbank.jobstock.admin.model.EmployeeWrapper;
import ru.sberbank.jobstock.admin.model.SkillWrapper;

public class EmployeeSkillController {
	
	class SkillConverter extends StringConverter<SkillWrapper> {

		@Override
		public String toString(SkillWrapper object) {
			
			return object.getSkillName().getName();
		}

		@Override
		public SkillWrapper fromString(String string) {
			
			return null;
		}
	}
	
	@FXML
	ChoiceBox<SkillWrapper> skill;
	
	@FXML
	ChoiceBox<SkillLevel> level;	
	
	public Stage currentStage;
	
	JobStockAdmin mainApp;
	
	EmployeeWrapper emp;	

	public EmployeeWrapper getEmp() {
		return emp;
	}

	public void setEmp(EmployeeWrapper emp) {
		this.emp = emp;
	}

	@FXML
    private void initialize() {
		ObservableList<SkillLevel> skillLevels = FXCollections.observableArrayList();
		skillLevels.add(SkillLevel.JUNIOR);
		skillLevels.add(SkillLevel.MIDDLE);
		skillLevels.add(SkillLevel.SENIOR);	
		
		level.setItems(skillLevels);
		
		skill.setConverter(new EmployeeSkillController.SkillConverter());
	}
	
	public JobStockAdmin getMainApp() {
		return mainApp;
	}

	public void setMainApp(JobStockAdmin mainApp) {
		this.mainApp = mainApp;
		skill.setItems(mainApp.getSkillData());
	}
	
	@FXML
	private void handleAdd() {
		Skill currSkill = new Skill();
		currSkill.setSkillName(skill.getSelectionModel().getSelectedItem().getSkillName());
		currSkill.setSkillLevel(level.getSelectionModel().getSelectedItem());
		emp.getSkills().add(currSkill);
		
		ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        String url = Constants.REST_URI+"/addSkillToEmployee?empId="+emp.getId().get()+"&skillNameId="+currSkill.getSkillName().getId()+"&level="+currSkill.getSkillLevel();
        WebResource webResource = client.resource(UriBuilder.fromUri(url).build());
        System.out.println("ADDING URL: "+url);
        String id = webResource.accept("application/json").get(String.class);
	}
}
