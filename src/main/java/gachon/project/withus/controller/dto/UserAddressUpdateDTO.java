package gachon.project.withus.controller.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserAddressUpdateDTO {

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
    public UserAddressUpdateDTO(String lat,String lng,String addr,
                                String region1Depth,String region2Depth){
        this.lat = lat;
        this.lng = lng;
        this.addr = addr;
        this.region1Depth = region1Depth;
        this.region2Depth = region2Depth;
    }


}
