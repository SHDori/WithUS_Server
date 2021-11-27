package gachon.project.withus.domain.user;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    //실명
    private String name;

    // 이메일
    private String email;

    // 생년월일 --> YYYY/MM/DD형식으로 넘겨줘서 split해서 년도로 나이계산
    private String birth;

    // 나이(int로 넘겨줄지 String으로 넘겨줄지 백에서 계산할지에따라 달라짐)
    private int age;

    // 사는 장소에 대한 정보 프론트에서 어떻게 넘겨주냐에따라 변경가능성이 있음(-------------------------------------------------------
    private String locationX;
    private String locationY;

    private String addressName;

    // 00시
    private String region1Depth;
    // 00구
    private String region2Depth;

    // 장소이름
    private String placeName;
    // ---------------------------------------------------------------)

    // 성별
    private String sex;

    // iot서비스 신청여부
    private boolean iot;

    // depressed Score로 우울증점수
    private int dpScore;



    @Builder
    public User(String name,String email, String birth, int age, String locationX,String locationY,String addressName,
                String region1Depth,String region2Depth,String placeName,String sex){
        this.name= name;
        this.email=email;
        this.birth = birth;
        this.age= age;
        this.locationX = locationX;
        this.locationY = locationY;
        this.addressName = addressName;
        this.region1Depth = region1Depth;
        this.region2Depth = region2Depth;
        this.placeName = placeName;
        this.sex = sex;
        this.iot = false;

    }

}
