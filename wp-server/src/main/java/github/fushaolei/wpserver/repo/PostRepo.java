package github.fushaolei.wpserver.repo;

import github.fushaolei.wpserver.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    @Query("select p from Post p where p.user.id = ?1")
    List<Post> findAllByUid(int id);

}
