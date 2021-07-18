package github.fushaolei.wpserver.controller;

import com.alibaba.fastjson.JSON;
import github.fushaolei.wpserver.entity.Post;
import github.fushaolei.wpserver.entity.Reply;
import github.fushaolei.wpserver.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostRepo postRepo;

    @PostMapping("/publish")
    public Reply publishPostText(@RequestBody String text,
                                 @RequestParam("uid") int uid) {

        text = JSON.parseObject(text).getString("text");
        Post post = postRepo.save(new Post(text, uid));
        if (post.getId() == 0) return Reply.error();
        return Reply.success();
    }

    @GetMapping("/getposts")
    public Reply getPosts() {

        List<Post> postList = postRepo.findAllByUid(Integer.parseInt("1"));
        if (postList.size()==0) return Reply.error();

        for (Post post : postList) {
            System.out.println(post);
        }

        Reply<List<Post>> reply = new Reply<>();
        reply.setSuccess();
        reply.setData(postList);
        return reply;
    }
}
