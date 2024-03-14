package com.example.securityzerotoend.model.convertor;


import com.example.securityzerotoend.model.dto.LoginRequest;
import com.example.securityzerotoend.model.entity.UserEntity;
import com.example.securityzerotoend.model.response.UserSrv;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConvertor extends AbstractConvertor<UserEntity, LoginRequest, UserSrv> {
}
