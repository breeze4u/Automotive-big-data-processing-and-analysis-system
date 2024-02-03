package org.whh.car.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.whh.car.common.Result;
import org.whh.car.common.Params;
import org.whh.car.entity.User;
import org.whh.car.service.UserService;
import java.util.List;



/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author whh
 * @since 2023-11-30
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Result list(){
        List<User> list = userService.list();
        return Result.success(list);
    }

    @GetMapping("/getListByParams")
    public Result findByParams(Params params) {
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<User> users = userService.findListByParams(params);
        return Result.success(PageInfo.of(users));
    }

    @PostMapping("/saveUser")
    public Result save(@RequestBody User user){
        String s = userService.saveUser(user);
        return Result.success(s);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        String s = userService.deletUserById(id);
        return Result.success(s);
    }

    @PostMapping("/register")
    public Result Register(@RequestBody User user){
        String s = userService.registerUser(user);
        return Result.success(s);
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        User user1 = userService.login(user);
        return Result.success(user1);
    }




}
