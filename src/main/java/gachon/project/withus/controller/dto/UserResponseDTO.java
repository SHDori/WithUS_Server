package gachon.project.withus.controller.dto;


import gachon.project.withus.domain.user.User;
import lombok.Getter;

/*
* 유저정보 조회시 보이는것(-> 유저의 필수값이 들어가있는지 검증)
*
* */

@Getter
public class UserResponseDTO {
    //실명
    private String name;

    // 이메일
    private String email;

    private String birth;

    private String sex;

    // iot서비스 신청여부
    private boolean iot;

    private boolean siren;

    private String role;

    private String addr;

    private int dpScore;


    public UserResponseDTO(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.birth = user.getBirth();
        this.sex = user.getSex();
        this.iot = user.isIot();
        this.siren = user.isSiren();
        this.addr = user.getAddr();
        this.role = user.getRole();
        this.dpScore = user.getDpScore();
    }

}
