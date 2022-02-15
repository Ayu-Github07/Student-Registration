package com.example.springboot.service;

import java.util.List;

import com.example.springboot.model.Student;
import com.example.springboot.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void setStudent(Student student) {
        System.out.println(student);
        studentRepository.save(student);
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id).get();
    }

    public Student updateStudent(Student student) {
        List<Student> students = studentRepository.findAll();
        try {

            for (Student s : students) {
                if (s.getId() == student.getId()) {
                    studentRepository.save(student);
                }
            }
            return student;
        } catch (Exception e) {
            System.out.println("Student Not Found!!!");
            return null;
        }
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }
}
