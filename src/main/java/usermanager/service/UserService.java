package usermanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usermanager.model.User;
import usermanager.model.UserType;
import usermanager.repository.UserRepository;
import usermanager.repository.UserTypeRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTypeRepository userTypeRepository;

    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;

    }

    public User save(User user) {
        user.setUserType(userTypeRepository.findOne(user.getUserType().getId()));
        userRepository.save(user);
        return user;
    }

    public User findOne(Integer id) {
        return userRepository.findOne(id);
    }

    public List<User> findAllByIsActive(Boolean isActive) {
        return userRepository.findAllByIsActive(isActive);
    }

    public boolean deleteOne(Integer id) {
        userRepository.delete(id);
        return true;
    }

    @Transactional
    public User updateOne(User user, UserType userType) {
        userRepository.save(user);
        if (userType != null)
            userRepository.updateUserType(userType.getId(), user.getId());
        return userRepository.findOne(user.getId());
    }

    public List<User> findAllByUserType(UserType userType){
        return userRepository.findAllByUserType(userType);
    }

}
