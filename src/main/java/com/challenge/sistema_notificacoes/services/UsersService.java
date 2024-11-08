package com.challenge.sistema_notificacoes.services;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.challenge.sistema_notificacoes.entities.Users;
import com.challenge.sistema_notificacoes.repository.UsersRepository;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Optional<Users> findById(Long userId){
        return usersRepository.findById(userId);
    }

}
