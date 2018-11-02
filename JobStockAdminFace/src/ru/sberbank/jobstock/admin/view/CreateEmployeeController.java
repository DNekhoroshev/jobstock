package ru.sberbank.jobstock.admin.view;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.sberbank.cib.exchange.domain.Employee;
import ru.sberbank.jobstock.admin.JobStockAdmin;
import ru.sberbank.jobstock.admin.model.EmployeeWrapper;
import ru.sberbank.jobstock.admin.Constants;


public class CreateEmployeeController {
	
	public Stage currentStage;
	
	@FXML
	TextField empId;
	
	@FXML
	TextField empName;
	
	JobStockAdmin mainApp;

	public JobStockAdmin getMainApp() {
		return mainApp;
	}

	public void setMainApp(JobStockAdmin mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	private void handleCreate() {		
		ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        String url = Constants.REST_URI+"/addEmployee?name="+empName.getText();
        WebResource webResource = client.resource(UriBuilder.fromUri(url).build());
        
        String id = webResource.accept("application/json").get(String.class);
                
        url = Constants.REST_URI+"/findEmployee?id="+id;
        webResource = client.resource(UriBuilder.fromUri(url).build());
        
        Employee created_emp = webResource.accept("application/json").get(Employee.class); 
        System.out.println(created_emp);
        mainApp.getEmployeeData().add(new EmployeeWrapper(created_emp));
	}
	
	@FXML
	private void handleCancel() {
		currentStage.close();
	}
	
}
