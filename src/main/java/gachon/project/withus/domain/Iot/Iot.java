package gachon.project.withus.domain.Iot;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Iot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String email;

    private String date;

    private String hour;

    private String location;

    private String count;

    @Builder
    public Iot(String email, String date, String hour, String location, String count){
        this.email = email;
        this.date = date;
        this.hour = hour;
        this.location = location;
        this.count = count;
    }


}
