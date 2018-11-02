package ru.sberbank.jobstock.admin.view;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.sberbank.cib.exchange.domain.SkillName;
import ru.sberbank.jobstock.admin.Constants;
import ru.sberbank.jobstock.admin.JobStockAdmin;
import ru.sberbank.jobstock.admin.model.SkillWrapper;


public class CreateSkillController {
	
	public Stage currentStage;
	
	@FXML
	private TextField skillName;
	
	JobStockAdmin mainApp;

	public JobStockAdmin getMainApp() {
		return mainApp;
	}

	public void setMainApp(JobStockAdmin mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	private void handleCreate() {		
		System.out.println("AAA");
		ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        String url = Constants.REST_URI+"/addSkillName?name="+skillName.getText();
        WebResource webResource = client.resource(UriBuilder.fromUri(url).build());
        System.out.println(url);
        String id = webResource.accept("application/json").get(String.class);                
        
        SkillName sn = new SkillName();
        sn.setId(Integer.parseInt(id));
        sn.setName(skillName.getText());
        mainApp.getSkillData().add(new SkillWrapper(sn));
	}
	
	@FXML
	private void handleCancel() {
		currentStage.close();
	}
	
}
