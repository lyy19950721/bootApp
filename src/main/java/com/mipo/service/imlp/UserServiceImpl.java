package com.mipo.service.imlp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mipo.dao.UserMapper;
import com.mipo.entity.User;
import com.mipo.pojo.dto.BaseDTO;
import com.mipo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：lyy
 * @date ：Created in 2019-08-20 17:50
 * @description：
 * @modified By：
 * @version: $
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public List<User> listUser(BaseDTO baseDTO, boolean usePage) {
        if(usePage) {
            PageHelper.startPage(baseDTO.getPageNum(), baseDTO.getPageSize());
        }

        List<User> users = userMapper.listUser();

        if(usePage) {
            PageInfo<User> pageInfo = new PageInfo<>(users);
            return pageInfo.getList();
        }
        return users;
    }
}
