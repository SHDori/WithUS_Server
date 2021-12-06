package gachon.project.withus.service.iot;


import gachon.project.withus.controller.dto.IotLogResponseDTO;
import gachon.project.withus.controller.dto.IotLogSaveRequestDTO;
import gachon.project.withus.domain.Iot.Iot;
import gachon.project.withus.domain.Iot.IotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class IotService {

    private final IotRepository iotRepository;

    // 1. iot정보 저장
    @Transactional
    public Long save(IotLogSaveRequestDTO saveRequestDTO){
        System.out.println(saveRequestDTO.getEmail());
        return iotRepository.save(saveRequestDTO.toEntity()).getIdx();
    }

    public IotLogResponseDTO findDetail(String email,String date,String hour,String location){
        Iot iot = iotRepository.findDetailIotLog(email,date,hour,location)
                .orElseThrow(()-> new IllegalArgumentException("해당 기록이 없습니다. email = "+email+" date = "+date+" hour = "+hour+" location = "+location));
        return new IotLogResponseDTO(iot);
    }





}
