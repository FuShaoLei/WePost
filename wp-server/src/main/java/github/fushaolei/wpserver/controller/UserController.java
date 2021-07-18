package github.fushaolei.wpserver.controller;

import github.fushaolei.wpserver.entity.Reply;
import github.fushaolei.wpserver.entity.User;
import github.fushaolei.wpserver.repo.UserRepo;
import github.fushaolei.wpserver.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/login")
    public Reply login(@RequestParam("account") String account,
                       @RequestParam("password") String password) {
        User user = userRepo.findUserByAccountAndPassword(account, password);
        if (user == null || user.getId() == 0) return Reply.error();
        Reply<String> reply = new Reply<>();
        String token = JwtUtil.createToken(user.getId());
        reply.setSuccess(token);
        return reply;
    }

    @PostMapping("/register")
    public Reply register(@RequestParam("account") String account,
                          @RequestParam("password") String password) {
        User user = userRepo.save(new User(account, password));
        System.out.println("新插入的user = " + user);
        if (user.getId() == 0) return Reply.error();
        Reply<String> reply = new Reply<>();
        String token = JwtUtil.createToken(user.getId());
        reply.setSuccess(token);
        return reply;
    }
}
