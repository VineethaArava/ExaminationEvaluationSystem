package com.examination.app.controller;

import com.examination.app.entity.*;

import com.examination.app.service.ExaminerService;

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
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ExaminerControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    ExaminerService examinerService;

    List<Exam> exams = new ArrayList<>();
    List<ExamResponse> examResponses = new ArrayList<>();
    List<ExamResult> examResults = new ArrayList<>();
    ExamPaper examPaper = null;
    Student student = null;

    Gson gson = new Gson();

    @BeforeEach
    void setUp() {



        exams.add(new Exam(1, "Java Basics Exam", 120.5f, "2023-01-15 09:00:00", 30, false));
        exams.add(new Exam(2, "Advanced Java Concepts", 180.0f, "2023-02-10 14:30:00", 40, false));
        exams.add(new Exam(3, "Spring Framework Test", 90.75f, "2023-03-05 11:15:00", 20, false));
        exams.add(new Exam(4, "Hibernate Quiz", 60.25f, "2023-04-20 10:00:00", 15, false));
        exams.add(new Exam(5, "Web Development Challenge", 150.0f, "2023-05-12 18:45:00", 35, false));

        examPaper = new ExamPaper(4,exams.get(3), "Which event is emitted when a new client connects to a Node.js server?", "onRequest", "onConnect", "onConnection", "onOpen", "onConnection", 20);
        student = new Student(1,"John Doe","LA, 324, US","9900990099");

        examResponses.add(new ExamResponse(1,student,examPaper, examPaper.getCorrectOption(), true));

        examResults.add(new ExamResult(1, "A+",93F,student,exams.get(1)));
        examResults.add(new ExamResult(2, "F+",23F,student,exams.get(2)));
        examResults.add(new ExamResult(3, "B-",80F,student,exams.get(3)));
        examResults.add(new ExamResult(4, "A+",98F,student,exams.get(4)));

        Exam ex = exams.get(1);
        ex.setResultDeclared(true);

        when(examinerService.getAllExams()).thenReturn(exams);
        when(examinerService.prepareTestPaper(exams.get(0))).thenReturn(exams.get(0));
        when(examinerService.modifyTestPaper(exams.get(1))).thenReturn(exams.get(1));
        when(examinerService.addQuestionToExam(examPaper)).thenReturn(examPaper);
        when(examinerService.findCopiesByExam(4)).thenReturn(examResults.stream().filter(item -> item.getExam().getId().equals(4)).collect(Collectors.toList()));
        when(examinerService.findCopiesByExamStudent(4, 1)).thenReturn(examResponses);
        when(examinerService.declareResult(2)).thenReturn(ex);
    }

    @Test
    void getAllExams() throws Exception {
        this.mockMvc.perform(get("/api/examiner/getAllExams")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(exams)));
    }

    @Test
    void createExam() throws Exception{
        this.mockMvc.perform(post("/api/examiner/createExam")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(exams.get(0))));
                //.andExpect(status().isOk())
                //.andExpect(content().json(gson.toJson(exams.get(0))));
    }

    @Test
    void updateExam()  throws Exception{
        this.mockMvc.perform(put("/api/examiner/updateExam")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(exams.get(1))));
                //.andExpect(status().isOk())
                //.andExpect(content().json(gson.toJson(exams.get(1))));
    }

    @Test
    void addQuestionToExam()  throws Exception{
        this.mockMvc.perform(post("/api/examiner/updateExam")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(exams.get(1))));
                //.andExpect(status().isOk())
                //.andExpect(content().json(gson.toJson(exams.get(1))));
    }

    @Test
    void findCopiesByExam()  throws Exception{
        List<ExamResult> expectedResult = examResults.stream().filter(item -> item.getExam().getId().equals(4)).collect(Collectors.toList());
        this.mockMvc.perform(get("/api/examiner/findCopiesByExam/4")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(expectedResult)));
    }

    @Test
    void findCopiesByExamStudent()  throws Exception{
        this.mockMvc.perform(get("/api/examiner/findCopiesByExamStudent/4/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(examResponses)));
    }

    @Test
    void declareResult()  throws Exception{
        Exam expectedRes = exams.get(1);
        expectedRes.setResultDeclared(true);
        this.mockMvc.perform(get("/api/examiner/declareResult/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(expectedRes)));
    }
}