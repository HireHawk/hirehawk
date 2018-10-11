package com.hirehawk.feedback_service.controllers;

import com.hirehawk.feedback_service.dao.FeedbackDAO;
import com.hirehawk.feedback_service.dao.USERROLE;
import com.hirehawk.feedback_service.entity.Feedback;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.json.simple.*;

import java.util.Date;
import java.util.List;


@Controller
@Transactional
public class TestController {

    @Autowired
    private FeedbackDAO feedbackDAO;

    @RequestMapping("/")
    public RedirectView home() {
        feedbackDAO.saveFeedback(4, "some comment", new Date(2018,10,8,18,28,20),
                1, 2, USERROLE.GIVER);
        feedbackDAO.saveFeedback(3, new Date(2018,10,8,17,10,20),
                1, 2, USERROLE.GIVER);
        feedbackDAO.saveFeedback(2, "some another comment", new Date(2018,10,8,18,10,20),
                1, 2, USERROLE.GIVER);

        List<Feedback> feedbacks = feedbackDAO.getFeedbacks(2, USERROLE.GIVER);
        System.out.println(feedbacks.size());
        feedbacks = feedbackDAO.getFeedbacksWithoutComments(2, USERROLE.GIVER);
        System.out.println(feedbacks.size());
        feedbacks = feedbackDAO.getFeedbacksWithComments(2, USERROLE.GIVER);
        System.out.println(feedbacks.size());
        // return "index";
        return new RedirectView("feedback");
    }

    @GetMapping("/feedback")
    public String feedback() {
        feedbackDAO.saveFeedback(4, "some comment", new Date(2018,10,8,18,28,20),
                1, 2, USERROLE.GIVER);
        return "index";
    }

    @PostMapping("/fs1")
    public String g()
            /*(@PathVariable int mark, @PathVariable String comm, @PathVariable Date data,
                    @PathVariable Integer who, @PathVariable Integer about, @PathVariable String role)*/ {
                        System.out.println("cgjfjgk");
       //  System.out.println(smth);
        USERROLE role_;

        /*System.out.println(mark+comm+data+who+about+role);
            feedbackDAO.saveFeedback(mark, comm, data,
                    who, about, USERROLE.BOTH);
            System.out.println("saved");*/

        //JsonObject jsonObject = new JSONParser().parse(json).getAsJsonObject();

        return "index";
    }
}


