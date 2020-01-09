package com.ken207.openbank.domain;

import com.ken207.openbank.user.MemberRole;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Member")
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @NoArgsConstructor @AllArgsConstructor
public class Member {

    @Id @GeneratedValue
    public Integer id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<MemberRole> roles;

}

