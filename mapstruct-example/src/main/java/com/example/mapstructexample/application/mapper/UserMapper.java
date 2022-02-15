package com.example.mapstructexample.application.mapper;

import com.example.mapstructexample.application.controller.UserDTO;
import com.example.mapstructexample.application.domain.AddressDTO;
import com.example.mapstructexample.application.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserDTO userDTO);

    UserDTO toUserDTO(User user);

    @Mapping(target = "name" , ignore = true)
    UserDTO toUserDTO_v2(User user);

    @Mapping(target = "address" , source = "name")
    UserDTO toUserDTO_v3(User user);

    UserDTO toUserDTO_v4(User user , String address);

//    @Mapping(target = "address" , source = "addressDTO")
//    UserDTO toUserDTO_v5(User user , AddressDTO addressDTO);

    @Mapping(target = "address" , source = "addressDTO.address")
    UserDTO toUserDTO_v6(User user , AddressDTO addressDTO);
}
