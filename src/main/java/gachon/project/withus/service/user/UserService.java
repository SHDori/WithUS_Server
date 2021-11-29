package gachon.project.withus.service.user;


import gachon.project.withus.controller.dto.UserResponseDTO;
import gachon.project.withus.controller.dto.UserSaveRequestDTO;
import gachon.project.withus.domain.user.User;
import gachon.project.withus.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    // 유저정보 저장
    @Transactional
    public Long save(UserSaveRequestDTO saveRequestDTO){
        return userRepository.save(saveRequestDTO.toEntity()).getIdx();
    }

    //유저정보 email로 찾기
    public UserResponseDTO findByEmail(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다. email = "+email));
        return new UserResponseDTO(user);
    }

}
