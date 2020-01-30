package cn.kgc.shiro.factory;


import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

    public LinkedHashMap<String,String> buildFilterChainDefinitionMap(){
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        /*
        /login.jsp = anon
        /shiro/login.do = anon
        /shiro/logout.do = logout
        /user.jsp = roles[user]
        /admin.jsp = roles[admin]

        # everything else requires authentication:
        /** = authc
         */
        map.put("/login.jsp","anon");
        map.put("/shiro/login.do","anon");
        map.put("/shiro/logout.do","logout");
        map.put("/user.jsp","authc,roles[user]");
        map.put("/admin.jsp","authc,roles[admin]");
        map.put("/list.jsp","user");

        map.put("/**","authc");

        return map;
    }
}
