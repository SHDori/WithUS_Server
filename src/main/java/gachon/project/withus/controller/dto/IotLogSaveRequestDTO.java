package gachon.project.withus.controller.dto;


import gachon.project.withus.domain.Iot.Iot;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class IotLogSaveRequestDTO {

    private String email;

    private String date;

    private String hour;

    private String location;

    private String count;

    @Builder
    public IotLogSaveRequestDTO(String email, String date,String hour, String location, String count){

        this.email = email;
        this.date = date;
        this.hour = hour;
        this.location = location;
        this.count = count;
    }

    public Iot toEntity(){
        return Iot.builder()
                .email(email).date(date).hour(hour)
                .location(location).count(count)
                .build();
    }


}
