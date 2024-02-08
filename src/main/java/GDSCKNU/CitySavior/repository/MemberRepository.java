package GDSCKNU.CitySavior.repository;

import GDSCKNU.CitySavior.entity.member.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Boolean existsByEmail(String email);


}
