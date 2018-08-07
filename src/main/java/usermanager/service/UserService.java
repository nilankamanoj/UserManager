package usermanager.service;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usermanager.model.User;
import usermanager.repository.UserRepository;
import usermanager.repository.UserTypeRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTypeRepository userTypeRepository;

//    private DozerBeanMapper dozerBeanMapper = new  DozerBeanMapper();

    public List<User> findAll(){
        List<User> users = userRepository.findAll();
        return users;

    }

    public User save(User user){
        user.setUserType(userTypeRepository.findOne(user.getUserType().getId()));
        userRepository.save(user);
        return user;
    }


    public User findOne(Integer id){
        return userRepository.findOne(id);
    }

    public List<User> findAllByIsActive(Boolean isActive){
        return  userRepository.findAllByIsActive(isActive);
    }

    public boolean deleteOne(Integer id){
        userRepository.delete(id);
        return true;
    }

    public User updateOne(User user){
//        dozerBeanMapper.setCustomFieldMapper((source, destination, sourceFieldValue, classMap, fieldMapping) -> sourceFieldValue == null);
//        dozerBeanMapper.map(user,oldUser);
        return userRepository.saveAndFlush(user);
    }


}
