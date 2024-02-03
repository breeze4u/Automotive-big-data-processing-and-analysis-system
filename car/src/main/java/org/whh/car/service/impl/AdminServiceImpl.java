package org.whh.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.whh.car.common.Result;
import org.whh.car.entity.Admin;
import org.whh.car.exception.CustomException;
import org.whh.car.mapper.AdminMapper;
import org.whh.car.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author whh
 * @since 2023-12-02
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {


    @Autowired
    public AdminMapper adminMapper;


    @Override
    public Admin login(Admin admin) {
        if(Objects.isNull(admin.getName()) || admin.getName().equals("") ){
            throw new CustomException("用户名不能为空");
        }
        if(Objects.isNull(admin.getPassword()) || admin.getPassword().equals("")){
            throw new CustomException("密码不能为空");
        }
        else {
            QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
            adminQueryWrapper.eq("name",admin.getName()).eq("password",admin.getPassword());
            Admin admin1 = adminMapper.selectOne(adminQueryWrapper);
            if (Objects.isNull(admin1)){
                throw new CustomException("用户名或密码输入错误");
            }return admin1;
        }
    }
}
