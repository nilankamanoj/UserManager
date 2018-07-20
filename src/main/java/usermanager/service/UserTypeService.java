package usermanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usermanager.model.UserType;
import usermanager.repository.UserTypeRepository;

import java.util.List;

@Service
public class UserTypeService {
    @Autowired
    private UserTypeRepository userTypeRepository;

    public List<UserType> findAll(){
        return userTypeRepository.findAll();
    }

    public UserType findOne(Integer id){
        return userTypeRepository.findOne(id);
    }

    public boolean save(UserType userType){
        userTypeRepository.save(userType);
        return true;
    }
}
