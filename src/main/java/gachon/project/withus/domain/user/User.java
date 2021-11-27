package gachon.project.withus.domain.user;


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

    private String email;

    // 생년월일 --> YYYY/MM/DD형식으로 넘겨줘서 split해서 년도로 나이계산
    private String birth;

    // 나이
    private int age;

    // 사는 장소에 대한 정보 프론트에서 어떻게 넘겨주냐에따라 변경가능(-------------------------------------------------------
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

}
