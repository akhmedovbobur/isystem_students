package com.example.isystem_students.application;


import com.example.isystem_students.exception.ServerBadRequestException;
import com.example.isystem_students.model.User;
import com.example.isystem_students.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

//Management (foydalanuchilar boshqaruvi) uchun javob beradigan class
@Component
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = usersRepository.findByEmailAndDeletedAtIsNull(username);
        if (optional.isEmpty()) {
            throw new ServerBadRequestException("User not found");
        }
        User users = optional.get();
        return new CustomUserDetails(users);
    }
}
