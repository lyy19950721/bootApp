package com.mipo.service;

import com.mipo.entity.User;
import com.mipo.pojo.dto.BaseDTO;

import java.util.List;

public interface UserService {

    List<User> listUser(BaseDTO baseDTO, boolean usePage);
}
