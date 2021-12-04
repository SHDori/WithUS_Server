package gachon.project.withus.domain.Iot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IotRepository extends JpaRepository<Iot, Long> {


    @Query("SELECT i FROM Iot i WHERE i.email = ?1 ORDER BY i.idx DESC")
    List<Iot> findListByEmail(String email);

}
