package org.tmkim;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "MEMBER")
public class Member
{
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String username;

    //매핑 정보가 없는 필드
    private Integer age;

    //추가
    @Enumerated(EnumType.STRING)
    private RoleType roleType; //구분

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate; //생성일

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate; //수정일

    @Lob
    private String description; //회원 설명
}

