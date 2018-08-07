package usermanager.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import usermanager.model.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {

   public List<User> findAllByIsActive(Boolean isActive);

    }

