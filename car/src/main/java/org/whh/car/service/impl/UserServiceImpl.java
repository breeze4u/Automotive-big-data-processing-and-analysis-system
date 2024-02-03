package org.whh.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.whh.car.common.Params;
import org.whh.car.entity.User;
import org.whh.car.exception.CustomException;
import org.whh.car.mapper.UserMapper;
import org.whh.car.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author whh
 * @since 2023-11-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findListByParams(Params params) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (params != null && params.getInfor1() != null && !params.getInfor1().equals(""))
            wrapper.like("name", params.getInfor1());
        if (params != null && params.getInfor2() != null && !params.getInfor2().equals(""))
            wrapper.like("tel", params.getInfor2());
        return userMapper.selectList(wrapper);
    }

    @Override
    public String deletUserById(Integer id) {
        int i = userMapper.deleteById(id);
        if(i != 1){
            throw new CustomException("删除失败,请刷新后再次尝试");
        }return "删除成功";

    }

    public String addUser(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        QueryWrapper<User> name = wrapper.eq("name", user.getName());
        User selectOne = userMapper.selectOne(name);
        if (Objects.nonNull(selectOne)){
            throw new CustomException("用户名已存在");
        }
        userMapper.insert(user);
        return "添加成功";
    }

    public String updateUser(User user){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        QueryWrapper<User> id = wrapper.eq("id", user.getId());
        User user1 = userMapper.selectOne(id);
        if(Objects.isNull(id)){
            throw new CustomException("用户不存在，请刷新后再试");
        }
        if(!Objects.equals(user.getName(), user1.getName())){
            QueryWrapper<User> wrapper1 = new QueryWrapper<>();
            QueryWrapper<User> name = wrapper1.eq("name", user.getName());
            User user2 = userMapper.selectOne(name);
            if (Objects.nonNull(user2)){
                throw new CustomException("该用户名已经被注册，请换一个用户名");
            }
        }
        userMapper.update(user,id);
        return "更新成功";
    }

    @Override
    public String saveUser(User user) {
        if (Objects.isNull(user.getName()) || user.getName().equals("")) {
            throw new CustomException("用户名不能为空");
        }
        if(Objects.isNull(user.getPassword()) || user.getPassword().equals("")){
            throw new CustomException("密码不能为空");
        }
        if(Objects.isNull(user.getId())){
            return addUser(user);
        }else {
            return updateUser(user);
        }
    }

    @Override
    public String registerUser(User user) {
        if (Objects.isNull(user.getName()) || user.getName().equals("")) {
            throw new CustomException("用户名不能为空");
        }
        if(Objects.isNull(user.getPassword()) || user.getPassword().equals("")) {
            throw new CustomException("密码不能为空");
        }
        return addUser(user);
    }

    @Override
    public User login(User user) {
        if(Objects.isNull(user.getName()) || user.getName().equals("") ){
            throw new CustomException("用户名不能为空");
        }
        if(Objects.isNull(user.getPassword()) || user.getPassword().equals("")){
            throw new CustomException("密码不能为空");
        }
        else {
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("name",user.getName()).eq("password",user.getPassword());
            User user1 = userMapper.selectOne(userQueryWrapper);
            if (Objects.isNull(user1)){
                throw new CustomException("用户名或密码输入错误");
            }return user1;
        }
    }
}
