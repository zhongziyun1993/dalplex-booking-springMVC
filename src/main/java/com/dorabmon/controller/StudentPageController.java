package com.dorabmon.controller;

import com.dorabmon.model.Course;
import com.dorabmon.model.Court;
import com.dorabmon.model.User;
import com.dorabmon.service.course.CourseService;
import com.dorabmon.service.court.CourtService;
import com.dorabmon.service.studentCourse.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/student_page")
public class StudentPageController {


    @Autowired
    private CourtService courtService;

    @Autowired
    private StudentCourseService studentCourseService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView admin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        ModelAndView mav = new ModelAndView("student_page");
        List<Course> studentCourseList = new ArrayList<>();
        List<Court> studentCourtList = new ArrayList<>();

        HttpSession httpSession = httpServletRequest.getSession();

        if (httpSession.getAttribute("currentUser") == null) {
            ModelAndView mav1 = new ModelAndView("homePage");
            try {
                httpServletResponse.sendRedirect("/");
            } catch (IOException e) {
                e.printStackTrace();
            }

            return mav1;
        }

        User user = (User) httpSession.getAttribute("currentUser");
        mav.addObject("currentUser", user);

        // student's course
//        User user = new User("Ye Fang","123456","902111111","ye.fang@dal.ca","2017-09-01",300,"user");
        studentCourseList = studentCourseService.FindCourseListByStudentId(user); // pass a user from other view
        mav.addObject("studentCourseList", studentCourseList);
        // render court by student ID
        studentCourtList = courtService.listCourtsByStudentID(user.getStudent_id());
        mav.addObject("studentCourtList", studentCourtList);

        return mav;
    }

    //        delete booking by courseID, studentID
    @RequestMapping(value = "/{courseid}", method = RequestMethod.GET)
    public ModelAndView coursedelete(@PathVariable("courseid") String courseid, HttpServletRequest httpServletRequest) {

        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute("currentUser");
        studentCourseService.Delete(user.getStudent_id(), Integer.parseInt(courseid));
        // return
        ModelAndView modelAndView = new ModelAndView("student_page");
        return modelAndView;
    }


}
