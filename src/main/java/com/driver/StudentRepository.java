package com.driver;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;import java.util.HashMap;import java.util.List;
@Repository
public class StudentRepository
{
    HashMap<String,Student> studentDb=new HashMap<>();
    HashMap<String,Teacher> teacherDb=new HashMap<>();
    HashMap<String,List<String>> teacherStudentDb=new HashMap<>();

    public void addStudent(Student student)
    {
        String key = student.getName();
        studentDb.put(key,student);
    }

    public void addTeacher ( Teacher teacher)
    {
        String key=teacher.getName();
        teacherDb.put(key, teacher);
    }

    public void addStudentTeacherPair(String student,String teacher)
    {
        if(teacherStudentDb.containsKey(teacher))
        {
            List<String> students=teacherStudentDb.get(teacher);
            students.add(student);
            teacherStudentDb.put(teacher,students);
        }
        else
        {
            List<String> students=new ArrayList<String>();
            students.add(student);
            teacherStudentDb.put(teacher,students);
        }
    }

    public Student getStudentByName(String studentName)
    {
        return studentDb.get(studentName);
    }

    public Teacher getTeacherByName(String teacherName)
    {
        return teacherDb.get(teacherName);
    }

    public List<String> getStudentsByTeacherName(String teacherName)
    {
        return teacherStudentDb.get(teacherName);
    }

    public List<String> getAllStudents()
    {
        List<String> students =new ArrayList<>();
        for (Student obj: studentDb.values())
        {
            students.add(obj.getName());
        }

        return students;
    }
    public void deleteTeacherByName(String teacherName)
    {
        List<String> studentNames=teacherStudentDb.get(teacherName);

        for(String name: studentNames)
        {
            studentDb.remove(name);
        }

        teacherStudentDb.remove(teacherName);
        teacherDb.remove(teacherName);
    }

    public void deleteAllTeachers()
    {
        List<String> teachersList=new ArrayList<>();

        for(Teacher obj:teacherDb.values())
        {
            teachersList.add(obj.getName());
        }

        for (String teachersName: teachersList) {
            deleteTeacherByName(teachersName);
        }
    }


}