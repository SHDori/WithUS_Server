package gachon.project.withus.controller.dto;


import gachon.project.withus.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDTO {

    private String name;

    private String email;

    private String birth;

    // 사는 장소에 대한 정보 프론트에서 어떻게 넘겨주냐에따라 변경가능성이 있음(-------------------------------------------------------
    private String lat;
    private String lng;

    //장소 풀네임
    private String addr;

    // 00시
    private String region1Depth;
    // 00구
    private String region2Depth;

    // ---------------------------------------------------------------)

    // 성별
    private String sex;

    @Builder
    public UserSaveRequestDTO(String name, String email, String birth,
                              String lat, String lng, String addr,
                              String region1Depth, String region2Depth,
                              String sex){

        this.name= name;
        this.email=email;
        this.birth = birth;

        // 위치 정보
        this.lat = lat;
        this.lng = lng;
        this.addr = addr;
        this.region1Depth = region1Depth;
        this.region2Depth = region2Depth;


        this.sex = sex;
    }

    // User객체를 프론트로부터 받아온 DTO값을 바탕으로 생성해서 반환
    public User toEntity(){
        return User.builder()
                .name(name).email(email).birth(birth)
                .lat(lat).lng(lng).addr(addr)
                .region1Depth(region1Depth).region2Depth(region2Depth)
                .sex(sex).build();
    }


}
