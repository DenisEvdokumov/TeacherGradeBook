package com.example.teachergradebook.data.repository.local;

import com.example.teachergradebook.data.database.StudentGroupDao;
import com.example.teachergradebook.data.model.Course;
import com.example.teachergradebook.data.model.Grade;
import com.example.teachergradebook.data.model.Practice;
import com.example.teachergradebook.data.model.Student;
import com.example.teachergradebook.data.model.StudentGroup;
import com.example.teachergradebook.data.repository.TableDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by Денис on 14.03.2018.
 */

public class LocalTableDataSource implements TableDataSource {

    private StudentGroupDao studentGroupDao;


    @Inject
    public LocalTableDataSource(StudentGroupDao studentGroupDao){
        this.studentGroupDao = studentGroupDao;
    }

    @Override
    public Flowable<List<Student>> loadStudents(boolean forceRemote) {
        return studentGroupDao.getAllStudent();
    }

    @Override
    public Flowable<List<StudentGroup>> loadStudentGroups(boolean forceRemote) {
        return studentGroupDao.getAllStudentGroup();
    }

    @Override
    public Flowable<List<Grade>> loadGrade(boolean forceRemote) {
        return studentGroupDao.getAllGrade();
    }

    @Override
    public Flowable<List<Practice>> loadPractice(boolean forceRemote) {
        return studentGroupDao.getAllPractice();
    }

    @Override
    public Flowable<List<Course>> loadCourse(boolean forceRemote) {
        return studentGroupDao.getAllCourse();
    }


    @Override
    public void addGroup(StudentGroup studentGroup) {
        studentGroupDao.insert(studentGroup);
    }

    @Override
    public void addStudent(Student student) {
        studentGroupDao.insert(student);
    }

    @Override
    public void addGrade(Grade grade) {
        studentGroupDao.insert(grade);
    }

    @Override
    public void addPractice(Practice practice) {
        studentGroupDao.insert(practice);
    }

    @Override
    public void addCourse(Course course) {

    }

    @Override
    public void clearData() {
        studentGroupDao.deleteAllStudentGroup();
        studentGroupDao.deleteAllStudent();
    }
}
