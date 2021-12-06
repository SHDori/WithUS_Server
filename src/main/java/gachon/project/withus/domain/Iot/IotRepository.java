package gachon.project.withus.domain.Iot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IotRepository extends JpaRepository<Iot, Long> {


    @Query("SELECT i FROM Iot i WHERE i.email = ?1 ORDER BY i.idx DESC")
    List<Iot> findListByEmail(String email);

    @Query("SELECT i FROM Iot i WHERE i.email = ?1 AND i.date = ?2 AND i.hour =?3 AND i.location = ?4")
    Optional<Iot> findDetailIotLog(String email,String date,String hour, String location);
}
