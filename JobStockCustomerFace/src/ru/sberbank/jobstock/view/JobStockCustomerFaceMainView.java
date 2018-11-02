package ru.sberbank.jobstock.view;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import ru.sberbank.cib.exchange.domain.Employee;
import ru.sberbank.cib.exchange.domain.Order;
import ru.sberbank.cib.exchange.domain.Skill;
import ru.sberbank.cib.exchange.domain.SkillName;
import ru.sberbank.jobstock.Constants;
import ru.sberbank.jobstock.JobStockCustomerFace;

public class JobStockCustomerFaceMainView {
	
	JobStockCustomerFace mainApp;	

	@FXML
	Label empName;
	
	@FXML 
	ListView<Skill> skills;
	
	@FXML
	ListView<Order> orders;
	
	Employee empl;
	
	@FXML
    private void initialize() {
		TextInputDialog dialog = new TextInputDialog("Input your ID");
		 
		dialog.setTitle("Input your ID");
		dialog.setHeaderText("Input your ID:");
		dialog.setContentText("ID:");
		 
		Optional<String> result = dialog.showAndWait();
		 
		result.ifPresent(id -> {
		    empl = getEmployeeById(id);
			if(empl!=null) {
				this.empName.setText(empl.getName());							
			}else
				this.empName.setText("Not found");
		});
		
	
    }
	
	private Employee getEmployeeById(String id) {
		ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        String url = Constants.REST_URI+"/findEmployee?id="+id;
        System.out.println(url);
        WebResource webResource = client.resource(UriBuilder.fromUri(url).build());
        
        Employee emp = null;
        try {
        	emp = webResource.accept("application/json").get(Employee.class);
        	System.out.println(emp);
        }catch(Exception e) {
        	System.out.println("Not found");
        }
        
        return emp;
	}
	
	public JobStockCustomerFace getMainApp() {
		return mainApp;
	}

	public void setMainApp(JobStockCustomerFace mainApp) {
		this.mainApp = mainApp;
		ObservableList<Skill> skillData = FXCollections.observableArrayList();		
		if((empl!=null)&&(empl.getSkills()!=null))
			skillData.addAll(empl.getSkills());		
		skills.setItems(skillData);	
	}
	
	@FXML
	private void handleGetOrdersByEmployee() {
		ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        String url = Constants.REST_URI+"/findMatchingOrdersForEmployee?empId="+empl.getId();
        System.out.println(url);
        WebResource webResource = client.resource(UriBuilder.fromUri(url).build());
        //String gotOrders = webResource.accept("application/json").get(String.class);
        //System.out.println("ORDERS:"+gotOrders);
        Order[] gotOrders = webResource.accept("application/json").get(Order[].class);
        System.out.println("Got: "+gotOrders);
        ObservableList<Order> orderData = FXCollections.observableArrayList();
        if(gotOrders!=null) {        	
        	orderData.addAll(gotOrders);
        	orders.setItems(orderData);
        }
	}
}
