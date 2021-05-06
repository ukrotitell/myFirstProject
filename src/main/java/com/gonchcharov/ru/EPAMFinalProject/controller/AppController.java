package com.gonchcharov.ru.EPAMFinalProject.controller;

import com.gonchcharov.ru.EPAMFinalProject.entity.*;
import com.gonchcharov.ru.EPAMFinalProject.service.*;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class AppController {


    @Autowired
    DocumentService documentService;

    @Autowired
    StudentService studentService;

    @Autowired
    LabsService labsService;

    @Autowired
    UserService userService;

    @Autowired
    CoursesService coursesService;

    @Autowired
    TeacherService teacherService;


    @PostMapping("/students/showCourse/upload")
    public String uploadFile(@RequestParam("document") MultipartFile multipartFile,
                             @RequestParam("labId") int id,
                             RedirectAttributes redirectAttributes, Authentication authentication) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Document document = new Document();
        document.setName(fileName);

        document.setContent(multipartFile.getBytes());
        document.setSize(multipartFile.getSize());

        redirectAttributes.addFlashAttribute("message", "The file has been uploaded successfully");
        String userName = authentication.getName();
        System.out.println(userName);
        User theUser = userService.findByUserName(userName);
        Student student = studentService.findStudentById(theUser.getId());
        Labs lab = labsService.findLabsId(id);
        document.setStudent(student);
        document.setLab(lab);
        int courseId = lab.getCourseId();
        System.out.println(courseId);
        documentService.save(document);


        return "redirect:/students/showCourse?courseId=" + courseId;
    }


    @GetMapping("/teachers/showCourse/checkLabs")
    public String checkLabs(@RequestParam("labsId") int labsId,
                            Model model) {
        Labs lab = labsService.findLabsId(labsId);
        System.out.println(lab.getId());
        List<Document> documents = documentService.findDocumentsOfStudent(lab);

        model.addAttribute("documents", documents);


        return "labs";
    }

    @GetMapping("/teachers/showCourse/checkStudents")
    public String checkStudents(@RequestParam("id") int id, Model model) {

        Courses course = coursesService.findById(id);


        model.addAttribute("course", course);


        return "checkStudents";
    }

    @GetMapping("/download")
    public void downloadFile(@RequestParam("id") int id,
                             HttpServletResponse response) throws Exception {
        Optional<Document> result = Optional.ofNullable(documentService.findDocumentById(id));
        if (result.isEmpty()) {
            throw new Exception("Could not find document with ID: " + id);
        }
        Document document = result.get();
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + document.getName();

        response.setHeader(headerKey, headerValue);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(document.getContent());
        outputStream.close();
    }
}