package library_fiek.models;

public class Member {
    private int id;
    private String fullName;
    private String email;
    private String phone;
    private String memberType;

    public Member(int id, String fullName, String email, String phone, String memberType) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.memberType = memberType;
    }

    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getMemberType() { return memberType; }

    public void setId(int id) { this.id = id; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setMemberType(String memberType) { this.memberType = memberType; }
    }

main/java/library_fiek/dto/MemberDto.java

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
    this.memberType = memberType;
    }


}






