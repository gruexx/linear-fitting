package com.example.linearfitting.controller.login;

import com.example.linearfitting.entity.base.Result;
import com.example.linearfitting.entity.user.DataVO;
import com.example.linearfitting.entity.user.UserDTO;
import com.example.linearfitting.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author
 */
@Slf4j
@RestController
@Api(tags = "用户信息")
@RequestMapping("/demo/user")
public class LoginController {

    @Resource
    private UserService userService;

    @ApiOperation("注册")
    @PostMapping("/register")
    public Result<?> register(@RequestBody UserDTO user) {
        if (Objects.isNull(user) || !UserDTO.isValid(user)) {
            return Result.error("用户信息输入错误");
        }

        if (Objects.nonNull(user.getEmail()) && !UserDTO.isEmail(user)) {
            return Result.error("邮箱输入不正确");
        }

        if (Objects.nonNull(user.getPhone()) && !UserDTO.isPhone(user)) {
            return Result.error("手机号格式不正确");
        }

        return Result.success(userService.register(user));
    }

    @ApiOperation("登录")
    @GetMapping("/login")
    public Result<?> login(UserDTO user) {
        if (Objects.isNull(user)) {
            return Result.error("用户信息输入错误");
        }

        if (Objects.isNull(user.getPassword())) {
            return Result.error("请输入密码");
        }

        return Result.success(userService.login(user));
    }

    @ApiOperation("获取保存数据")
    @GetMapping("/data")
    public Result<?> data(Integer id) {
        return Result.success(userService.data(id));
    }

    @ApiOperation("获取保存数据")
    @PostMapping("/save")
    public Result<?> save(@RequestBody DataVO data) {
        return Result.success(userService.save(data));
    }

}
