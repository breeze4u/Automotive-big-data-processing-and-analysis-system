package org.whh.car.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.whh.car.common.Result;
import org.whh.car.entity.Admin;
import org.whh.car.service.AdminService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author whh
 * @since 2023-12-02
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @PostMapping("/login")
    public Result login(@RequestBody Admin admin) {
        Admin admin1 = adminService.login(admin);
        return Result.success(admin1);
    }


}

