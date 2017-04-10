/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc190_as5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
/**
 *
 * @author Jorge Contreras
 */
public class CSC190_As5 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        /*we will first start by scanning the config.txt file */
        try{
             ArrayList<GridPane> g = new ArrayList<>();//arraylist for the gridpane fork display
            
            
            
            ArrayList<Scene> view = new ArrayList<>();
            
            
            
            ArrayList<Button> nextButtons = new ArrayList<>();
            
            
            
            
            ArrayList<Button> prevButtons = new ArrayList<>();
            
            String name = "";
                    
            String image = "";
            
            String description = "";
            
            double price = 0.0;
            
            ArrayList<DishInformation> dishes = new ArrayList<>();//arraylist of dishes with their information from the file
            
            
            
            
            /*from Fu's reference https://capturevision.wordpress.com/2008/06/28/how-to-embed-resource-files-using-netbeans/
            */
            Scanner sc = new Scanner(getClass().getResourceAsStream("config.txt"));//scans the config file         

            //This part is used to read the number of dishes in the config.txt file
            int NumOfDishes = sc.nextInt();    
            
            sc.nextLine();

            //this for loop will read dish data from file and store it     
            for (int i =0; i<NumOfDishes; i++) {
                
                
                
                sc.nextLine();
                
                /*Every 4 lines */
                for (int j = 0; j<4; j++){
                    String line = sc.nextLine();//string the line that was scanned 
                    
                    
                    
                    
                    /*from the line take the string part we want
                    in order to get the name, description,price,and image*/
                    if(line.contains("Name: ")){
                        name = line.substring(6); //System.out.println(name);
                    }//end else if
                    else if(line.contains("Description: ")){
                        description = line.substring(13);
                    }//end else if
                    else if(line.contains("Price: ")){
                        price = Double.parseDouble(line.substring(8));
                    }//end else if
                    else if(line.contains("Image: ")){
                        image = "/images/" + line.substring(7);
                    }//end else if
                    
                }//end for
                //up till this point we have scanned each line in config and
                //seperated strings to organize info from the informaiton we want to display
                
                
                
                DishInformation j = new DishInformation(name, description, image, price);//form my class of dish infomration for each dish
                
                
                dishes.add(j);//add dish object to arraylist
            }//end of for loop
            
            
           
            
            //define screen here
            int counter = 0;
            
            
            //for dish of type Dishinformation in arraylist dishes
            for (DishInformation dish : dishes){
                
              //refernce https://docs.oracle.com/javase/8/javafx/api/javafx/scene/image/ImageView.html
               ImageView v = new ImageView();
               
               
               
               //set image dimenstion in window
               Image in = new Image(dish.image);
               
               v.setImage(in);
               
               v.setFitHeight(250);
               
               
               
               
               v.setFitWidth(250);
               
               Label TitleLabel = new Label(dish.name);
               
               
               
               TitleLabel.setId("Title");
               
               
               
               Label DescriptionLabel = new Label(dish.description);              
               DescriptionLabel.setWrapText(true);
               
               
               DescriptionLabel.setMaxWidth(250);
               
               
               
               
               
               Label PriceLabel = new Label("Price: $"+ dish.price );
               
               
               //button for next
               Button NextButton = new Button("Next >");
               nextButtons.add(NextButton);
               
               
               //button for previous
               Button PreviousButton = new Button("< Prev");
               prevButtons.add(PreviousButton);
               //when you click the button
               
               //from reference guide for buttons slide show
               nextButtons.get(counter).setOnAction((ActionEvent event) -> {
                   primaryStage.close();
                   int next = 0;
                   for (int i = 0; i < g.size(); i++) {
                       if(event.getSource().equals(nextButtons.get(i))){
                           next = i + 1;
                       }//end if
                   }//end for
                    if(event.getSource().equals(nextButtons.get(g.size()-1))){
                       next = 0;
                   }//end if
                   primaryStage.setScene(view.get(next));
                   primaryStage.show();
               });
                prevButtons.get(counter).setOnAction((ActionEvent event) -> {
                primaryStage.close();
                   int previous = 0;
                   for (int i = 0; i < g.size(); i++) {
                       if(event.getSource().equals(prevButtons.get(i))){
                           previous = i-1;
                       }//end if
                   }//end for
                   if(event.getSource().equals(prevButtons.get(0))){
                       previous = g.size()-1;
                   }//end if
                   primaryStage.setScene(view.get(previous));
                   primaryStage.show();
               });
                
                
                
                
                
                
                
                
                
                //here i will set sections in each part of the grid
                
               GridPane grid = new GridPane();
               
               
               grid.add(TitleLabel,1,1,1,1);//set title at top
               
               
               grid.add(DescriptionLabel, 2, 2);//place descirption label
               
               
               grid.add(PriceLabel,3,2);//place price label
               
               
               grid.add(v,1,2);//position for image
               
               
               grid.add(prevButtons.get(counter),1,3);//poisiton for previous button
               
               
               grid.add(nextButtons.get(counter),2,3);//position for new button 
               g.add(grid); //add componenets
               Scene window = new Scene(g.get(counter),1000,1000);//set my window size
               
               view.add(window);
               counter++;
            }//end 
  
            
            
            
            
            
            
            
            
            primaryStage.setTitle("SmartRestaurant Table 1");
            primaryStage.setScene(view.get(0));
            primaryStage.show();

        }//end try
        
        
        
        
        
        
        
        
        catch(NumberFormatException except){
            System.out.println("Exception " + except);
        }//end catch
    }//end start

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }//end main
    
}
