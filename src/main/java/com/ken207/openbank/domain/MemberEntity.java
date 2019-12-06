package com.ken207.openbank.domain;

import com.ken207.openbank.accounts.MemberRole;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Member")
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
@AttributeOverride(name = "id",column = @Column(name = "member_id"))
public class MemberEntity extends BaseEntity<MemberEntity> {

    private String email;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<MemberRole> roles;

}
