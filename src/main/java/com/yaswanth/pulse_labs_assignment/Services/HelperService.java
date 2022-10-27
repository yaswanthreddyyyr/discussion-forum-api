package com.yaswanth.pulse_labs_assignment.Services;

import com.yaswanth.pulse_labs_assignment.Entities.Discussion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelperService {
    public String convertListToString(List<Discussion> values){
        String statement="(";
        for(int i=0;i<values.size();i++){
            statement+=values.get(i).getDiscussionId().toString();
            if(i!=values.size()-1)
                statement+=",";
        }
         statement+=")";
        System.out.println(statement);
        return statement;
    }
}
