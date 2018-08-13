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

    public List<UserType> findAll() {
        return userTypeRepository.findAll();
    }

    public UserType findOne(Integer id) {
        return userTypeRepository.findOne(id);
    }

    public UserType save(UserType userType) {
        return userTypeRepository.save(userType);
    }

    public boolean deleteOne(Integer id) {
        userTypeRepository.delete(id);
        return true;
    }

    public List<UserType> getAllByName(String name) {
        return userTypeRepository.getAllByName(name);
    }

    public UserType update(UserType userType) {
        return userTypeRepository.save(userType);
    }

}
