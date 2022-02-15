package com.example.springboot.controller;

import java.util.List;

import com.example.springboot.model.Student;
import com.example.springboot.service.SequenceGeneratorService;
import com.example.springboot.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SequenceGeneratorService service;

    @GetMapping("/")
    public String getAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        System.out.println("GET/");
        return "index";
    }

    @GetMapping("/new")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "registration";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        student.setId(service.getSequenceNumber(Student.SEQUENCE_NAME));
        System.out.println(student);
        System.out.println("PUT/");
        studentService.setStudent(student);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView updateStudent(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("update");
        Student student = studentService.getStudentById(id);
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @RequestMapping("/update")
    public String update(@ModelAttribute("student") Student student) {
        System.out.println(student);
        student = studentService.updateStudent(student);
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStudent(id);
        return "redirect:/";
    }
}
