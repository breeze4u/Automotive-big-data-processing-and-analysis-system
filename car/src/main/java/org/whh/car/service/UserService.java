package org.whh.car.service;

import org.whh.car.common.Params;
import org.whh.car.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author whh
 * @since 2023-11-30
 */
public interface UserService extends IService<User> {
    List<User> findListByParams(Params params);


    String deletUserById(Integer id);

    String saveUser(User user);

    String registerUser(User user);

    User login(User user);
}

