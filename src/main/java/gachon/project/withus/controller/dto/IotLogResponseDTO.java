package gachon.project.withus.controller.dto;


import gachon.project.withus.domain.Iot.Iot;
import lombok.Getter;

@Getter
public class IotLogResponseDTO {

    private String email;

    private String date;

    private String hour;

    private String location;

    private String count;

   public IotLogResponseDTO(Iot iot){
       this.email = iot.getEmail();
       this.date = iot.getDate();
       this.hour = iot.getHour();
       this.location = iot.getLocation();
       this.count = iot.getCount();
   }

}
