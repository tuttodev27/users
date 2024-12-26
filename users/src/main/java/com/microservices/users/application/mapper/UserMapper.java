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
        return new User(
                null,
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                null,
                null,
                null,
                null,
                true,
                mapPhonesToDomain(request.getPhones())
        );
    }
    public UserResponseDTO domainToUserResponse(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                mapPhonesToResponse(user.getPhones()),
                user.getCreated(),
                user.getModified(),
                user.getLastLogin(),
                user.getToken(),
                user.isActive()
        );
    }
    private List<Phone> mapPhonesToDomain(List<PhoneRequestDTO> phonesRequest) {
        if (phonesRequest == null) return Collections.emptyList();
        return phonesRequest.stream()
                .map(this::phoneRequestToDomain)
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
                null,
                phoneRequest.getNumber(),
                phoneRequest.getCityCode(),
                phoneRequest.getCountryCode(),
                null
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
