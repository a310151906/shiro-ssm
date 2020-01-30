package cn.kgc.shiro.mapper;

import org.springframework.stereotype.Repository;

@Repository
public class ShiroMapperImpl implements ShiroMapper{

    @Override
    public void select() {
        System.out.println("从数据库查。。。");
    }
}
