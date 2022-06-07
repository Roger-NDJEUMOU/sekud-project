
package util;

/**
 *
 * @author pacha
 */
import database.DatabaseConnectionManager;
import java.util.ArrayList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import models.Course;
import models.FinalMark;
import models.Semester;

public class LoadMarksChart {

    DatabaseConnectionManager manager = new DatabaseConnectionManager();
    ArrayList<String> courseId = new ArrayList<>() ;
    ArrayList<Double> studentMarks = new ArrayList<>() ;
    ArrayList<Object> objects;
    ArrayList<FinalMark> list;
    ArrayList<Course> list1;
    ArrayList<Semester> list3 ;
    ArrayList<Semester> list2 = new ArrayList<>();
    //ArrayList<BarChart<String, Number>> charts;

    public LoadMarksChart(String idStudent) {
        objects = manager.getStudentFinalMarks(idStudent);
        list3 = (ArrayList<Semester>)objects.get(1);        
        list = (ArrayList<FinalMark>)objects.get(0);
        list1 = (ArrayList<Course>)objects.get(2);
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                courseId.add(list1.get(i).getIdCourse());
                studentMarks.add(list.get(i).getFinalMark());
            }
        
        for(int i = 1; i < list3.size(); i++){
            if(list3.get(i - 1).getIdSemester() != list3.get(i).getIdSemester()){
                list2.add(list3.get(i-1));
                list2.add(list3.get(i));
            }
        }
        }

    }

    public BarChart<Number,String> getMarkChart(Semester semester) {

        CategoryAxis yAxis = new CategoryAxis();
        yAxis.setLabel("Courses");
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Marks");

        XYChart.Series series = new XYChart.Series();
        series.setName("Marks for: " + semester.getSemesterName() + " " + semester.getSemesterYear());

        for (int i = 0; i < list.size(); i++) {
            if(list3.get(i).getIdSemester() == semester.getIdSemester())
                series.getData().add(new XYChart.Data(studentMarks.get(i), courseId.get(i)));
        }

        BarChart<Number,String > barChart = new BarChart<>(xAxis, yAxis);
        barChart.getData().add(series);
        barChart.setBarGap(3);

        return barChart;

    }

    public ArrayList<FinalMark> getList() {
        return list;
    }

    public ArrayList<Course> getList1() {
        return list1;
    }
    

}
