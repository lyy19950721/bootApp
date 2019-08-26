package com.mipo.service.imlp;

import com.mipo.dao.UserMapper;
import com.mipo.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：lyy
 * @date ：Created in 2019-08-20 17:53
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class BaseServiceImpl<T> implements BaseService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public T getOne(Integer id) {
        return null;
    }
}
