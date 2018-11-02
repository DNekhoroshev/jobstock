package ru.sberbank.jobstock.admin;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.sberbank.cib.exchange.domain.Order;
import ru.sberbank.jobstock.admin.model.EmployeeWrapper;
import ru.sberbank.jobstock.admin.model.SkillWrapper;
import ru.sberbank.jobstock.admin.view.CreateEmployeeController;
import ru.sberbank.jobstock.admin.view.CreateOrderController;
import ru.sberbank.jobstock.admin.view.CreateSkillController;
import ru.sberbank.jobstock.admin.view.EmployeeSkillController;
import ru.sberbank.jobstock.admin.view.JobStockAdminViewController;
import ru.sberbank.jobstock.admin.view.OrderSkillController;


public class JobStockAdmin extends Application {
	private Stage primaryStage;
    private BorderPane rootLayout;
	
    private ObservableList<EmployeeWrapper> employeeData = FXCollections.observableArrayList();
    private ObservableList<SkillWrapper> skillData = FXCollections.observableArrayList();
    private ObservableList<Order> orderData = FXCollections.observableArrayList();
    
    @Override
   	public void start(Stage primaryStage) {
   		try {
   			this.primaryStage = primaryStage;
   	        this.primaryStage.setTitle("JobStockCustomerFace");
   	        initRootLayout();
   	        showAdminView();
   		} catch(Exception e) {
   			e.printStackTrace();
   		}
   	}
   	
   	private void initRootLayout() {
   		try {
               // Загружаем сведения об адресатах.
               FXMLLoader loader = new FXMLLoader();
               loader.setLocation(JobStockAdmin.class.getResource("view/RootLayout.fxml"));
               rootLayout = (BorderPane) loader.load();

            // Отображаем сцену, содержащую корневой макет.
               Scene scene = new Scene(rootLayout,1024,768);
               primaryStage.setScene(scene);
               primaryStage.show();
           } catch (IOException e) {
               e.printStackTrace();
           }
   	}
	
   	public void showAdminView() {
        try {
            // Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(JobStockAdmin.class.getResource("view/JobStockAdminView.fxml"));
            AnchorPane adminOverview = (AnchorPane) loader.load();

            // Помещаем сведения об адресатах в центр корневого макета.
            rootLayout.setCenter(adminOverview);
            
            JobStockAdminViewController controller = loader.getController();            
               
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   	
   	public void showCreateEmployeeView() {
        try {
            Stage createDialog = new Stage();          
        	
        	// Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(JobStockAdmin.class.getResource("view/CreateEmployeeView.fxml"));
            AnchorPane createOverview = (AnchorPane) loader.load();

            Scene createScene = new Scene(createOverview);
            createDialog.setScene(createScene);
            createDialog.show();
                        
            CreateEmployeeController controller = loader.getController();            
            
            controller.setMainApp(this);
            controller.currentStage = createDialog;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   	
   	public void showCreateSkillView() {
        try {
            Stage createDialog = new Stage();          
        	
        	// Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(JobStockAdmin.class.getResource("view/CreateSkillView.fxml"));
            AnchorPane createOverview = (AnchorPane) loader.load();

            Scene createScene = new Scene(createOverview);
            createDialog.setScene(createScene);
            createDialog.show();
                        
            CreateSkillController controller = loader.getController();            
            
            controller.setMainApp(this);
            controller.currentStage = createDialog;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   	
   	public void showCreateOrderView() {
        try {
            Stage createDialog = new Stage();          
        	
        	// Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(JobStockAdmin.class.getResource("view/CreateOrder.fxml"));
            AnchorPane createOverview = (AnchorPane) loader.load();

            Scene createScene = new Scene(createOverview);
            createDialog.setScene(createScene);
            createDialog.show();
                        
            CreateOrderController controller = loader.getController();            
            
            controller.setMainApp(this);
            controller.currentStage = createDialog;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   	
   	public void showEmployeeSkillAddView(EmployeeWrapper emp) {
        try {
            Stage createDialog = new Stage();          
        	
        	// Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(JobStockAdmin.class.getResource("view/EmployeeSkillAddView.fxml"));
            AnchorPane createOverview = (AnchorPane) loader.load();

            Scene createScene = new Scene(createOverview);
            createDialog.setScene(createScene);
            createDialog.show();
                        
            EmployeeSkillController controller = loader.getController();            
            controller.setEmp(emp);
            
            controller.setMainApp(this);
            controller.currentStage = createDialog;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   	
   	public void showOrderSkillAddView(Order ord) {
        try {            
        	Stage createDialog = new Stage();          
        	
        	// Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(JobStockAdmin.class.getResource("view/OrderSkillAddView.fxml"));
            AnchorPane createOverview = (AnchorPane) loader.load();

            Scene createScene = new Scene(createOverview);
            createDialog.setScene(createScene);
            createDialog.show();
                        
            OrderSkillController controller = loader.getController();            
            controller.setOrd(ord);
            
            controller.setMainApp(this);
            controller.currentStage = createDialog;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   	
	public static void main(String[] args) {
		launch(args);
	}

	public ObservableList<EmployeeWrapper> getEmployeeData() {
		return employeeData;
	}

	public ObservableList<SkillWrapper> getSkillData() {
		return skillData;
	}

	public ObservableList<Order> getOrderData() {
		return orderData;
	}

}
