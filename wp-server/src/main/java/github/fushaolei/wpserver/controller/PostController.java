package github.fushaolei.wpserver.controller;

import com.alibaba.fastjson.JSON;
import github.fushaolei.wpserver.entity.Post;
import github.fushaolei.wpserver.entity.Reply;
import github.fushaolei.wpserver.repo.PostRepo;
import github.fushaolei.wpserver.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostRepo postRepo;

    @PostMapping("/publish")
    public Reply publishPostText(@RequestBody String text, HttpServletRequest request) {
        int uid = JwtUtil.getId(request);
        System.out.println("uid = " + uid);
        text = JSON.parseObject(text).getString("text");
        Post post = postRepo.save(new Post(text, uid));
        if (post.getId() == 0) return Reply.error();
        return Reply.success();
    }

    @GetMapping("/getposts")
    public Reply getPosts(HttpServletRequest request) {
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
}
