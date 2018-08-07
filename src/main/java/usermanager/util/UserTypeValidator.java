package usermanager.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import usermanager.model.UserType;
import usermanager.service.UserTypeService;

import java.util.HashMap;

@Component
public class UserTypeValidator {
    @Autowired
    private UserTypeService userTypeService;

    public HashMap validate(UserType userType) {
        HashMap response = new HashMap();
        if(userType.getName()==null|| userType.getName() =="")response.put("name","empty values not allowed");
        else if(!userTypeService.getAllByName(userType.getName()).isEmpty()){
            UserType userType1 = userTypeService.getAllByName(userType.getName()).get(0) ;

            if(userType1.getId() != userType.getId())response.put("name","duplicate names not allowed");
        }
                if(userType.getDescription()==null|| userType.getDescription() =="")response.put("description","empty values not allowed");


        return response;
        }


}
