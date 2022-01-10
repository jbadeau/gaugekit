package com.github.jbadeaul.todo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Todo {

    public Todo() {
    }

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String text;

    @Column
    private boolean completed;

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return completed;

    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


}
