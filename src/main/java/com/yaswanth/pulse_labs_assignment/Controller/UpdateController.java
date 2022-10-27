package com.yaswanth.pulse_labs_assignment.Controller;

import com.yaswanth.pulse_labs_assignment.Entities.Request;
import com.yaswanth.pulse_labs_assignment.Entities.Response;
import com.yaswanth.pulse_labs_assignment.Services.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class UpdateController {
    @Autowired
    DiscussionService discussionService;
    @PutMapping("/discussions/update")
    public Response updateController(@RequestBody Request request) throws SQLException {

        int result = 0;
        try{
            result=discussionService.updateDiscussion(request);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        Response response = new Response();
        if(result > 0){
            response.setStatusCode(200);
            response.setStatusMessage("Updated Successfully ... ");
        }
        else{
            response.setStatusCode(400);
            response.setStatusMessage("Invalid Request");
        }
        return response;
    }
}
