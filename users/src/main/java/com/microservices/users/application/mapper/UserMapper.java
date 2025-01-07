package com.microservices.users.application.mapper;

import com.microservices.users.application.dto.PhoneRequestDTO;
import com.microservices.users.application.dto.PhoneResponseDTO;
import com.microservices.users.application.dto.UserRequestDTO;
import com.microservices.users.application.dto.UserResponseDTO;
import com.microservices.users.domain.models.Phone;
import com.microservices.users.domain.models.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class UserMapper {


    public User userToDomain(UserRequestDTO request) {
        User user = new User(
                null,
                null,
                null,
                null,
                request.getEmail(),
                request.getPassword(),
                null,
                true,
                null
        );

        // Asocia los teléfonos al usuario
        List<Phone> phones = mapPhonesToDomain(request.getPhones(), user);
        user.setPhones(phones);

        return user;
    }

    public UserResponseDTO domainToUserResponse(User user) {
        return new UserResponseDTO(
                user.getId(),
                null,
                user.getEmail(),
                mapPhonesToResponse(user.getPhones()),
                user.getCreated(),
                user.getModified(),
                user.getLastLogin(),
                user.getToken(),
                user.isActive()
        );
    }

    private List<Phone> mapPhonesToDomain(List<PhoneRequestDTO> phonesRequest, User user) {
        if (phonesRequest == null || phonesRequest.isEmpty()) {
            return Collections.emptyList();
        }
        return phonesRequest.stream()
                .map(phoneDTO -> {
                    Phone phone = new Phone(
                            null,
                            phoneDTO.getNumber(),
                            phoneDTO.getCityCode(),
                            phoneDTO.getCountryCode(),
                            user
                    );
                    return phone;
                })
                .collect(Collectors.toList());
    }


    private List<PhoneResponseDTO> mapPhonesToResponse(List<Phone> phones) {
        if (phones == null) return Collections.emptyList();
        return phones.stream()
                .map(this::phoneDomainToResponse)
                .collect(Collectors.toList());
    }


    private Phone phoneRequestToDomain(PhoneRequestDTO phoneRequest) {
        return new Phone(
                null, // El ID será generado automáticamente.
                phoneRequest.getNumber(),
                phoneRequest.getCityCode(),
                phoneRequest.getCountryCode(),
                null // La relación con User será asignada al persistir.
        );
    }


    private PhoneResponseDTO phoneDomainToResponse(Phone phone) {
        return new PhoneResponseDTO(
                phone.getNumber(),
                phone.getCityCode(),
                phone.getCountryCode()
        );
    }
}