/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc190_as5;

/**
 *
 * @author JorgeContreras
 */
public class DishInformation {
    String name,description,image;
    Double price;
    
     public DishInformation(String a ,String b ,String c, Double d){
        name =a;
        description =b;
        image =c;
        price =d;
     }//end cunstructor DishInformation
     public DishInformation(){
        name="";
        description="";
        image="";
        price=0.0;
    }
}//end DishInformation
