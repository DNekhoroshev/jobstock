package ru.sberbank.jobstock;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.sberbank.jobstock.view.JobStockCustomerFaceMainView;


public class JobStockCustomerFace extends Application {
	private Stage primaryStage;
    private BorderPane rootLayout;	
    
    @Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
	        this.primaryStage.setTitle("JobStockCustomerFace");
	        initRootLayout();
	        showEmployeeOverview();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initRootLayout() {
		try {
            // Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(JobStockCustomerFace.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

         // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout,1024,768);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Биржа задач - АРМ исполнителя");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
     * Показывает в корневом макете сведения об адресатах.
     */
    public void showEmployeeOverview() {
        try {
            // Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(JobStockCustomerFace.class.getResource("view/mainview.fxml"));
            BorderPane areasOverview = (BorderPane) loader.load();

            // Помещаем сведения об адресатах в центр корневого макета.
            rootLayout.setCenter(areasOverview);
            
            JobStockCustomerFaceMainView controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
