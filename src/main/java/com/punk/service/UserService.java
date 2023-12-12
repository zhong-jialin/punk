package com.punk.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.punk.common.CaptureConfig;
import com.punk.common.Result;
import com.punk.dao.UserDao;
import com.punk.entity.Params;
import com.punk.entity.User;
import com.punk.exception.CustomException;
import com.punk.utils.JwtTokenUtils;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    public PageInfo<User> findBySearch(Params params) {
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<User> userList =  userDao.findBySearch(params);
        return PageInfo.of(userList);
    }

    public List<User> findAll() {
//        使用引用的包
        return userDao.selectAll();
    }


    public void adduser(User user) {
        if (user.getUsername() == null || "".equals( user.getUsername())){
            throw new CustomException("用户名不能为空");
        }
        User userr = userDao.findByName(user.getUsername());
        if (userr != null) {
            // 说明已经有了，这里我们就要提示前台不允许新增了
            throw new CustomException("该用户名已存在，请更换用户名");
        }
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        userDao.insertSelective(user);
    }

    public void updateuser(User user) {
//更新时间
        user.setUtime(LocalDateTime.now());
        userDao.updateByPrimaryKeySelective(user);
    }

    public void delete(Integer id) {
        userDao.deleteByPrimaryKey(id);
    }

    public User login(User user) {
        // 1. 进行一些非空判断
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new CustomException("用户名不能为空");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new CustomException("密码不能为空");
        }
        User loginUser = userDao.findByNameLogin(user.getUsername(),user.getPassword());
        if (loginUser == null) {
            // 如果查出来没有，那说明输入的用户名或者密码有误，提示用户，不允许登录
            throw new CustomException("用户名或密码输入错误");
        }

        String token = JwtTokenUtils.genToken(loginUser.getId().toString(), loginUser.getPassword());
        loginUser.setToken(token);

//        // 判断验证码对不对
//        if (!user.getVerCode().toLowerCase().equals(CaptureConfig.CAPTURE_MAP.get(key))) {
//            // 如果不相等，说明验证不通过
//            CaptchaUtil.clear(request);
//            return Result.error("验证码不正确");
//        }
        return loginUser;
    }

    public User findById(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }
}
