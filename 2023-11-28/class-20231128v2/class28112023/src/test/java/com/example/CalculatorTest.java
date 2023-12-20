// This is an example of a test class (JUnit) for the class Calculator 
package com.example;

// JUnit importations  
import static org.junit.Assert.*;

import org.junit.Test; 


// Definition of the test class CalculatorTest
public class CalculatorTest {

    
    // Test for the sum method of the class Calculator
    @Test
    public void testSum() {
        
        
        // Create an instance of the class Calculator
        Calculator calculadora = new Calculator();

         
        // Call the sum method with the arguments 3 and 7
        int result = calculadora.sum(3, 7); 
        
        
        // Check if the sum result is equals to 10
        assertEquals(10, result);

    } 

    
    // Test for the subtraction method of the class Calculator
    
    @Test
    public void testSubtraction (){
        
        
        // Create an instance of the class Calculator
        Calculator calculadora = new Calculator(); 

        
        // Call the subtraction method with the arguments 10 and 4
        int result = calculadora.subtract(10, 4);
        
         
        // Check if the subtraction result is equal to 6
        assertEquals(6, result);
    }

    @Test

    public void testDivision () {
        Calculator calculator = new Calculator(); 

        int result = calculator.divide(4, 2);
        
        assertEquals(2, result);
    }
    
}
