package gachon.project.withus.controller.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/*
* 유저정보 수정하는 DTO
* 이름, 생년월일, 사는곳에대한정보를 넣어서  수정할수 있습니다.
* */
@Getter
@NoArgsConstructor
public class UserUpdateRequestDTO {

    //실명
    private String name;

    // 생년월일 --> YYYY/MM/DD형식으로 넘겨줘서 split해서 년도로 나이계산
    private String birth;

    // 사는 장소에 대한 정보
    private String lat;
    private String lng;

    //장소 풀네임
    private String addr;

    // 00시
    private String region1Depth;
    // 00구
    private String region2Depth;

    @Builder
    public UserUpdateRequestDTO(String name,String birth,String lat,String lng,String addr,
                              String region1Depth,String region2Depth){
        this.name= name;
        this.birth = birth;
        this.lat = lat;
        this.lng = lng;
        this.addr = addr;
        this.region1Depth = region1Depth;
        this.region2Depth = region2Depth;

    }


}
