package com.ken207.openbank.service;

import com.ken207.openbank.domain.MemberEntity;
import com.ken207.openbank.repository.MemberRepository;
import com.ken207.openbank.user.MemberAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Primary
@Service
public class MemberService implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MemberEntity member = memberRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new UsernameNotFoundException(email));
        return new MemberAdapter(member);
    }

    public MemberEntity createUser(MemberEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return memberRepository.save(user);
    }
}