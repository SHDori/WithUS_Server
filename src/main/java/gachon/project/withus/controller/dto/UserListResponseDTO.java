package gachon.project.withus.controller.dto;

import gachon.project.withus.domain.user.User;
import lombok.Getter;

@Getter
public class UserListResponseDTO {

    private String name;

    private String email;

    private String lat;

    private String lng;

    private String addr;

    private int age;

    private String sex;

    private int dpScore;

    private boolean siren;

    public UserListResponseDTO(User user){

        this.name = user.getName();
        this.email = user.getEmail();
        this.lat = user.getLat();
        this.lng = user.getLng();
        this.addr = user.getAddr();
        this.age = user.getAge();
        this.sex = user.getSex();
        this.dpScore = user.getDpScore();
        this.siren = user.isSiren();
    }


}
