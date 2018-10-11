package com.dev.nguyenvantung.fg_app.data.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("active")
    @Expose
    private Integer active;
    @SerializedName("role")
    @Expose
    private UserRole role;
    @SerializedName("created_at")
    @Expose
    private UserCreatedAt createdAt;
    @SerializedName("updated_at")
    @Expose
    private UserUpdatedAt updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserCreatedAt getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(UserCreatedAt createdAt) {
        this.createdAt = createdAt;
    }

    public UserUpdatedAt getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(UserUpdatedAt updatedAt) {
        this.updatedAt = updatedAt;
    }
}
