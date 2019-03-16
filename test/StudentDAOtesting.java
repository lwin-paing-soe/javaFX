/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Date;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import student.app.dao.StudentDAO;
import student.app.model.Student;

/**
 *
 * @author user
 */
public class StudentDAOtesting {
    
    public StudentDAOtesting() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
   
     StudentDAO dao=new StudentDAO();
//      @Test
//    public void testsaveStudent() throws SQLException{
//       
//        Date dob=new Date(System.currentTimeMillis());
//        Student sd=new Student("Aye Aye", "ayeaye@gmail.com", "Female", dob);
//        assertEquals(1, dao.saveStudent(sd));
//    }

//   @Test
//   public void testgetStudent() throws SQLException{
//       assertEquals(2, dao.getStudent().size());
//      
//   }
//public void testDeleteStuent(){
//    StudentDAO dao=new StudentDAO();
//        assertEquals(1, dao.deleteStudent());
//}
}
