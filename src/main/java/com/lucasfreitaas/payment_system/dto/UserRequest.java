package com.lucasfreitaas.payment_system.dto;

import com.lucasfreitaas.payment_system.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "O nome não pode ser vazio.")
        String name,
        @NotBlank(message = "O email não pode ser vazio.")
        @Email
        String email,
        @NotBlank(message = "A senha não pode ser vazia")
        @Size(message = "A senha deve conter no mínimo 6 caracteres.")
        String password) {
    public User toModel(){
        return new User(name, email, password);
    }
}
