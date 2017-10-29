/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortinganimations;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

/**
 *
 * @author Michal Biernat
 */
public class SortingAnimations extends Application {
    
    Timer timer;
    int rect1Position=1;
    int rect2Position=0;
    int countIteration=1;
    boolean actionInProgress=false;

    public void changePosition(MyRectangle[] myRects,int positionRect1,int positionRect2){
      MyRectangle myRect1Temp=myRects[positionRect1];
      MyRectangle myRect2Temp=myRects[positionRect2];
      myRects[positionRect1]=myRect2Temp;
      myRects[positionRect2]=myRect1Temp;     
    }
    
    @Override
    public void start(Stage primaryStage) {
    
        MyRectangle[] listRect=new MyRectangle[8];
        RectanglesConstruction(listRect);
        Button btn = new Button();
        btn.setText("Bubble Sorting");
        btn.setOnAction(new EventHandler<ActionEvent>() {
          
            @Override
            public void handle(ActionEvent event) {
                if(actionInProgress==true)return;
                actionInProgress=true;
                MyRectangle rect1=listRect[1];
                MyRectangle rect2=listRect[0];
                rect1.flash(rect2);
                timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        if(rect1Position==listRect.length){
                        listRect[rect2Position-1].blackStroke(listRect[rect1Position-1]);
                        rect1Position=1;
                        rect2Position=0;
                        countIteration++;
                             if(countIteration==listRect.length){
                                 listRect[rect2Position].blackStroke(listRect[rect1Position]);
                                 timer.cancel();
                                 actionInProgress=false;
                                 return;    
                             }
                        }
                        if(rect2Position>0){    
                            listRect[rect2Position-1].blackStroke(rect2);   
                        }
                        listRect[rect2Position].flash(listRect[rect1Position]);
                        try {
                            Thread.sleep(100);
                        }
                        catch (InterruptedException ex) {
                        }
                        if(listRect[rect2Position].getValue()>listRect[rect1Position].getValue()){
                            try {
                                Thread.sleep(700);
                            } 
                            catch (InterruptedException ex) {
                            }
                            listRect[rect1Position].move2(listRect[rect2Position]);
                            changePosition(listRect,rect2Position,rect1Position);
                        }
                        rect1Position++;
                        rect2Position++;     
                    }
                }, 100, 2500);
            }
        });
        Button btn2 = new Button();
        btn2.setText("Selection Sorting");
        btn2.setOnAction(new EventHandler<ActionEvent>() {   
            @Override
            public void handle(ActionEvent event) {  
                    if(actionInProgress==true)return;
                    actionInProgress=true;
                    rect2Position=0;
                    rect1Position=MyRectangle.findTheSmallest(listRect, 1);
                    timer = new Timer();
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            listRect[rect2Position].flash(listRect[rect1Position]);
                            try {
                                Thread.sleep(100);
                            }
                            catch (InterruptedException ex) {
                            }
                            if(listRect[rect2Position].getValue()>listRect[rect1Position].getValue()){
                                try {
                                    Thread.sleep(700);
                                } 
                                catch (InterruptedException ex) {                                  
                                }
                                listRect[rect1Position].move2(listRect[rect2Position]);
                                changePosition(listRect,rect2Position,rect1Position);
                            }                        
                            try {
                                Thread.sleep(1000);
                            } 
                            catch (InterruptedException ex) {
                            }
                            listRect[rect2Position].blackStroke(listRect[rect1Position]);
                            rect2Position++;
                            if(rect2Position==listRect.length-1){           
                                timer.cancel();
                                actionInProgress=false;
                                return;                                   
                            }
                            rect1Position=MyRectangle.findTheSmallest(listRect, rect2Position+1); 
                        }
                    }, 100, 3000);     
            }
        });
       
        VBox vbox=new VBox();    
        VBox hbox = new VBox();
        hbox.setPrefHeight(300);
        hbox.setPadding(new Insets(80, 80, 80, 40));
        hbox.setSpacing(40);
        Pane pane=new Pane();
        pane.setPrefSize(850, 500);
        rectanglesInPane(listRect, pane);
         
        Button btn3 = new Button();
        btn3.setText("Shuffle");
        btn3.setOnAction(new EventHandler<ActionEvent>() {          
            @Override
            public void handle(ActionEvent event) {
                if(actionInProgress==true)return;
                for (MyRectangle myRectangle : listRect) {
                    pane.getChildren().remove(myRectangle);
                }
                MyRectangle.shuffle(listRect);
                rectanglesInPane(listRect, pane);
                
            }
        });
        hbox.getChildren().add(pane);
        HBox hboxForButtons=new HBox();
        hboxForButtons.setSpacing(15);
        
        vbox.getChildren().add(hbox);
        vbox.getChildren().add(hboxForButtons);
        hboxForButtons.setAlignment(Pos.BASELINE_CENTER);
        hboxForButtons.getChildren().add(btn);
        hboxForButtons.getChildren().add(btn2);
        hboxForButtons.getChildren().add(btn3);
        
        Scene scene = new Scene(vbox, 900, 550);
        
        primaryStage.setTitle("Sorting Animations");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void rectanglesInPane(MyRectangle[] listRect, Pane pane) {
        int x=20;
        int y=100;
        for (MyRectangle myRectangle : listRect) {
            myRectangle.setWidth(60);
            myRectangle.setHeight(60);
            myRectangle.setX(x);
            myRectangle.setY(y);
            x=x+100;
            pane.setPrefWidth(60);
            pane.getChildren().add(myRectangle);
            
        }
    }

    private void RectanglesConstruction(MyRectangle[] listRect) {
        for(int i=0;i<listRect.length;i++){
             MyRectangle myrect1=new MyRectangle();
             myrect1.setValue((int)(1+Math.random()*9));
             final Image image1 = new Image(getClass().getResourceAsStream("images/"+myrect1.getValue()+".png"));
             myrect1.setStrokeWidth(5);
             myrect1.setStroke(Color.BLACK);
             myrect1.setFill(new ImagePattern(image1));
             listRect[i]=myrect1;
        }
 
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
