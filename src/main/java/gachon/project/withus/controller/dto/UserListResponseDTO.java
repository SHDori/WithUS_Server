package gachon.project.withus.controller.dto;

import gachon.project.withus.domain.user.User;
import lombok.Getter;

@Getter
public class UserListResponseDTO {

    private String name;

    private String email;

    private String addr;

    private int age;

    private int dpScore;

    public UserListResponseDTO(User user){

        this.name = user.getName();
        this.email = user.getEmail();
        this.addr = user.getAddr();
        this.age = user.getAge();
        this.dpScore = user.getDpScore();
    }


}
