/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortinganimations;

import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.StrokeTransition;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author Michał Biernat
 */
public class MyRectangle extends Rectangle{
  
    public static int findTheSmallest(MyRectangle[] myrects,int index){
        int index2=index;
        int value=myrects[index].getValue();
        for(int i=index;i<myrects.length;i++){
            int value2=myrects[i].getValue();
            if(value2<value){
                value=value2;
                index2=i;
            }
            
        }
        return index2;
        
    }
    public static void shuffle(MyRectangle[] myrects){
        for(int i=0;i<myrects.length;i++){
            MyRectangle rect2=myrects[i];
            int index2=(int)(Math.random()*myrects.length);
            MyRectangle rect3=myrects[index2];
            myrects[i]=rect3;
            myrects[index2]=rect2;
        }
    }

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public  void move2(Rectangle rect2){
   
                final Timeline timeline = new Timeline();
                final KeyValue kv = new KeyValue(this.yProperty(), -60);
                final KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
                timeline.getKeyFrames().add(kf);

                PauseTransition pauseTransition = new PauseTransition(Duration.millis(600));
                pauseTransition.setOnFinished(e -> System.out.println("Ready to circle..."));
                
                final Timeline timeline2 = new Timeline();
                final KeyValue kv2 = new KeyValue(this.xProperty(), rect2.getX());
                final KeyFrame kf2 = new KeyFrame(Duration.millis(200), kv2);
                timeline2.getKeyFrames().add(kf2);
                
                final Timeline timeline3 = new Timeline();
                final KeyValue kv3 = new KeyValue(this.yProperty(), 100);
                final KeyFrame kf3 = new KeyFrame(Duration.millis(200), kv3);
                timeline3.getKeyFrames().add(kf3);
                             
                final Timeline timelineRect1 = new Timeline();
                final KeyValue kvRect1 = new KeyValue(rect2.yProperty(), -60);
                final KeyFrame kfRect1 = new KeyFrame(Duration.millis(200), kvRect1);
                timelineRect1.getKeyFrames().add(kfRect1);
              
                final Timeline timelineRect2 = new Timeline();
                final KeyValue kvRect2 = new KeyValue(rect2.xProperty(), this.getX()+0);
                final KeyFrame kfRect2 = new KeyFrame(Duration.millis(200), kvRect2);
                timelineRect2.getKeyFrames().add(kfRect2);
                
                final Timeline timelineRect3 = new Timeline();
                final KeyValue kvRect3 = new KeyValue(rect2.yProperty(), 100);
                final KeyFrame kfRect3 = new KeyFrame(Duration.millis(200), kvRect3);
                timelineRect3.getKeyFrames().add(kfRect3);
              
                SequentialTransition st = new SequentialTransition();
                st.getChildren().addAll(timeline,timeline2,timeline3,timelineRect1,timelineRect2,timelineRect3);
                st.setCycleCount(1);
                st.play();
                             
    }
    public  void flash(MyRectangle rect2){
            
            
            this.setStrokeWidth(5);
            StrokeTransition strokeTransition1 = new StrokeTransition(Duration.millis(400),this);
            strokeTransition1.setFromValue(Color.BLACK);
            strokeTransition1.setToValue(Color.RED);
            strokeTransition1.setCycleCount(1);
            
            rect2.setStrokeWidth(5);
            StrokeTransition strokeTransition2 = new StrokeTransition(Duration.millis(400), rect2);
            strokeTransition2.setFromValue(Color.BLACK);
            strokeTransition2.setToValue(Color.RED);
            strokeTransition2.setCycleCount(1);      
           
            strokeTransition1.play();
            strokeTransition2.play();
    
                
    }
        public  void enableStroke(MyRectangle rect2){
            this.setStrokeWidth(10);
            this.setStroke(Color.RED);
           rect2.setStrokeWidth(10);
           rect2.setStroke(Color.RED);
          
    }
           public  void disableStroke(MyRectangle rect2){
            this.setStrokeWidth(0);
            rect2.setStrokeWidth(0);
    }
         public  void blackStroke(MyRectangle rect2){
            this.setStroke(Color.BLACK);
            rect2.setStroke(Color.BLACK);
    }  
    

    @Override
    public String toString() {
        return "MyRectangle{" + "value=" + value + "x="+this.getX()+" y="+this.getY()+'}';
    }
    
    
}
