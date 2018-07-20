package usermanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import usermanager.model.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Integer> {
}
