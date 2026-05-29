package library_fiek.services;

import library_fiek.dto.MemberDto;
import library_fiek.mappers.MemberMapper;
import library_fiek.models.Member;
import library_fiek.repositories.MemberRepository;

import java.util.List;

public class MemberService {
    private final MemberRepository memberRepository = new MemberRepository();
    private final MemberMapper memberMapper = new MemberMapper();

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public List<Member> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return findAll();
        }

        return memberRepository.search(keyword.trim());
    }

    public void create(MemberDto dto) {
        validate(dto);
        Member member = memberMapper.toModel(dto);
        memberRepository.insert(member);
    }

    public void update(int id, MemberDto dto) {
        validate(dto);
        Member member = memberMapper.toModel(id, dto);
        memberRepository.update(member);
    }

    public void delete(int id) {
        memberRepository.delete(id);
    }

    private void validate(MemberDto dto) {
        if (dto.getFullName() == null || dto.getFullName().trim().isEmpty()) {
            throw new IllegalArgumentException("Full name is required.");
        }

        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required.");
        }

        if (!dto.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email is not valid.");
        }

        if (dto.getPhone() == null || dto.getPhone().trim().isEmpty()) {
            throw new IllegalArgumentException("Phone is required.");
        }

        if (dto.getMemberType() == null || dto.getMemberType().trim().isEmpty()) {
            throw new IllegalArgumentException("Member type is required.");
        }
    }
}