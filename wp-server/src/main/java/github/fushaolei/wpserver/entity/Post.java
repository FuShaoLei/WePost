package github.fushaolei.wpserver.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "wp_post")
public class Post {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "uid",referencedColumnName = "id")
    private User user;
    @Column
    private String date;
    @Column
    private String content;
    @Column
    private String picture;

    @OneToMany(targetEntity = Comment.class)
    @JoinColumn(name = "pid ",referencedColumnName = "id")
    private List<Comment> comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
