package ru.sberbank.jobstock.admin.view;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.sberbank.cib.exchange.domain.Order;
import ru.sberbank.jobstock.admin.Constants;
import ru.sberbank.jobstock.admin.JobStockAdmin;

public class CreateOrderController {
	
	public Stage currentStage;
	
	@FXML
	TextField orderName;
	
	@FXML
	TextArea orderDescription;
	
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
        String url = Constants.REST_URI+"/addOrder?name="+orderName.getText()+"&description="+orderDescription.getText();
        WebResource webResource = client.resource(UriBuilder.fromUri(url).build());
        
        String id = webResource.accept("application/json").get(String.class);
                
        url = Constants.REST_URI+"/findOrder?id="+id;
        webResource = client.resource(UriBuilder.fromUri(url).build());
        
        Order created_order = webResource.accept("application/json").get(Order.class); 
        System.out.println(created_order);
        mainApp.getOrderData().add(created_order);
	}
	
	@FXML
	private void handleCancel() {
		currentStage.close();
	}
	
}
