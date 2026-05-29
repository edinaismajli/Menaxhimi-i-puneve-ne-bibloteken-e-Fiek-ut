
package library_fiek.mappers;

import library_fiek.dto.MemberDto;
import library_fiek.models.Member;

public class MemberMapper {
    public Member toModel(MemberDto dto) {
        return new Member(
                0,
                dto.getFullName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getMemberType()
        );
    }

    public Member toModel(int id, MemberDto dto) {
        return new Member(
                id,
                dto.getFullName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getMemberType()
        );
    }
}
