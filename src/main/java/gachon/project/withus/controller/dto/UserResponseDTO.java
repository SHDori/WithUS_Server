package gachon.project.withus.controller.dto;


import gachon.project.withus.domain.user.User;
import lombok.Getter;

/*
* 유저정보 조회시 보이는것
* 개인정보인 생년월일 나이 사는위치는 보이지 않게 했습니다
* */

@Getter
public class UserResponseDTO {
    //실명
    private String name;

    // 이메일
    private String email;

    // iot서비스 신청여부
    private boolean iot;

    public UserResponseDTO(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.iot = user.isIot();
    }

}
