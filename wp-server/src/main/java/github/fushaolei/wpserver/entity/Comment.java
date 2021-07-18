package github.fushaolei.wpserver.entity;

import javafx.geometry.Pos;

import javax.persistence.*;

@Entity
@Table(name = "wp_comment")
public class Comment {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name = "pid",referencedColumnName = "id")
    private Post post;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "uid",referencedColumnName = "id")
    private User user;
    @Column
    private String date;
    @Column
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}