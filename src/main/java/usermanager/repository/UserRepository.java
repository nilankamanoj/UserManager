package usermanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import usermanager.model.User;
import usermanager.model.UserType;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    public List<User> findAllByIsActive(Boolean isActive);

    public List<User> findAllByUserType(UserType userType);

    @Transactional
    @Modifying
    @Query("update User u set u.userType = (select ut from UserType ut where ut.id=:utid) where u.id=:uid")
    public void updateUserType(@Param("utid") int utid, @Param("uid") int uid);

}
