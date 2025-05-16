package com.pbl3.supermarket.mapper;

import com.pbl3.supermarket.dto.request.UserCreationRequest;
import com.pbl3.supermarket.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest userCreationRequest);
}
