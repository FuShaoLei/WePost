package github.fushaolei.wpserver.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "wp_post")
public class Post {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "uid", referencedColumnName = "id")
    private User user;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Lob
    private String content;
    @Lob
    private String picture;

    @OneToMany(targetEntity = Comment.class)
    @JoinColumn(name = "pid ", referencedColumnName = "id")
    private List<Comment> comments;

    public Post() {
    }

    public Post(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public Post(String content, int uid) {
        User user = new User();
        user.setId(uid);
        this.content = content;
        this.user = user;
    }

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

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
