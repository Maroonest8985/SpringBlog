package com.example.demo123.DTO;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class CommentDTO {

    private long no;
    private long member_no;
    private String text;
    private long post_no;
    private int reply;

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public long getMember_no() {
        return member_no;
    }

    public void setMember_no(long member_no) {
        this.member_no = member_no;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getPost_no() {
        return post_no;
    }

    public void setPost_no(long post_no) {
        this.post_no = post_no;
    }

    public int getReply() {
        return reply;
    }

    public void setReply(int reply) {
        this.reply = reply;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    private String date;
    private String member_name;
}
