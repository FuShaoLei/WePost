package github.fushaolei.wpserver.entity;

import javafx.geometry.Pos;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "wp_comment")
public class Comment {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int id;
    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name = "pid",referencedColumnName = "id")
    private Post post;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "uid",referencedColumnName = "id")
    private User user;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
