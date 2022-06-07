package controllers;

import database.DatabaseConnectionManager;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Main;
import models.Course;
import models.CourseSchedule;
import models.Login;
import models.Person;
import models.Semester;
import models.Student;
import models.Timetable;
import util.AcademicRecordContainer;
import util.MyDate;
import util.MyProfileCenterContainer;

/**
 * FXML Controller class
 *
 * @author Pacha
 */
public class HomeController implements Initializable {

    Login loginInfo;
    Person personalInfo;
    Student studentInfo;
    Timetable timetable;
    ArrayList<CourseSchedule> schedules;
    MyDate dateUtil = new MyDate();
    DatabaseConnectionManager manager
            = new DatabaseConnectionManager();

    @FXML
    private VBox boxClassesToday;

    @FXML
    private Label dateTimeLabel;

    @FXML
    private ImageView userPhoto;

    @FXML
    private Label welcomeLabel;
    @FXML
    private ScrollPane homeCenterPane;
 
    @FXML
    private BorderPane homeBorderPane; 
    
    @FXML
    private Button btnMyAcademicRecord;  
    
    @FXML
    private Button btnMyProfile;    
    
    int numberOfSemester ;
    
    ArrayList<Object> objects;
    ArrayList<Semester> list;
    
    
    AcademicRecordContainer academicRecord = new AcademicRecordContainer();
    MyProfileCenterContainer profile = new MyProfileCenterContainer();

    @FXML
    void openTimetable(ActionEvent event) {
        (new Main()).viewPDF(timetable.getTimetable());
    }
    
    @FXML
    void onHome(ActionEvent event) {
        
        academicRecord.switchFromAcademicRecordToParent(academicRecord, homeCenterPane, homeBorderPane);

    }
    

    


    @FXML
    void onMyAcademicRecord(ActionEvent event) throws IOException{
        academicRecord.switchFromParentToAcademicRecord(homeCenterPane, academicRecord, personalInfo, studentInfo, manager, homeBorderPane);
    }
    
    @FXML
    void onMyProfile(ActionEvent event) {
        profile.switchFromParentToAcademicRecord(profile, personalInfo, studentInfo, manager, homeBorderPane, homeCenterPane);

    }

    @FXML
    void onSignOut(ActionEvent event) throws IOException {
        Node node = (Node)event.getSource();
        Stage stage1 = (Stage)node.getScene().getWindow();
        stage1.close();
        Stage stage = new Stage();
        Locale.setDefault(Locale.ENGLISH);
        Parent root = FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));           
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/sekud-logo.jpg")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.setMaximized(true);
        stage.show();  
        
    }

    public Button getMyAcademicRecordButton() {
        return btnMyAcademicRecord;
    }
    
    //This method is used to load the charts representing the marks of the student per semester in the scrollPane
    //So that the myAcademic button is now used to just switch to the academic view!
    public void loadChartsIntoTheMyAcademicRecordScrollPane(){
        numberOfSemester = manager.getStudentDistinctSemesterIdFromFinalMarkTable(studentInfo.getId());
        objects = manager.getStudentFinalMarks(studentInfo.getId());
        list = (ArrayList<Semester>)objects.get(1);
        ArrayList<Semester> list2 = new ArrayList<>();
        for(int i = 1; i < list.size(); i++){
            if(list.get(i - 1).getIdSemester() != list.get(i).getIdSemester()){
                list2.add(list.get(i-1));
                list2.add(list.get(i));
            }
        }
        for (int i = 0; i < numberOfSemester; i++) {
            academicRecord.displayLabeledBarChart(studentInfo, list2.get(i),numberOfSemester);
        }         
    }
    
    public void loadMyProfileContent(){
        profile.settingMyProfileBorderPane(personalInfo, studentInfo, manager, homeBorderPane, homeCenterPane,this);
    }
    
    

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Get user login info from stage and 
        // use it to retrieve user personal info
        btnMyAcademicRecord.setTooltip(new Tooltip("Click to view your academic record"));
        btnMyProfile.setTooltip(new Tooltip("Click to view your personal profile"));
        homeCenterPane.setVisible(true);
        academicRecord.getViewButton().setVisible(true);
        loginInfo = (Login) main.Main.getStage().getUserData();
        personalInfo = manager.getPersonInfo(loginInfo.getId());

        if (loginInfo.getType().equalsIgnoreCase("STUDENT")) {
            studentInfo = manager.getStudentInfo(loginInfo.getRegNumber());
            timetable = manager.getTimetable(studentInfo.getIdProgram(), "Spring", 2022);

            setMyClassesToday();
        }

        setWelcomeLabel(profile);
        setDateTimeLabel();
        setProfilePicture();
        
        //Call the method so that when the home view is displayed it automatically generates the charts so that 
        //they are just generated once for a student
        loadChartsIntoTheMyAcademicRecordScrollPane();
        
        //method to load the content of the my profile once when we enter the home window
        loadMyProfileContent();
    }

    private void setMyClassesToday() {
        ArrayList<Object> objects
                = manager.getSchedule(studentInfo.getIdProgram(),
                        studentInfo.getIdLevel(),
                        dateUtil.getWeekDay());
        
        schedules = (ArrayList<CourseSchedule>) objects.get(0);
        ArrayList<Course> courses = (ArrayList<Course>) objects.get(1);

        if (!schedules.isEmpty()) {
            
            for (int i = 0; i < schedules.size(); i++) {
                CourseSchedule sched = schedules.get(i);
                String content = courses.get(i).getTitle() + " ("
                        + courses.get(i).getIdCourse() + ")";
                String periodAndHall = sched.getStartTime()
                        + " - " + sched.getEndTime() + " in "
                        + sched.getRoom();

                Label classToday = new Label(periodAndHall
                        + " \n  " + content);

                boxClassesToday.getChildren().add(classToday);
            }
        } else {
            Label noClassLabel = new Label("No class today.") ;
            boxClassesToday.getChildren().add(noClassLabel);
        }
        
        Button btnViewTimetable = new Button("My Timetable");
        btnViewTimetable.setAlignment(Pos.CENTER);
        btnViewTimetable.setOnAction((ActionEvent event) -> {
            openTimetable(event);
        });
        
        StackPane pane = new StackPane(btnViewTimetable) ;
        
        boxClassesToday.getChildren().add(pane) ;
    }

    public void setWelcomeLabel(MyProfileCenterContainer profile) {
        if(profile.getPseudoValue().getText().isEmpty()){
            welcomeLabel.setText("Welcome \""
                + manager.getPseudo(loginInfo.getId()) + "\"");            
        }
        else{
            welcomeLabel.setText("Welcome \""
                + profile.getPseudoValue().getText() + "\"");            
        }

    }

    private void setDateTimeLabel() {
        dateTimeLabel.setText(dateUtil.toString());
    }

    private void setProfilePicture() {
        userPhoto.setImage(personalInfo.getPhoto());
    }

}
