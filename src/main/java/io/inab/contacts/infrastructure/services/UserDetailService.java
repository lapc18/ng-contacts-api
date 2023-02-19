package io.inab.contacts.infrastructure.services;


import io.inab.contacts.domain.models.UserSecurity;
import io.inab.contacts.infrastructure.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserDetailService implements UserDetailsService {
    private final UsersRepository usersRepository;

    private ModelMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = usersRepository.findByEmail(email);
        if(user == null) throw new UsernameNotFoundException("User not found!");

        return new UserSecurity(user);
    }
}