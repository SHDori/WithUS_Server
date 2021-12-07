package gachon.project.withus.service.iot;


import gachon.project.withus.controller.dto.IotLogResponseDTO;
import gachon.project.withus.controller.dto.IotLogSaveRequestDTO;
import gachon.project.withus.domain.Iot.Iot;
import gachon.project.withus.domain.Iot.IotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<IotLogResponseDTO> findDetailByTime(String date, String hour){
        return iotRepository.findDetailIotLog(date,hour).stream()
                .map(IotLogResponseDTO::new)
                .collect(Collectors.toList());

    }





}
