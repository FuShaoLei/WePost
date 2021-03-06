package github.fushaolei.wpserver.controller;

import com.alibaba.fastjson.JSON;
import github.fushaolei.wpserver.entity.Post;
import github.fushaolei.wpserver.entity.Reply;
import github.fushaolei.wpserver.repo.PostRepo;
import github.fushaolei.wpserver.utils.DateUtil;
import github.fushaolei.wpserver.utils.JwtUtil;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepo postRepo;

    /**
     * 发表文章
     */
    @PostMapping("/create")
    public Reply publishPostText(@RequestBody String text, HttpServletRequest request) {
        int uid = JwtUtil.getId(request);
        text = text.replace("\"", "");
        System.out.println("text = " + text);
        System.out.println("uid = " + uid);
        Post post = postRepo.save(new Post(text, new Date(), uid));
        if (post.getId() == 0) return Reply.error();
        return Reply.success();
    }

    /**
     * 获取某个用户的blog
     */
    @GetMapping("/getUserPosts")
    public Reply getUserPosts(HttpServletRequest request) {
        int id = JwtUtil.getId(request);
        List<Post> postList = postRepo.findAllByUid(id);
        if (postList.size() == 0) return Reply.error();

        for (Post post : postList) {
            System.out.println(post);
        }

        Reply<List<Post>> reply = new Reply<>();
        reply.setSuccess();
        reply.setData(postList);
        return reply;
    }

    /**
     * 分页获取博客
     */
    @GetMapping("/{page}")
    public Reply getPost(@PathVariable("page") int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.Direction.ASC, "id");
        Page<Post> postPage = postRepo.findPostByPage(pageable);
        System.out.println("总条数是：" + postPage.getTotalElements() + "，总页数是：" + postPage.getTotalPages());
        List<Post> postList = postPage.getContent();

        for (Post post : postList) {
            System.out.println(post);
        }

        Reply<List<Post>> reply = new Reply<>();
        reply.setSuccess(postList);
        return reply;
    }

}
