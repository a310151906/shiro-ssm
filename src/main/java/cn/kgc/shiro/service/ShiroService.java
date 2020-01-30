package cn.kgc.shiro.service;

import cn.kgc.shiro.mapper.ShiroMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ShiroService {

    @Autowired
    private ShiroMapper shiroMapper;

    public void testMethod(){
        shiroMapper.select();
        Session session = SecurityUtils.getSubject().getSession();
        Object val = session.getAttribute("key");
        System.out.println("service sessionValue-->"+val);
        System.out.println("testMethod, time:"+new Date());
    }
}
