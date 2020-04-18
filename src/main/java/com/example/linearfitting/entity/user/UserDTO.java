package com.example.linearfitting.entity.user;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * @author
 */
@Data
public class UserDTO {
    private Integer id;

    private String name;

    private String phone;

    private String email;

    private String password;

    private Date createTime;

    private Date updateTime;

    public static boolean isValid(UserDTO user) {
        return Objects.nonNull(user) && Objects.nonNull(user.name) && Objects.nonNull(user.password);
    }

    public static boolean isEmail(UserDTO user) {
        String expr = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\." +
                "[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})$";

        return user.getEmail().matches(expr);


    }

    public static boolean isPhone(UserDTO user) {
        String expr = "^(13[0-9]|15[0-9]|153|15[6-9]|180|18[23]|18[5-9])\\d{8}$";
        return user.getPhone().matches(expr);
    }


}
