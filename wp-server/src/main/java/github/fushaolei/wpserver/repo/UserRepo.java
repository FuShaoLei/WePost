package github.fushaolei.wpserver.repo;

import github.fushaolei.wpserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface UserRepo extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.account = ?1 and u.password = ?2")
    User findUserByAccountAndPassword(String account, String password);
}
