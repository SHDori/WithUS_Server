package gachon.project.withus.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 전체유저 Page로 반환
    @Override
    @Query("SELECT u FROM User u  where u.deleteYn = false")
    Page<User> findAll(Pageable pageable);

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u where u.idx = ?1")
    Optional<User> findById(Long idx);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByUserEmail(String email);

    // iot 서비스 신청유저들 list로 뿌려줌 (지도에는 전체유저를 뿌려야하니까 list로)
    @Query("SELECT u FROM User u where u.iot = true AND u.deleteYn = false")
    List<User> findByIot();

    // iot 서비스 신청유저들 list(Page)로 뿌려줌
    @Query("SELECT u FROM User u where u.iot = true AND u.deleteYn = false")
    Page<User> findByIotPage(Pageable pageable);



}
