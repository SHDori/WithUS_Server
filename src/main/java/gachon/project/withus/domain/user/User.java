package gachon.project.withus.domain.user;


import gachon.project.withus.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    //실명
    private String name;

    // 이메일
    private String email;

    // 생년월일 --> YYYY/MM/DD형식으로 넘겨줘서 split해서 년도로 나이계산
    private String birth;

    // 나이(백에서 계산)
    private int age;

    // 사는 장소에 대한 정보 (-------------------------------------------------------
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

    // iot서비스 신청여부
    private boolean iot;

    // depressed Score로 우울증점수
    private int dpScore;
    // 역할 (guest, admin)
    private String role;
    // 삭제여부
    private boolean deleteYn;

    private boolean siren;

    @Builder
    public User(String name,String email, String birth, String lat,String lng,String addr,
                String region1Depth,String region2Depth,String sex){
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

        String[] birtharr = birth.split(" ");
        LocalDate now = LocalDate.now();
        int nowYear = now.getYear();

        // 나이계산
        this.age= nowYear - Integer.parseInt(birtharr[3]);


//        String[] birtharr = birth.split("/");
//        LocalDate now = LocalDate.now();
//        int nowYear = now.getYear();
//
//        // 나이계산
//        this.age= nowYear - Integer.parseInt(birtharr[0]);
        System.out.println("day of Year: "+ nowYear);
        System.out.println(birtharr[3]);
        // 기본값 설정
        this.iot = false;
        this.dpScore = 50;
        // admin은 임의로 DB에서 바꿔주는거로
        this.role = "Guest";
        this.deleteYn = false;
        this.siren = false;

    }

    public void updateInfo(String name,String birth,String lat,String lng,String addr,
                       String region1Depth,String region2Depth,String sex){
        this.name = name;
        this.birth = birth;

        // 위치 정보
        this.lat = lat;
        this.lng = lng;
        this.addr = addr;
        this.region1Depth = region1Depth;
        this.region2Depth = region2Depth;

        this.sex = sex;

        String[] birtharr = birth.split(" ");
        LocalDate now = LocalDate.now();
        int nowYear = now.getYear();

        // 나이계산
        this.age= nowYear - Integer.parseInt(birtharr[3]);
        System.out.println("day of Year: "+ nowYear);
        System.out.println(birtharr[3]);


    }

    public void updateAddr(String lat,String lng,String addr,
                           String region1Depth,String region2Depth){
        // 위치 정보
        this.lat = lat;
        this.lng = lng;
        this.addr = addr;
        this.region1Depth = region1Depth;
        this.region2Depth = region2Depth;
    }

    // 유저정보 삭제
    public void delete(){
        this.deleteYn =true;
    }

    // iot 신청
    public void registerIotService(){
        this.iot =true;
    }
    // iot 신청취소
    public void unregisterIotService(){
        this.iot =false;
    }

    public void onSiren(){this.siren = true;}

    public void offSiren(){this.siren = false;}

    // 우울증 점수 상승
    public void plusDpScore(){
        if(this.dpScore<90){
            this.dpScore +=10;
        }
    }
    // 우울증 점수 하락
    public void minusDpScore(){
        if(this.dpScore>10){
            this.dpScore -=10;
        }
    }

    public void initDpScore(){
        this.dpScore = 50;
    }

}
