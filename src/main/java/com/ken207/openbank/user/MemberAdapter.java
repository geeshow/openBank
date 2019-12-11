package com.ken207.openbank.user;

import com.ken207.openbank.domain.MemberEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class MemberAdapter extends User {

    private MemberEntity memberEntity;

    public MemberAdapter(MemberEntity memberEntity) {
        super(memberEntity.getEmail(), memberEntity.getPassword(), authorities(memberEntity.getRoles()));
        this.memberEntity = memberEntity;
    }

    private static Collection<? extends GrantedAuthority> authorities(Set<MemberRole> roles) {
        return roles.stream()
                .map(userRole -> new SimpleGrantedAuthority("ROLE_" + userRole.name()))
                .collect(Collectors.toSet());
    }

    public MemberEntity getMemberEntity() {
        return memberEntity;
    }
}
