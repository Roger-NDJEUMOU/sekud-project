
package util;

import database.DatabaseConnectionManager;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import models.Course;
import models.FinalMark;
import models.Person;
import models.Semester;
import models.Student;

/**
 *
 * @author Pacha
 */
public class AcademicRecordContainer {
    private final BorderPane academicRecordContainer = new BorderPane();
    private  VBox graphsContainer = new VBox();
    private  ScrollPane academicRecordCenter = new ScrollPane(graphsContainer);
    private final Label name = new Label();
    private final StackPane academicRecordTop = new StackPane(name);
    private final Button viewButton = new Button("View My Transcript");
    private final StackPane academicRecordBottom = new StackPane(viewButton);
    ArrayList<FinalMark> list;
    ArrayList<Course> list1;
    private final GridPane transcript = new GridPane();
    private  ScrollPane transcriptContainer = new ScrollPane(transcript);
    
    
    public AcademicRecordContainer() {
    }

    public Button getViewButton() {
        return viewButton;
    }

    /*public Button getBackButton() {
        return backButton;
    }*/
    
    
    public BorderPane getCategoryContainer() {
        return academicRecordContainer;
    }

    public VBox getGraphsContainer() {
        return graphsContainer;
    }

    public ScrollPane getCategoryCenter() {
        return academicRecordCenter;
    }

    public Label getName() {
        return name;
    }

    public StackPane getCategoryTop() {
        return academicRecordTop;
    }

    public Button getDownloadButton() {
        return viewButton;
    }

    public StackPane getCategoryBottom() {
        return academicRecordBottom;
    }

    
    
    
    
    public void settingAcademicRecordBorderPane(Person personalInfo,Student studentInfo,DatabaseConnectionManager manager,BorderPane homeBorderPane) {
        viewButton.setVisible(true);
        academicRecordContainer.setVisible(true);
        academicRecordCenter.setVisible(true);
        academicRecordContainer.setTop(academicRecordTop);
        academicRecordContainer.setBottom(academicRecordBottom);
        academicRecordContainer.setCenter(academicRecordCenter);
        graphsContainer.setSpacing(4);
        transcript.setVgap(10.5);
        transcript.setHgap(20.5);
        transcript.setPadding(new Insets(10, 20, 10, 20));
        viewButton.setTooltip(new Tooltip("Click to view your transcript"));
        academicRecordContainer.getStylesheets().add("/css/myacademicrecord.css");
        viewButton.setPadding(new Insets(10,0,10,0));
        personalInfo = manager.getStudentNameFromFinalMarkTable(studentInfo.getId());
        academicRecordBottom.setPadding(new Insets(10, 0, 10, 0));
        academicRecordTop.setPadding(new Insets(10, 0, 10, 0));
        name.setText(personalInfo.getLastName() + " " + personalInfo.getFirstName());
        homeBorderPane.setCenter(academicRecordContainer);
        //handling the view transcript button
        viewButton.setOnMouseClicked(e ->{
            viewButton.setVisible(false);
            LoadMarksChart lb = new LoadMarksChart(studentInfo.getId());
            list = lb.getList();
            list1 = lb.getList1();
           if(!list.isEmpty() && !list1.isEmpty()){
            transcript.add(new Label("Course ID"), 5,0 );
            transcript.add(new Label("Marks"), 8,0 );
            transcript.add(new Label("Grade"), 11,0 );
            
            for(int i = 0; i < list1.size(); i++){
                transcript.add(new Label(list1.get(i).getIdCourse()), 5,i + 1 );
            }
            for(int i = 0; i < list.size(); i++){
                transcript.add(new Label(list.get(i).getFinalMark() + ""), 8,i + 1 );
            }
            for(int i = 0; i < list.size(); i++){
                transcript.add(new Label(computeGrade(list.get(i).getFinalMark())), 11,i + 1 );
            }
            
            academicRecordCenter.setVisible(false);
            transcript.setVisible(true);
            transcript.setAlignment(Pos.CENTER);
            transcript.setVgap(10.5);
            transcript.setHgap(20.5);
            transcript.setPadding(new Insets(10, 20, 10, 20));
            academicRecordContainer.setCenter(transcriptContainer);
            transcriptContainer.setPadding(new Insets(10));
           }
           else{
               System.out.println("either list or list1 is empty");
           }
            
            
        });

        
    }
    
    public void displayLabeledBarChart(Student studentInfo, Semester semester,int count){
         graphsContainer.getChildren().add(new LoadMarksChart(studentInfo.getId()).getMarkChart(semester));
         graphsContainer.setAlignment(Pos.CENTER);     
    }
    
    public void switchFromAcademicRecordToParent(AcademicRecordContainer category, Parent homeCenterPane,BorderPane homeBorderPane){
        homeCenterPane.setVisible(true);
        category.getCategoryContainer().setVisible(false);
        homeBorderPane.setCenter(homeCenterPane);       
    }    
    
    public void switchFromParentToAcademicRecord(Parent homeCenterPane,AcademicRecordContainer category,Person personalInfo,Student studentInfo,DatabaseConnectionManager manager,BorderPane homeBorderPane){
        homeCenterPane.setVisible(false);
        category.settingAcademicRecordBorderPane(personalInfo, studentInfo, manager, homeBorderPane);      
    }
    
    public String computeGrade(double mark){
        if(90 <= mark && mark <= 100){
            return " A ";
        }
        if(80 <= mark && mark < 90){
            return " B ";
        }
        if(70 <= mark && mark < 80){
            return " C ";
        }
        if(60 <= mark && mark < 70){
            return " D ";
        }
        if(50 <= mark && mark < 60){
            return " E ";
        }
      return " F ";
    }
    
    
    
}
