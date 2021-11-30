package gachon.project.withus.controller;


import gachon.project.withus.controller.dto.UserResponseDTO;
import gachon.project.withus.controller.dto.UserSaveRequestDTO;
import gachon.project.withus.controller.dto.UserUpdateRequestDTO;
import gachon.project.withus.domain.user.UserRepository;
import gachon.project.withus.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/*
* 유저 Data에 대한 기능 api를 정의한 곳입니다.
* 1. 유저정보 저장
* 2. 유저정보 조회(email로 찾기, id로 찾기)
* 3. 유저정보 수정
* 4. 유저정보 삭제
* 5. 유저 iot 서비스 신청
* 6. 유저 우울증 점수 상승,하락
* */

@RequiredArgsConstructor
@RestController
public class UserDataApiController {


    private final UserService userService;


    // 1. 유저정보 저장
    @PostMapping("/api/user/save")
    public Long save(@RequestBody UserSaveRequestDTO saveRequestDTO){
        return userService.save(saveRequestDTO);
    }

    // 2-1. 유저정보조회 (email로 찾기)
    @GetMapping("/api/user/{email}")
    public UserResponseDTO findByEmail(@PathVariable String email){
        UserResponseDTO userResponseDTO = userService.findByEmail(email);
        return userResponseDTO;
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
    public String registIot(@PathVariable String email){
        userService.registerIotService(email);
        return email;
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

}
