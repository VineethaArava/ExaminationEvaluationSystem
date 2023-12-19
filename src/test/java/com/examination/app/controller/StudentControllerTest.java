package com.examination.app.controller;

import com.examination.app.dto.AnswerDTO;
import com.examination.app.dto.StudentRegisterDTO;
import com.examination.app.entity.Exam;
import com.examination.app.entity.ExamPaper;
import com.examination.app.entity.ExamResult;
import com.examination.app.entity.Student;
import com.examination.app.service.StudentService;
import com.examination.app.service.UserService;
import com.examination.app.util.UserRoleEnum;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentService studentService;

    Student student = new Student(1,"John Doe","LA, 324, US","9900990099");
    StudentRegisterDTO studentRegisterDTO = new StudentRegisterDTO(null,"John Doe", "john@gmail.com","123123", UserRoleEnum.STUDENT,"LA, 324, US", "9900990099");
    Exam exam = new Exam(1, "Java JPA",3.5F,"10:00AM",5,false);
    AnswerDTO answerDTO = new AnswerDTO(1,1,new ArrayList<>());
    ExamResult examResult = new ExamResult(1,"A+",98f,student,exam);
    List<ExamPaper> examPapers = new ArrayList<>();
    Gson gson = new Gson();

    @BeforeEach
    void setUp() {
        examPapers.add(new ExamPaper(1,exam,"What does the 'npm init' command do in Node.js?",
                "Installs Node.js on the system","Initializes a new Node.js project","Runs unit tests","Upgrades Node.js to the latest version",
                "Initializes a new Node.js project", 10));
        examPapers.add(new ExamPaper(2,exam, "Which package manager is commonly used for managing Node.js dependencies?", "NPM", "Bower", "Yarn", "Composer", "NPM", 20));
        examPapers.add(new ExamPaper(3,exam ,"In Node.js, what is the purpose of the 'require' function?", "To include external modules", "To declare variables", "To define functions", "To create loops", "To include external modules", 20));
        examPapers.add(new ExamPaper(4,exam, "Which event is emitted when a new client connects to a Node.js server?", "onRequest", "onConnect", "onConnection", "onOpen", "onConnection", 20));
        examPapers.add(new ExamPaper(5, exam,"What does the 'npm init' command do in Node.js?", "Installs Node.js on the system", "Initializes a new Node.js project", "Runs unit tests", "Upgrades Node.js to the latest version", "Initializes a new Node.js project", 20));
    }
    @Test
    void register() throws Exception{
        when(studentService.register(studentRegisterDTO)).thenReturn(student);
        this.mockMvc.perform(post("/api/student/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(studentRegisterDTO)));
                //.andExpect(status().isOk())
                //.andExpect(content().json(gson.toJson(student)));
    }

    @Test
    void submitExam() throws Exception{
        when(studentService.register(studentRegisterDTO)).thenReturn(student);
        this.mockMvc.perform(post("/api/student/submitExam")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(answerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Exam successfully submitted")));
    }

    @Test
    void giveExam() throws Exception{
        when(studentService.giveExam(exam.getId())).thenReturn(examPapers);
        this.mockMvc.perform(get("/api/student/giveExam/"+exam.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(examPapers)));
    }

    @Test
    void examResult() throws Exception{
        when(studentService.checkResult(exam.getId(),student.getId())).thenReturn(examResult);
        this.mockMvc.perform(get("/api/student/examResult/"+exam.getId()+"/"+student.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(examResult)));
    }
}