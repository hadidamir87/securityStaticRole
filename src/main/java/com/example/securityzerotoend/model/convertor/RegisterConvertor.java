package com.example.securityzerotoend.model.convertor;


import com.example.securityzerotoend.model.dto.RegisterRequest;
import com.example.securityzerotoend.model.entity.UserEntity;
import com.example.securityzerotoend.model.response.RegisterResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterConvertor extends AbstractConvertor<UserEntity, RegisterRequest, RegisterResponse> {

}
