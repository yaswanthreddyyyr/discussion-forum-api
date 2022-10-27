package com.yaswanth.pulse_labs_assignment.Controller;

import com.yaswanth.pulse_labs_assignment.Entities.Discussion;
import com.yaswanth.pulse_labs_assignment.Entities.GetResponse;
import com.yaswanth.pulse_labs_assignment.Services.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class GetController {
	@Autowired
	DiscussionService discussionService;

    @GetMapping("/discussions/getList")
    public GetResponse getController(@RequestParam Optional<Long> page, @RequestParam Optional<String> tagName, @RequestParam Optional<String> query, @RequestParam Optional<Long> fromDate, @RequestParam Optional<Long> toDate){
        Long pageNumber;
        if(page.equals(Optional.empty())){
            pageNumber=0L;
        }
        else{
            pageNumber=page.get();
        }


        List<Discussion> discussions= Collections.emptyList();
        GetResponse response = new GetResponse();
        try{

            discussions=discussionService.getDiscussions(pageNumber,tagName,query,fromDate,toDate);
            response.setStatusMessage("Success");
            response.setStatusCode(200);
            response.setDiscussions(discussions);
            response.setPageNumber(pageNumber);
            if(discussions.size()<10){
                response.setHasMore(false);
            }
            else {
                response.setHasMore(true);
            }
        }
        catch (Exception e){
            response.setStatusCode(400);
            response.setStatusMessage("Invalid Request");

        }
        return response;

    }

}
