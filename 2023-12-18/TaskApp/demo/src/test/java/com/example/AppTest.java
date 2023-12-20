package com.example;

import org.junit.Test;


import static org.junit.Assert.*; 
import org.junit.Before; 
import org.junit.Test; 
import java.awt.event.*; 


/**
 * Unit test for simple App.
 */
public class AppTest {

    TaskListApp newApp; 

    @Before 
    public void setUp() {
        newApp = new TaskListApp(); 
    }


    @Test
    public void testAddNotes() {
        newApp.newTaskField.setText("oi oi oi"); 
        newApp.addTask();
        
        newApp.newTaskField.setText("oi oi oi 2 "); 
        newApp.addTask();


        assertEquals(2, newApp.taskList.getModel().getSize()); 
        
    }

    @Test
    public void testRemoveNotes() {
        
        newApp.newTaskField.setText("oi oi oi"); 
        newApp.addTask();
        
        newApp.taskList.setSelectedIndex(0); // Element to be removed 
        
        newApp.removeTask();
        
        assertEquals(0, newApp.taskList.getModel().getSize()); 
    }

    @Test 
    public void testCheck() {
        newApp.newTaskField.setText("oi oi oi"); 
        newApp.addTask();

        Task selectedTask = newApp.taskListModel.getElementAt(0);
        selectedTask.setCompleted(!selectedTask.isCompleted());

        assertTrue(selectedTask.isCompleted());


    }   


}
