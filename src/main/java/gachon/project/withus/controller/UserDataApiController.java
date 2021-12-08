package gachon.project.withus.controller;


import gachon.project.withus.controller.dto.UserListResponseDTO;
import gachon.project.withus.controller.dto.UserResponseDTO;
import gachon.project.withus.controller.dto.UserSaveRequestDTO;
import gachon.project.withus.controller.dto.UserUpdateRequestDTO;
import gachon.project.withus.domain.user.User;
import gachon.project.withus.domain.user.UserRepository;
import gachon.project.withus.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/*
* 유저 Data에 대한 기능 api를 정의한 곳입니다.
* 1. 유저정보 저장
* 2. 유저정보 조회(email로 찾기, id로 찾기)
* 3. 유저정보 수정
* 4. 유저정보 삭제
* 5. 유저 iot 서비스 신청
* 6. 유저 우울증 점수 상승,하락
* 7. iot 서비스유저 조회(in 관리자 페이지)
* */

@RequiredArgsConstructor
@RestController
public class UserDataApiController {


    private final UserService userService;
    private final UserRepository userRepository;


    // 1. 유저정보 저장
    @PostMapping("/api/user/save")
    public Long save(@RequestBody UserSaveRequestDTO saveRequestDTO){

        return userService.save(saveRequestDTO);

    }



//    @PostMapping("/api/user/save")
//    public ResponseEntity<String> save(@RequestBody UserSaveRequestDTO saveRequestDTO){
//        if(userRepository.findByUserEmailForCheck(saveRequestDTO.getEmail()).isEmpty()) {
//            if (saveRequestDTO.getName() == "" || saveRequestDTO.getBirth() == ""
//                    || saveRequestDTO.getEmail() == "" || saveRequestDTO.getSex() == "") {
//                return new ResponseEntity<>("check the name, email, birth", HttpStatus.BAD_REQUEST);
//            } else {
//                userService.save(saveRequestDTO);
//                return new ResponseEntity<>("save " + saveRequestDTO.getEmail() + "'s infomation successfully", HttpStatus.OK);
//            }
//        }
//        else{
//            return new ResponseEntity<>(saveRequestDTO.getEmail() + " welcome!", HttpStatus.OK);
//        }
//    }

    // 2-1. 유저정보조회 (email로 찾기)
    @GetMapping("/api/user/{email}")
    public ResponseEntity<String> findByEmail(@PathVariable String email){
        UserResponseDTO userResponseDTO = userService.findByEmail(email);
        if(userResponseDTO.getName()=="" || userResponseDTO.getEmail() == ""
                || userResponseDTO.getBirth() == "" || userResponseDTO.getSex() == ""){
            return new ResponseEntity<>("check the"+email+ "'s name, email, birth, sex",HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(email+"-> this user filled infomation about name, email, birth, sex",HttpStatus.OK);
        }

    }

    // 2-2. 유저정보조회 (idx로 찾기)
    @GetMapping("/api/user/fbi/{idx}")
    public UserResponseDTO findByIdx(@PathVariable Long idx){
        UserResponseDTO userResponseDTO = userService.findByIdx(idx);
        return userResponseDTO;
    }

    // 3. 유저정보 수정
    @PutMapping("/api/user/update/{email}")
    public String updateUserInfo(@PathVariable String email, @RequestBody UserUpdateRequestDTO updateDTO){

        return userService.updateByEmail(email,updateDTO);
    }

    // 4. 유저정보 삭제
    @PutMapping("/api/user/delete/{email}")
    public String updateUserInfo(@PathVariable String email){
        userService.deleteUserByEmail(email);
        return email;
    }

    // 5. 유저 iot 서비스 신청
    @PutMapping("/api/user/registIot/{email}")
    public ResponseEntity<String> registIot(@PathVariable String email){
        UserResponseDTO userResponseDTO = userService.findByEmail(email);
        if(userResponseDTO.getAddr()==""){
            return new ResponseEntity<>("iot서비스를 이용하기위해서 주소정보를 입력해주세요.",HttpStatus.BAD_REQUEST);
        }
        else{
            userService.registerIotService(email);
            return new ResponseEntity<>("서비스가 성공적으로 신청되었습니다.",HttpStatus.OK);
        }


    }
    // 5-2. 유저 iot 서비스 신청취소
    @PutMapping("/api/user/unregistIot/{email}")
    public ResponseEntity<String> unregistIot(@PathVariable String email){
        UserResponseDTO userResponseDTO = userService.findByEmail(email);
        if(!userResponseDTO.isIot()){
            return new ResponseEntity<>("아직 신청하지 않은 유저입니다",HttpStatus.OK);
        }
        else{
            userService.unregisterIotService(email);
            return new ResponseEntity<>("서비스가 성공적으로 취소되었습니다.",HttpStatus.OK);
        }


    }

    // 6. 유저 우울증 점수 상승,하락
    // 6-1. 유저 우울증 점수 상승
    @PutMapping("/api/user/plus/{email}")
    public String plusDpScore(@PathVariable String email){

        userService.plusDpScore(email);
        return email;
    }

    // 6-2. 유저 우울증 점수 하락
    @PutMapping("/api/user/minus/{email}")
    public String minusDpScore(@PathVariable String email){

        userService.minusDpScore(email);
        return email;
    }

    /* 7. 유저정보 조회 in 관리자 페이지
        7-1. iot신청유저 조회(list) (지도에 뿌리기 위함)
        7-2. iot신청유저 조회(page) 리스트로 쭈욱 보기위함
        7-3. 전체 유저조회(page)
    */

    // 7-1. iot신청유저 조회(list) (지도에 뿌리기 위함)
    @GetMapping("/api/admin/iotList")
    public List<UserListResponseDTO> findIotUserList(){
        return userRepository.findByIot().stream()
                .map(UserListResponseDTO::new)
                .collect(Collectors.toList());
    }
    // 7-2. iot신청유저 조회(page)
    @GetMapping("/api/admin/iotUser")
    public Page<UserListResponseDTO> iotUserPaging(@PageableDefault(size = 12, sort = "createdDate",direction = Sort.Direction.DESC) Pageable pageRequest){
        Page<User> iotUserPage = userRepository.findByIotPage(pageRequest);

        Page<UserListResponseDTO>  iotUserList = iotUserPage.map(
                user -> new UserListResponseDTO(user)
        );

        return iotUserList;
    }


    // 7-3. 전체 유저조회(page)
    @GetMapping("/api/admin/user")
    public Page<UserListResponseDTO> userPaging(@PageableDefault(size = 12, sort = "createdDate",direction = Sort.Direction.DESC) Pageable pageRequest){
        Page<User> userPage = userRepository.findAll(pageRequest);

        Page<UserListResponseDTO>  userPagingList = userPage.map(
                user -> new UserListResponseDTO(user)
        );

        return userPagingList;
    }
    // 8. iot 유저 이상감지
    @PutMapping("/api/admin/iot/onsiren/{email}")
    public String onSiren(@PathVariable String email){

        userService.onSiren(email);
        return email;
    }

    // 이상감지 off
    @PutMapping("/api/admin/iot/offsiren/{email}")
    public String offSiren(@PathVariable String email){

        userService.offSiren(email);
        return email;
    }

    // local test용 더미데이터

    /*
    @PostConstruct
    public void initializing(){

        for (int i = 0; i<20;i++){
            User user = User.builder()
                    .name("김승환 "+i)
                    .email("gachonboy0"+i+"@naver.com")
                    .birth("199"+i%10+"/12/7")
                    .lat("32.456")
                    .lng("27.456")
                    .addr("경기도 안양시 만안구 854-"+i)
                    .region1Depth("경기도")
                    .region2Depth("안양시")
                    .sex("남")
                    .build();
            userRepository.save(user);
        }
    }

     */





}
