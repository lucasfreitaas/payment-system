package com.lucasfreitaas.payment_system.service;

import com.lucasfreitaas.payment_system.entity.User;
import com.lucasfreitaas.payment_system.repository.UserRepository;
import com.lucasfreitaas.payment_system.util.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user){
        //Verificação de e-mail, o método fará a consulta do Email no banco de dados e caso o retorno seja diferente de null, significa que
        //o e-mail já existe no banco de dados, portanto não é permitido criar outro usuário com o mesmo e-mail. Caso o retorno seja null,
        //o novo usuário será criado.
        if (userRepository.findByEmail(user.getEmail()) != null){
            throw new RuntimeException("Este email já existe");
        } else {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            String randomCode = RandomString.generateRandomString(64);
            user.setVeririficationCode(randomCode);
            user.setEnabled(false);

            User savedUser = userRepository.save(user);

            return savedUser;
        }
    }
}
