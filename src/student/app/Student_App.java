/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.app;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import student.app.dao.StudentDAO;
import student.app.model.Student;
/**
 *
 * @author user
 */
public class Student_App extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        String title="JavaFX Application.";
        System.out.println("Before fxml Loading..");
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/student/app/views/main.fxml"));
        Parent root =loader.load();
        System.out.println("After fxml loading...");
        
        Scene scene = new Scene(root);
      
        stage.setScene(scene);
        stage.setTitle("Student Application");
        stage.show();
    }

    /**
//     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        launch(args);
       
    }
        
    

    
}
