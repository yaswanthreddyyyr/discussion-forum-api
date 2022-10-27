package com.yaswanth.pulse_labs_assignment.Controller;

import com.yaswanth.pulse_labs_assignment.Entities.Response;
import com.yaswanth.pulse_labs_assignment.Services.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class DeleteController {

    @Autowired
    DiscussionService discussionService;

    @DeleteMapping("/discussions/delete/{discussionId}")
     public Response deleteController(@PathVariable(value="discussionId") Long discussionId) {
        int result =0;
        try{
            result = discussionService.deleteDiscussion(discussionId);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        Response response = new Response();
        if(result >0){
            response.setStatusCode(200);
            response.setStatusMessage("Deleted Successfully ...");
        }
        else{
            response.setStatusCode(400);
            response.setStatusMessage("InValid Request");
        }
        return response;
     }
}
