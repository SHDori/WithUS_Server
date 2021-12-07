package gachon.project.withus.domain.Iot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IotRepository extends JpaRepository<Iot, Long> {


    @Query("SELECT i FROM Iot i WHERE i.email = ?1 ORDER BY i.idx DESC")
    List<Iot> findListByEmail(String email);

    @Query("SELECT i FROM Iot i WHERE i.date = ?1 AND i.hour =?2")
    List<Iot> findDetailIotLog(String date,String hour);
}
