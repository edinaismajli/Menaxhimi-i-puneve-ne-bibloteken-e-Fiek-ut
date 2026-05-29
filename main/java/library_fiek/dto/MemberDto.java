
package library_fiek.dto;

public class MemberDto {
    private String fullName;
    private String email;
    private String phone;
    private String memberType;


    public MemberDto(String fullName, String email, String phone, String memberType) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.memberType = memberType
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getMemberType() {
        return memberType;
    }
}