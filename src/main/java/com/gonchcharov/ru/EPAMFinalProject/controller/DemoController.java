package com.gonchcharov.ru.EPAMFinalProject.controller;

import com.gonchcharov.ru.EPAMFinalProject.entity.*;
import com.gonchcharov.ru.EPAMFinalProject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class DemoController {
    @Autowired
    private CoursesService coursesService;

    @Autowired
    private LabsService labsService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;


    @GetMapping("/teachers")
    public String showTeachers(Model model, Authentication authentication) {
        List<Courses> coursesList = coursesService.findAll();
        model.addAttribute("courses", coursesList);
        String userName = authentication.getName();
        User theUser = userService.findByUserName(userName);
        model.addAttribute("user", theUser);
        return "teachers";
    }


    @GetMapping("/students")
    public String showStudents(Model model) {
        List<Courses> coursesList = coursesService.findAll();
        model.addAttribute("courses", coursesList);
        return "students";
    }

    @GetMapping("/students/showCourse")
    public String showCourseForStudents(@RequestParam("courseId") int id,
                                        Model model, Authentication authentication) {
        Courses course = coursesService.findById(id);
        List<Labs> labs = labsService.findLabsByCourseId(id);
        model.addAttribute("labs", labs);
        model.addAttribute("course", course);


        return "courseStudents";

    }

    @GetMapping("/teachers/showCourse")
    public String showCourse(@RequestParam("courseId") int id,
                             Model model, Authentication authentication) {
        Courses course = coursesService.findById(id);
        List<Labs> labs = labsService.findLabsByCourseId(id);
        model.addAttribute("labs", labs);
        model.addAttribute("course", course);
        String userName = authentication.getName();
        User theUser = userService.findByUserName(userName);
        Teacher teacher = teacherService.findTeacherById(theUser.getId());
        if (teacher == course.getTeacher()) {
            return "courseTeachersIfRegistered";
        } else {
            return "courseTeachers";
        }
    }

    @PostMapping("/students/showCourse/save")
    public String saveStudentToCourse(@RequestParam("courseId") int id,
                                      Model model, Authentication authentication,
                                      RedirectAttributes redirectAttributes) {
        Courses course = coursesService.findById(id);
        String userName = authentication.getName();
        User theUser = userService.findByUserName(userName);
        Student student = studentService.findStudentById(theUser.getId());
        redirectAttributes.addFlashAttribute("message1", "Вы успешно записались на курс");
        model.addAttribute("student", student);
        model.addAttribute("course", course);
        studentService.save(student);
        course.addStudent(student);
        coursesService.save(course);
        return "redirect:/students/showCourse?courseId=" + id;
    }

    @PostMapping("/teachers/showCourse/save")
    public String saveTeacherToCourse(@RequestParam("courseId") int id,
                                      Model model, Authentication authentication,
                                      RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Вы стали наставником курса");
        Courses course = coursesService.findById(id);
        String userName = authentication.getName();
        System.out.println(userName);
        User theUser = userService.findByUserName(userName);
        Teacher teacher = teacherService.findTeacherById(theUser.getId());
        System.out.println(teacher.getId());
        System.out.println(course.getId());
        model.addAttribute("teacher", teacher);
        model.addAttribute("course", course);
        teacher.addCourse(course);
        coursesService.save(course);
        return "redirect:/teachers/showCourse?courseId=" + id;
    }
}










