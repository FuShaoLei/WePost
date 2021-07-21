package github.fushaolei.wpserver.repo;

import github.fushaolei.wpserver.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    @Query("select new Post(p.id,p.content) from Post p where p.user.id = ?1")
    List<Post> findAllByUid(int id);

    @Query("select new Post(p.id,p.date,p.content,p.user.id,p.user.name,p.user.avator) from Post p")
    Page<Post> findPostByPage(Pageable pageable);

}
