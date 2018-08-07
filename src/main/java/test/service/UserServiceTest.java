package test.service;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import usermanager.model.User;
import usermanager.model.UserType;
import usermanager.repository.UserRepository;
import usermanager.service.UserService;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;


public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testList(){

        UserType firstUserType = new UserType();
        UserType secondUserType = new UserType();

        User firstUser = new User();
        User secondUser = new User();

        List<User> userList = new ArrayList();
        userList.add(firstUser);
        userList.add(secondUser);

        when(userRepository.findAll()).thenReturn(userList);

        List<User> userListByService  = userService.findAll();

        verify(userRepository).findAll();

        assertEquals(userList,userListByService);
    }


    @Test
    public void testGet(){

        UserType firstUserType = new UserType();
        User user = new User();

        when(userRepository.findOne(1)).thenReturn(user);

        User userReturnByService = userService.findOne(1);

        verify(userRepository).findOne(1);

        assertEquals(user,userReturnByService);
    }


}
