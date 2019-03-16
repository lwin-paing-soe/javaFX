/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.app.controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import student.app.dao.StudentDAO;
import student.app.model.Student;

/**
 * FXML Controller class
 *
 * @author user
 */
public class EditController implements Initializable {

    @FXML
    private ToggleGroup gender;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private RadioButton maleRadio;
    @FXML
    private RadioButton femaleRadio;
    @FXML
    private DatePicker dobPicker;
    @FXML
    private Button savebtn;
    
    private int studentID;
    
    private StudentDAO studentdao;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         maleRadio.setUserData("Male");
        femaleRadio.setUserData("Female");
        StudentDAO studentdao=new StudentDAO();
    }    

    @FXML
    private void updateStudent(ActionEvent event) {
        
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
        
        Student sd=new Student(studentID,name, email, userdata, sqlDob);
        try {
            studentdao.updateStudent(sd);
            Stage currentStage=(Stage)nameField.getScene().getWindow();
            currentStage.close();
        } catch (SQLException ex) {
            Logger.getLogger(EditController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void setStudentData(Student selectedStudent) {
       this.studentID=selectedStudent.getId();
        nameField.setText(selectedStudent.getName());
        emailField.setText(selectedStudent.getEmail());
        if(selectedStudent.getGender().equals("Male")){
            maleRadio.setSelected(true);
            
        }else{
            femaleRadio.setSelected(true);
        }
        dobPicker.setValue(selectedStudent.getDob().toLocalDate());
       }
    }
    

