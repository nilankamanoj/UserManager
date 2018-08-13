package usermanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import usermanager.model.UserType;
import java.util.List;

public interface UserTypeRepository extends JpaRepository<UserType, Integer> {
    public List<UserType> getAllByName(String name);
}
