package com.upiiz.superheroes.entities;

import org.springframework.hateoas.Link;

import java.util.List;

public class CustomResponse<T> {
    private int status;
    private String message;
    private T data;
    private List<Link> links;

    public CustomResponse(int status, String message, T data, List<Link> links) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.links = links;
    }

    // Getters y Setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
