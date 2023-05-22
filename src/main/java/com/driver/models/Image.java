package com.driver.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private  String description;

    private String dimension;


    // child for the blog
    @ManyToOne
    @JoinColumn
    Blog blog;

    public Image() {

    }

    public Image(String description, String dimension) {
        this.description = description;
        this.dimension = dimension;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getDimension() {
//        return dimension;
//    }
//
//    public void setDimension(String dimension) {
//        this.dimension = dimension;
//    }
//
//    public Blog getBlog() {
//        return blog;
//    }
//
//    public void setBlog(Blog blog) {
//        this.blog = blog;
//    }
}
