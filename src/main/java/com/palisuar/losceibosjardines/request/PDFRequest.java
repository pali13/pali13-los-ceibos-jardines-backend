package com.palisuar.losceibosjardines.request;

import java.util.List;

public class PDFRequest {
    
    private List<Task> tasks;
    private String title;

    // Getters y setters

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}