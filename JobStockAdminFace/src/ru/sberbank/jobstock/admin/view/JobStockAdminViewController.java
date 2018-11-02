package ru.sberbank.jobstock.admin.view;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import ru.sberbank.cib.exchange.domain.Employee;
import ru.sberbank.cib.exchange.domain.Order;
import ru.sberbank.cib.exchange.domain.Skill;
import ru.sberbank.jobstock.admin.Constants;
import ru.sberbank.jobstock.admin.JobStockAdmin;
import ru.sberbank.jobstock.admin.model.EmployeeWrapper;
import ru.sberbank.jobstock.admin.model.NameValueEntity;
import ru.sberbank.jobstock.admin.model.SkillWrapper;

public class JobStockAdminViewController {
	
	JobStockAdmin mainApp;

	@FXML
	TabPane mtp;
	
	@FXML
	TableView<EmployeeWrapper> employeeTable;
	
	@FXML
    private TableColumn<EmployeeWrapper, String> empNameColumn;
	
	@FXML
	TableView<NameValueEntity> employeeProperties;
	
	@FXML
    private TableColumn<NameValueEntity, String> employeePropertyName;
	
	@FXML
    private TableColumn<NameValueEntity, String> employeePropertyValue;
	
	@FXML
	TableView<NameValueEntity> employeeSkills;
	
	@FXML
    private TableColumn<NameValueEntity, String> employeeSkillName;
	
	@FXML
    private TableColumn<NameValueEntity, String> employeeSkillLevel;
	
	@FXML
	TableView<SkillWrapper> skillTable;
	
	@FXML
    private TableColumn<SkillWrapper, String> skillName;
	
	@FXML
	private TextArea skillDescription;
	
	@FXML
	TableView<Order> ordersTable;
	
	@FXML
    private TableColumn<Order, String> orderNameColumn;
	
	@FXML
	private TextArea orderDescription;
	
	@FXML
	TableView<NameValueEntity> orderSkills;
	
	@FXML
    private TableColumn<NameValueEntity, String> orderSkillName;
	
	@FXML
    private TableColumn<NameValueEntity, String> orderSkillLevel;
	
	@FXML
    private void initialize() {
        empNameColumn.setCellValueFactory(cellData->cellData.getValue().getName());
        
        employeePropertyName.setCellValueFactory(cellData->cellData.getValue().getName());
        employeePropertyValue.setCellValueFactory(cellData->cellData.getValue().getValue());
        
        employeeSkillName.setCellValueFactory(cellData->cellData.getValue().getName());
        employeeSkillLevel.setCellValueFactory(cellData->cellData.getValue().getValue());
        orderSkillName.setCellValueFactory(cellData->cellData.getValue().getName());
        orderSkillLevel.setCellValueFactory(cellData->cellData.getValue().getValue());
        
        skillName.setCellValueFactory(cellData->cellData.getValue().getName());
        
        orderNameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getName()));
        
        employeeTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showEmployeeDetails(newValue));
        skillTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showSkillDetails(newValue));
        ordersTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showOrderDetails(newValue));
        
    }
	
	
	public JobStockAdmin getMainApp() {
		return mainApp;
	}

	public void setMainApp(JobStockAdmin mainApp) {
		this.mainApp = mainApp;
		employeeTable.setItems(mainApp.getEmployeeData());
		skillTable.setItems(mainApp.getSkillData());
		ordersTable.setItems(mainApp.getOrderData());
		
	}
	
	private void showEmployeeDetails(EmployeeWrapper emp) {
		ObservableList<NameValueEntity> props = FXCollections.observableArrayList();
		props.add(new NameValueEntity("Id", emp.getId().get()));
		props.add(new NameValueEntity("Name", emp.getName().get()));
		
		employeeProperties.setItems(props);
		showEmployeeSkills(emp);
		
	}
	
	private void showEmployeeSkills(EmployeeWrapper emp) {
		System.out.println("SKILLS:: "+emp.getSkills());
		ObservableList<NameValueEntity> skills = FXCollections.observableArrayList();
		if (emp.getSkills() != null) {
			for (Skill skill : emp.getSkills()) {
				skills.add(new NameValueEntity(skill.getSkillName().getName(), skill.getSkillLevel().toString()));
			}
		}
		employeeSkills.setItems(skills);
		
	}
	
	private void showSkillDetails(SkillWrapper skill) {
		skillDescription.setText("Simple descrition for id "+skill.getId().get());
	}
	
	private void showOrderDetails(Order order) {
		orderDescription.setText(order.getDescription());
		showOrderSkills(order);
	}
	
	private void showOrderSkills(Order ord) {
		System.out.println("ORDER SKILLS:: "+ord.getSkills());
		ObservableList<NameValueEntity> skills = FXCollections.observableArrayList();
		if (ord.getSkills() != null) {
			for (Skill skill : ord.getSkills()) {
				skills.add(new NameValueEntity(skill.getSkillName().getName(), skill.getSkillLevel().toString()));
			}
		}
		orderSkills.setItems(skills);
		
	}
	
	@FXML
	private void handleCreate() {
		if(mtp.getTabs().get(0).isSelected())
			mainApp.showCreateEmployeeView();
		if(mtp.getTabs().get(2).isSelected())
			mainApp.showCreateSkillView();
		if(mtp.getTabs().get(3).isSelected())
			mainApp.showCreateOrderView();
	}
	
	@FXML
	private void handleAddSkillToEmployee() {
		EmployeeWrapper emp = employeeTable.getSelectionModel().getSelectedItem();
		mainApp.showEmployeeSkillAddView(emp);
	}
	
	@FXML
	private void handleAddSkillToOrder() {
		Order ord = ordersTable.getSelectionModel().getSelectedItem();
		mainApp.showOrderSkillAddView(ord);
	}
	
	@FXML
	private void handleClose() {
		System.exit(0);
	}
	
	@FXML
	private void handleDelete() {
		
	}
	
	@FXML
	private void handleLoad() {
		Client client = ClientBuilder.newClient();
		
		WebTarget webTarget 
		  = client.target(Constants.REST_URI);
		
		WebTarget employeeWebTarget 
		  = webTarget.path("E01");
		
		Invocation.Builder invocationBuilder = employeeWebTarget.request(MediaType.APPLICATION_JSON_TYPE);
		
		Employee emp = invocationBuilder.get(Employee.class);
		
		
		
		System.out.println("Employee: "+ emp);
	}
	
}
