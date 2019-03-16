/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.app.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import student.app.dao.StudentDAO;
import student.app.model.Student;

/**
 *
 * @author user
 */
public class MainController implements Initializable {
    
   
   
    
   
    @FXML
    private MenuItem aboutItem;
    @FXML
    private MenuBar menuBar;
    @FXML
    private RadioButton maleRadio;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton femaleRadio;
    private DatePicker docPicker;
    @FXML
    private Button saveBtn;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    
    private StudentDAO studentDAO;
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student , Integer> idCol;
    @FXML
    private TableColumn<Student, String> nameCol;
    @FXML
    private TableColumn<Student, String> emailCol;
    @FXML
    private TableColumn<Student, String> genderCol;
    @FXML
    private TableColumn<Student, Date> dobCol;
    @FXML
    private DatePicker dobPicker;
    @FXML
    private MenuItem editItem;
    @FXML
    private MenuItem deleteItem;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        maleRadio.setUserData("Male");
        femaleRadio.setUserData("Female");
        studentDAO=new StudentDAO();
        initColumns();
        loadTableData();
    } 
    public void setTile(String txt){
      //  myLabel.setText(txt);
    }

    @FXML
    private void showAboutWindow(ActionEvent event) throws IOException {
     
        Parent layout=FXMLLoader.load(getClass().getResource("/student/app/views/about.fxml"));
        Scene scene=new Scene(layout);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(menuBar.getScene().getWindow());
        stage.show();
        
        
    }

    @FXML
    private void saveStudents(ActionEvent event) {
        String name=nameField.getText();
        String email=emailField.getText();
        String userdata=(String)gender.getSelectedToggle().getUserData();
        LocalDate dob=dobPicker.getValue();
        
        if(name.isEmpty() || email.isEmpty() || dob==null){
             Alert alert=new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText(null);
            alert.setContentText("Please Fill the requie fields.");
            alert.show();
            return;
        }
        
        Date sqlDob=Date.valueOf(dob);
        
        Student sd=new Student(name, email, userdata, sqlDob);
        
        try {
            studentDAO.saveStudent(sd);
            loadTableData();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Student successfully added.");
            alert.show();
            clearForm();
           
                   
        } catch (SQLException ex) {
            System.out.println("Error");
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
			
        
    }

    private void initColumns() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
         nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
          emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
           genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
            dobCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
    }

    private void loadTableData() {
       List<Student> students;
        try {
            students = studentDAO.getStudent();
             studentTable.getItems().setAll(students);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    private void clearForm() {
       nameField.clear();
       emailField.clear();
       maleRadio.setSelected(true);
       dobPicker.setValue(null);
       
    }

    @FXML
    private void showEditWindow(ActionEvent event) throws IOException {
        Student selectedStudent= studentTable.getSelectionModel().getSelectedItem();
          
             //delete student
        if(selectedStudent==null){
            Alert alert=new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText(null);
            alert.setContentText("Please selete the student you want to delete.");
            alert.show();
            
            return;
       }
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/student/app/views/edit.fxml"));
        Parent root=loader.load();
        EditController con =loader.getController();
        con.setStudentData(selectedStudent);
        Scene scene=new Scene(root);
        Stage editStage=new Stage();
        editStage.setScene(scene);
        editStage.initModality(Modality.WINDOW_MODAL);
        editStage.initOwner(studentTable.getScene().getWindow());
      
        editStage.showAndWait();
        loadTableData();//refresh the table after editing the student
    }

    @FXML
    private void deleteStudent(ActionEvent event) {
        
        //get seleted student
        Student selectedstudent=studentTable.getSelectionModel().getSelectedItem();
        
        //delete student
       if(selectedstudent==null){
            Alert alert=new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText(null);
            alert.setContentText("Please selete the student you want to delete.");
            alert.show();
            return;
       }
        try {
            studentDAO.deleteStudent(selectedstudent.getId());
            studentTable.getItems().remove(selectedstudent);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
