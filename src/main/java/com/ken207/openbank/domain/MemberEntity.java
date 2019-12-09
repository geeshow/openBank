package com.ken207.openbank.domain;

import com.ken207.openbank.accounts.MemberRole;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Member_User")
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @NoArgsConstructor @AllArgsConstructor
public class MemberEntity {

    @Id @GeneratedValue
    public Integer id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<MemberRole> roles;

}

