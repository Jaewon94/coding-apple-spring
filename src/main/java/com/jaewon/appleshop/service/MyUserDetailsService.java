package com.jaewon.appleshop.service;

import com.jaewon.appleshop.domain.Member;
import com.jaewon.appleshop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        DB에서 username을 가진 유저를 찾아와서
//        return new User(유저아이디, 비번, 권한) 해주세요
        Optional<Member> member = memberRepository.findByUsername(username);
        if(member.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        Member user = member.get();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        CustomUser custom = new CustomUser(user.getUsername(), user.getPassword(), grantedAuthorities);
        custom.setDisplayName(user.getDisplayName());
        return custom;


    }

    public class CustomUser extends User {

        private String displayName;
        public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, authorities);
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }
    }


}