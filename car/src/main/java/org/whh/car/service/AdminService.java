package org.whh.car.service;

import org.whh.car.common.Result;
import org.whh.car.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author whh
 * @since 2023-12-02
 */
public interface AdminService extends IService<Admin> {


    Admin login(Admin admin);

}
