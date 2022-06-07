
package util;


import com.jfoenix.controls.JFXTextField;
import controllers.HomeController;
import database.DatabaseConnectionManager;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import main.Main;
import models.Person;
import models.Student;

/**
 *
 * @author pacha
 */
public class MyProfileCenterContainer {
    
    private final BorderPane profileContainer = new BorderPane();
    private final GridPane profileCenter = new GridPane();
    private final ScrollPane pane1 = new ScrollPane(profileCenter);
    private final Label pseudo = new Label("Pseudo: ");
    private final Label name = new Label("name: ");
    private final Label dob = new Label("Date Of Birth: ");
    private final Label faculty = new Label("Faculty: ");
    private final Label department = new Label("Department: ");
    private final Label id = new Label("Registration Number: ");
    private final Label program = new Label("Program: ");
    private final Label entranceYear = new Label("Entrance Year: ");
    private final Label level = new Label("Level: ");
    private final Label phoneNumber = new Label("Phone Number: ");
    private final Label email = new Label("Email: ");
    private final Label address = new Label("Address: ");
    private final Label pseudoValue = new Label();
    private final Label nameValue = new Label();
    private final Label dobValue = new Label();
    private final Label facultyValue = new Label();
    private final Label departmentValue = new Label();
    private final Label idValue = new Label();
    private final Label programValue = new Label();
    private final Label entranceYearValue = new Label();
    private final Label levelValue = new Label();
    private final Label phoneNumberValue = new Label();
    private final Label emailValue = new Label();
    private final Label addressValue = new Label();
    private final Button editPseudo = new Button("Edit pseudo");
    private final Button editProfilePicture = new Button("Edit picture");
    private final Ellipse ellipse = new Ellipse();
    private final StackPane imageContainer = new StackPane(ellipse);
    private final Button save = new Button("Save");
    private final Button cancel = new Button("Cancel");
    private final TextField t1 = new TextField();

    public MyProfileCenterContainer() {
    }
    
    public void settingMyProfileBorderPane(Person personalInfo,Student studentInfo,DatabaseConnectionManager manager,BorderPane homeBorderPane,ScrollPane homeCenterPane,HomeController homeController){
        profileContainer.getStylesheets().add("/css/myacademicrecord.css");
        profileContainer.setCenter(pane1);
        
        ellipse.setFill(null);
        ellipse.setStroke(Color.PINK);
        ellipse.setRadiusX(60.5);
        ellipse.setRadiusY(62.5);
        
        ImageView profileView = new ImageView(personalInfo.getPhoto());
        //resizing the image to have width of 100 while preserving the ratio and using
        //higher quality filtering method
        //improve performance
        profileView.setFitWidth(100);
        profileView.setPreserveRatio(true);
        profileView.setSmooth(true);
        profileView.setCache(true);
        imageContainer.getChildren().add(profileView);
        imageContainer.setPadding(new Insets(12));
        profileCenter.setHgap(2.5);
        profileCenter.setVgap(10.5);
        profileCenter.add(imageContainer, 0, 0);
        profileCenter.add(editProfilePicture, 1, 0);
        editProfilePicture.setTooltip(new Tooltip("Edit Your Profile Picture"));
        editPseudo.setTooltip(new Tooltip("Edit Your Pseudo"));
        save.setTooltip(new Tooltip("Click to save your new pseudo"));
        cancel.setTooltip(new Tooltip("Click to exit"));
        save.setStyle("-fx-background-color: #2fd37c ;");
        cancel.setStyle("-fx-background-color: #dd4826 ;");
        
        //ADDING LABEL TO COLUMN 0 OF THE GRIDPANE
        profileCenter.add(pseudo, 1, 1);
        
        profileCenter.add(name, 1, 2);
        
        profileCenter.add(dob, 1, 3);
        
        profileCenter.add(faculty, 1, 4);
        
        profileCenter.add(id, 1, 5);
        
        profileCenter.add(program, 1, 6);
        
        profileCenter.add(entranceYear, 1, 7);
        
        profileCenter.add(level, 1, 8);
        
        profileCenter.add(phoneNumber, 1, 9);
        
        profileCenter.add(email, 1, 10);
        
        profileCenter.add(address, 1, 11);
        
        //ADDING LABEL TO COLUMN 5 OF THE GRIDPANE
        profileCenter.add(pseudoValue, 5, 1);
        pseudoValue.setText(personalInfo.getPseudo());
        
        //add edit pseudo button
        profileCenter.add(editPseudo, 7, 1);
        //
        
        profileCenter.add(nameValue, 5, 2);
        if(personalInfo.getFirstName() == null){
            nameValue.setText(personalInfo.getLastName());
        }
        else{
            nameValue.setText(personalInfo.getLastName() + " " + personalInfo.getFirstName());
        }
        
        
        profileCenter.add(dobValue, 5, 3);
        dobValue.setText(personalInfo.getDateOfBirth().toString());
        
        profileCenter.add(facultyValue, 5, 4);
        facultyValue.setText(manager.getProgramNameAndFacultyNameFromASpecificStudent(studentInfo.getId()).get(0));
        
        profileCenter.add(idValue, 5, 5);
        idValue.setText(studentInfo.getId());
        
        profileCenter.add(programValue, 5, 6);
        programValue.setText(manager.getProgramNameAndFacultyNameFromASpecificStudent(studentInfo.getId()).get(1));
        
        profileCenter.add(entranceYearValue, 5, 7);
        entranceYearValue.setText(studentInfo.getEntranceYear() + "");
        
        profileCenter.add(levelValue, 5, 8);
        levelValue.setText(manager.getLevelNameOfASpecificStudent(studentInfo.getId()));
        
        profileCenter.add(phoneNumberValue, 5, 9);
        
        if(personalInfo.getPhoneNumber() == null){
            phoneNumberValue.setText("no phone number ");
            phoneNumberValue.setStyle("-fx-text-fill: #9f9f9f ;");
        }
        else{
            phoneNumberValue.setText(personalInfo.getPhoneNumber());
        }
        
        profileCenter.add(emailValue, 5, 10);
        if(personalInfo.getEmail() == null){
            emailValue.setText("no email ");
            emailValue.setStyle("-fx-text-fill: #9f9f9f ;");
        }
        else{
            emailValue.setText(personalInfo.getEmail());
        }
        
        profileCenter.add(addressValue, 5, 11);
        addressValue.setText(personalInfo.getAddress());
        
        
        //handle the editPseudo Button
        editPseudo.setOnMouseClicked(e ->{
            GridPane pane = new GridPane();
            BorderPane bp = new BorderPane(pane);
            pane.getStylesheets().add("/css/myacademicrecord.css");
            pane.setHgap(6.5);
            pane.setVgap(6.5);
            save.setPadding(new Insets(8));
            cancel.setPadding(new Insets(8));
            Label label = new Label("Pseudo: ");
            label.setPadding(new Insets(8));
            
            t1.setPadding(new Insets(3));
            t1.setPromptText("Enter Your New Pseudo");
            pane.add(label, 2, 2);
            pane.add(t1, 3, 2);
            pane.add(save, 3, 3);
            pane.add(cancel, 2, 3);
            GridPane.setHalignment(save, HPos.RIGHT);
            GridPane.setHalignment(cancel, HPos.CENTER);
            Stage stage = new Stage();
            Scene scene = new Scene(bp,300,150);
            stage.setScene(scene);
            stage.setTitle("Edit Pseudo");
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/sekud-logo.jpg")));
            stage.setResizable(false);
            stage.show();
            //handling the save button
            save.setOnMouseClicked(a ->{
                if(t1.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error!!!");
                    alert.setContentText("You must enter a pseudo!!");
                    alert.showAndWait();
                }
                else{
                    manager.setPersonPseudo(studentInfo.getId(), t1.getText());
                    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmation.setHeaderText("Update Successfully!!!");
                    confirmation.setContentText("Your pseudo has been updated successfully!");
                    confirmation.showAndWait();
                    pseudoValue.setText(t1.getText());
                    homeController.setWelcomeLabel(this);
                    //Close the edit window
                    Node node = (Node)a.getSource();
                    Stage stage1 = (Stage)node.getScene().getWindow();
                    stage1.close();
                }
            });
            
            //handling the cancel button
            cancel.setOnMouseClicked(p ->{
                    //Close the edit window
                    Node node = (Node)p.getSource();
                    Stage stage1 = (Stage)node.getScene().getWindow();
                    stage1.close();    
            });
        });
        
        
        
    }

    public BorderPane getProfileContainer() {
        return profileContainer;
    }

    public Label getPseudoValue() {
        return pseudoValue;
    }
    
    
    
    
    public void switchFromAcademicRecordToParent(MyProfileCenterContainer profile, Parent homeCenterPane,BorderPane homeBorderPane){
        homeCenterPane.setVisible(true);
        profile.getProfileContainer().setVisible(false);
        homeBorderPane.setCenter(homeCenterPane);       
    }
    
    public void switchFromParentToAcademicRecord(MyProfileCenterContainer profile,Person personalInfo,Student studentInfo,DatabaseConnectionManager manager,BorderPane homeBorderPane,ScrollPane homeCenterPane){
        homeCenterPane.setVisible(false);
        profile.getProfileContainer().setVisible(true);
        homeBorderPane.setCenter(profile.getProfileContainer());
    }


}
