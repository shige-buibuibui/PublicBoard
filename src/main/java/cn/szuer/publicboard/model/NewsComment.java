package cn.szuer.publicboard.model;

import java.util.Date;

public class NewsComment {
    private Integer commentid;

    private Integer newsid;

    private Integer userid;

    private Date commenttime;

    private Integer likenum;

    private Boolean anonymousstate;

    private String content;

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    public Integer getNewsid() {
        return newsid;
    }

    public void setNewsid(Integer newsid) {
        this.newsid = newsid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(Date commenttime) {
        this.commenttime = commenttime;
    }

    public Integer getLikenum() {
        return likenum;
    }

    public void setLikenum(Integer likenum) {
        this.likenum = likenum;
    }

    public Boolean getAnonymousstate() {
        return anonymousstate;
    }

    public void setAnonymousstate(Boolean anonymousstate) {
        this.anonymousstate = anonymousstate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}