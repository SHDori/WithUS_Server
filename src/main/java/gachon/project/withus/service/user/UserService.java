package gachon.project.withus.service.user;


import gachon.project.withus.controller.dto.UserResponseDTO;
import gachon.project.withus.controller.dto.UserSaveRequestDTO;
import gachon.project.withus.controller.dto.UserUpdateRequestDTO;
import gachon.project.withus.domain.user.User;
import gachon.project.withus.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * 유저 Data에 대한 기능 서비스를 정의한 곳입니다.
 * 1. 유저정보 저장
 * 2. 유저정보 조회(email로 찾기, id로 찾기)
 * 3. 유저정보 수정
 * 4. 유저정보 삭제
 * 5. 유저 iot 서비스 신청,취소
 * 6. 유저 우울증 점수 상승,하락
 * 7. 유저정보를 리스트,페이지로 반환
 * */

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    //1. 유저정보 저장
    @Transactional
    public Long save(UserSaveRequestDTO saveRequestDTO){
        return userRepository.save(saveRequestDTO.toEntity()).getIdx();
    }

    //2-1.유저정보 email로 찾기
    public UserResponseDTO findByEmail(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다. email = "+email));
        return new UserResponseDTO(user);
    }

    //2-2. 유저정보 idx로 찾기
    public UserResponseDTO findByIdx(Long idx){
        User user = userRepository.findById(idx)
                .orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다. id = "+idx));
        return new UserResponseDTO(user);
    }

    // 3. 유저정보 수정
    @Transactional
    public String updateByEmail(String email, UserUpdateRequestDTO updateDTO){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. email = "+ email));

        user.updateInfo(updateDTO.getName(),updateDTO.getBirth(), updateDTO.getLat(),
                updateDTO.getLng(),updateDTO.getAddr(),updateDTO.getRegion1Depth(),updateDTO.getRegion2Depth());
        return email;
    }

    // 4. 유저정보 삭제
    @Transactional
    public void deleteUserByEmail(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. email = "+ email));
        user.delete();
    }


    // 5-1. 유저 iot 서비스 신청
    @Transactional
    public void registerIotService(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. email = "+ email));
        user.registerIotService();
    }

    // 5-2. 유저 iot 서비스 신청 취소
    @Transactional
    public void unregisterIotService(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. email = "+ email));
        user.unregisterIotService();
    }


    // 6. 유저 우울증 점수 상승,하락
    @Transactional
    public void plusDpScore(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. email = "+ email));
        user.plusDpScore();
    }

    @Transactional
    public void minusDpScore(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. email = "+ email));
        user.minusDpScore();
    }



}
