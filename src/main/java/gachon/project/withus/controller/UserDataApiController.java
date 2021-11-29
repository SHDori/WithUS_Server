package gachon.project.withus.controller;


import gachon.project.withus.controller.dto.UserResponseDTO;
import gachon.project.withus.controller.dto.UserSaveRequestDTO;
import gachon.project.withus.domain.user.UserRepository;
import gachon.project.withus.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserDataApiController {


    private final UserService userService;


    @PostMapping("/api/user/save")
    public Long save(@RequestBody UserSaveRequestDTO saveRequestDTO){
        return userService.save(saveRequestDTO);
    }

    @GetMapping("/api/user/{email}")
    public UserResponseDTO findByEmail(@PathVariable String email){
        UserResponseDTO userResponseDTO = userService.findByEmail(email);
        return userResponseDTO;

    }
}
