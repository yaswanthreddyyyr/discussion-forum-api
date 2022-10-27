package com.yaswanth.pulse_labs_assignment.Controller;


import com.yaswanth.pulse_labs_assignment.Entities.Response;
import com.yaswanth.pulse_labs_assignment.Entities.Request;
import com.yaswanth.pulse_labs_assignment.Services.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;


@RestController
public class PostController {

    @Autowired
    DiscussionService discussionService;

    @PostMapping("/discussions/insert")
    public Response postController(@RequestBody Request request){
        int result =0;
        Response res =new Response();
        if(request.getDiscussion().trim().length() == 0){
            res.setStatusCode(400);
            res.setStatusMessage("Invalid Request");
            return res;
        }
        request.setDiscussion(request.getDiscussion().trim());
        try{
             result = discussionService.insertDiscussion(request.getDiscussion(),request.getTags().split(","));

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        if(result>0){
            res.setStatusCode(200);
            res.setStatusMessage("Inserted Successfully...");

        }
        else{
            res.setStatusCode(400);
            res.setStatusMessage("Invalid Request");
        }
        return res;
    }


}
