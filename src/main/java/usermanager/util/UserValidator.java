package usermanager.util;


import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import usermanager.model.User;
import usermanager.service.UserTypeService;
import java.util.HashMap;

@Component
public class UserValidator {
    @Autowired
    private UserTypeService userTypeService;
    public HashMap validate(User user){
        HashMap response = new HashMap();



if(user.getFirstName()==null || user.getFirstName() == "")response.put("firstName","empty values not allowed");
else if(user.getFirstName().length()<3 || user.getFirstName().length()>20)response.put("firstName","length beyond 3-20 not allowed");
else if(user.getFirstName().matches(".*\\d+.*"))response.put("firstName","numeriacal charactors not allowed");

if(user.getLastName()==null || user.getLastName() == "")response.put("lastName","empty values not allowed");
else if(user.getLastName().length()<3 || user.getLastName().length()>20)response.put("lastName","length beyond 3-20 not allowed");
else if(user.getLastName().matches(".*\\d+.*"))response.put("lastName","numeriacal charactors not allowed");

if(user.getEmail()==null || user.getEmail() == "")response.put("email","empty values not allowed");
else if(!EmailValidator.getInstance().isValid(user.getEmail()))response.put("email","non email types not allowed");
else if(user.getEmail().length()>100)response.put("email","length exceed 100 not allowed");

if(user.getDateOfBirth()==null)response.put("dateOfBirth","empty values not allowed");

if(user.getCity()==null || user.getCity() == "")response.put("city","empty values not allowed");
else if(user.getCity().length()<3 ||user.getCity().length()>50 )response.put("city","length beyond 3-50 not allowed");

if(user.getUserType().getId()==null)response.put("userType","empty values not allowed");
else if(userTypeService.findOne(user.getUserType().getId())==null)response.put("userType","unavailable user types not allowed");

        return response;
    }



}
