package cn.kgc.shiro.handlers;

import cn.kgc.shiro.service.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.xml.stream.util.StreamReaderDelegate;

@Controller
@RequestMapping("/shiro")
public class ShiroHandler {

    @Autowired
    private ShiroService shiroService;

    @RequestMapping("/testShiroAnnotation.do")
    @RequiresRoles({"admin"})
    public String testShiroAnnotation(HttpSession session){
        session.setAttribute("key","value12345");
        shiroService.testMethod();
        return "redirect:/list.jsp";
    }

    @RequestMapping("/login.do")
    public String login(@RequestParam("username") String username,@RequestParam("password") String password){
        Subject currentUser = SecurityUtils.getSubject();
        System.out.println("==============");

        if (!currentUser.isAuthenticated()){
            //把用户名和密码封装为UsernamePasswordToken对象
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            //remember
            token.setRememberMe(true);
            try {
                //执行登入
                currentUser.login(token);
            }catch (AuthenticationException ae){
                //所有认证时异常父类
                System.out.println("登入失败："+ae.getMessage());
            }
        }
        return "redirect:/list.jsp";
    }
}
