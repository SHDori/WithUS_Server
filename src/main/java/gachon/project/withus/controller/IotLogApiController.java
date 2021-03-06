package gachon.project.withus.controller;


import gachon.project.withus.controller.dto.IotLogResponseDTO;
import gachon.project.withus.controller.dto.IotLogSaveRequestDTO;
import gachon.project.withus.controller.dto.UserListResponseDTO;
import gachon.project.withus.domain.Iot.Iot;
import gachon.project.withus.domain.Iot.IotRepository;
import gachon.project.withus.service.iot.IotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class IotLogApiController {

    private final IotService iotService;
    private final IotRepository iotRepository;

    // 1. 기록 저장요청
    @PostMapping("/api/iot")
    public Long save(@RequestBody IotLogSaveRequestDTO saveRequestDTO){
        return iotService.save(saveRequestDTO);

    }

    // 2. 이메일별 iot 로그정보 조회
    @GetMapping("/api/iot/{email}")
    public List<IotLogResponseDTO> findLogListByEmail(@PathVariable String email){
        return iotRepository.findListByEmail(email).stream()
                .map(IotLogResponseDTO::new)
                .collect(Collectors.toList());
    }
    // 3. 특정 날짜 특정시간 기록조회
    @GetMapping("/api/iot/detail/{date}/{hour}")
    public List<IotLogResponseDTO> findDetailLog(@PathVariable String date, @PathVariable String hour){
        return iotService.findDetailByTime(date,hour);

    }


    // local test용 더미데이터
    /*
    @PostConstruct
    public void iotInitializing(){
        for(int i=0; i<24 ; i++) {
            Iot iot = Iot.builder()
                    .email("vw9801@naver.com").date("2021-12-04")
                    .hour(""+i).location("entrance").count(""+i)
                    .build();
            iotRepository.save(iot);
        }

        for(int i=0; i<24 ; i++) {
            Iot iot = Iot.builder()
                    .email("vw0527@naver.com").date("2021-12-04")
                    .hour(""+i).location("entrance").count(""+i)
                    .build();
            iotRepository.save(iot);
        }

        for(int i=0; i<24 ; i++) {
            Iot iot = Iot.builder()
                    .email("test@naver.com").date("2021-12-04")
                    .hour(""+i).location("entrance").count(""+i)
                    .build();
            iotRepository.save(iot);
        }
    }

     */






}
