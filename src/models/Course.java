package models;

import java.io.File;

/**
 *
 * @author Roger NDJEUMOU
 */
public class Course {

    private String idCourse;
    private String title;
    private String description;
    private int credit;
    private int passingScore;
    private File syllabus;
    private int idLecturer;

    public Course() {
    }

    public Course(String idCourse) {
        this.idCourse = idCourse;
    }
    
    public Course(String idCourse, String title) {
        this.idCourse = idCourse;
        this.title = title;
    }

    public Course(String idCourse, String title,
            String description, int credit, int passingScore,
            File syllabus, int idLecturer) {
        this.idCourse = idCourse;
        this.title = title;
        this.description = description;
        this.credit = credit;
        this.passingScore = passingScore;
        this.syllabus = syllabus;
        this.idLecturer = idLecturer;
    }

    public String getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(String idCourse) {
        this.idCourse = idCourse;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public char getPassingGrade() {
        return passingScore >= 80 ? 'B' : 'C';
    }

    public void setPassingScore(int passingScore) {
        this.passingScore = passingScore;
    }

    public File getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(File syllabus) {
        this.syllabus = syllabus;
    }

    public int getIdLecturer() {
        return idLecturer;
    }

    public void setIdLecturer(int idLecturer) {
        this.idLecturer = idLecturer;
    }

}
