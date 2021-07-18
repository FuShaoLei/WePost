package github.fushaolei.wpserver.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "wp_user")
public class User {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(unique = true, nullable = false)
    private String account;
    @Column(nullable = false)
    private String password;
    @Column
    private String name;
    @Column
    private String avator;

    /**
     * 一个user对应多个post
     */
    @OneToMany(targetEntity = Post.class)
    @JoinColumn(name = "uid ",referencedColumnName = "id")
    private List<Post> posts;

    /**
     * 一个user对应多个comment
     */
    @OneToMany(targetEntity = Post.class)
    @JoinColumn(name = "uid ",referencedColumnName = "id")
    private List<Comment> comments;

    public User() {
    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
