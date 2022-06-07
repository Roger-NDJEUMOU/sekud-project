/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author atako
 */
public class Semester {
    private int idSemester;
    private String semesterName;
    private int semesterYear;

    public Semester() {
    }

    public Semester(int idSemester) {
        this.idSemester = idSemester;
    }

    public Semester(int idSemester, String semesterName) {
        this.idSemester = idSemester;
        this.semesterName = semesterName;
    }

    public Semester(String semesterName, int semesterYear) {
        this.semesterName = semesterName;
        this.semesterYear = semesterYear;
    }

    public Semester(int idSemester, String semesterName, int semesterYear) {
        this.idSemester = idSemester;
        this.semesterName = semesterName;
        this.semesterYear = semesterYear;
    }
    

    public int getSemesterYear() {
        return semesterYear;
    }

    public void setSemesterYear(int semesterYear) {
        this.semesterYear = semesterYear;
    }
    
    

    public int getIdSemester() {
        return idSemester;
    }

    public void setIdSemester(int idSemester) {
        this.idSemester = idSemester;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }
    
    
    
}
