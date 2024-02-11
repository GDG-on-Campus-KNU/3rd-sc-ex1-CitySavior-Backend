package GDSCKNU.CitySavior.entity.member;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {

    USER(false),
    ADMIN(true);

    private final Boolean isAdmin;

    public static MemberRole getByIsAdmin(Boolean isAdmin) {

        return Arrays.stream(MemberRole.values())
                .filter(role -> role.isAdmin.equals(isAdmin))
                .findFirst()
                .orElseThrow();
    }
}
