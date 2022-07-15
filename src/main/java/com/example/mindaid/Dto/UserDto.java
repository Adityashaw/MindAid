package com.example.mindaid.Dto;

import com.example.mindaid.Model.User;

import javax.persistence.Column;

public class UserDto extends User {
    public int userId;
    public String name;
    public String phone;
    public String email;
    public UserDto userDto;
    public UserDto [] userDtoArray=new UserDto[1];

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public UserDto[] getUserDtoArray() {
        return userDtoArray;
    }

    public void setUserDtoArray(UserDto[] userDtoArray) {
        this.userDtoArray = userDtoArray;
    }
}
