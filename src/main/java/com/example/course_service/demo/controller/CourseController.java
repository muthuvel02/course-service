package com.example.course_service.demo.controller;


import com.example.course_service.demo.dto.Course;
import com.example.course_service.demo.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RestController
@RequestMapping("/course")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody Course course) {
        Course newCourse = courseService.onboardNewCourse(course);
        return new ResponseEntity<>(newCourse, HttpStatus.CREATED);//201
    }

    @GetMapping
    public ResponseEntity<?> findALlCourse(){
        return new ResponseEntity<>(courseService.viewAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/search/path/{courseId}")
    public ResponseEntity<?> findCourse(@PathVariable Integer courseId){
        return new ResponseEntity<>(courseService.findByCourseId(courseId),HttpStatus.OK);
    }

    @GetMapping("/search/request")
    public ResponseEntity<?> findCourseUsingRequestParam(@RequestParam(required = false) Integer courseId){
        return new ResponseEntity<>(courseService.findByCourseId(courseId),HttpStatus.OK);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable int courseId){
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable int courseId, @RequestBody Course course){
        return new ResponseEntity<>(courseService.updateCourse(courseId, course), HttpStatus.OK);
    }


}