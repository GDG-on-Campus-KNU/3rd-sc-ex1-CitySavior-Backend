package GDSCKNU.CitySavior.repository.member;

import GDSCKNU.CitySavior.entity.member.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query("SELECT m.isAdmin " +
            "FROM Member m " +
            "WHERE m.email = :email")
    Boolean findIsAdminByEmail(@Param("email") String email);
}
