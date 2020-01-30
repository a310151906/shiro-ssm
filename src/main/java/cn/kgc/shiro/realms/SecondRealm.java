package cn.kgc.shiro.realms;


import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

public class SecondRealm extends AuthenticatingRealm{
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("[SecondRealm] doGetAuthenticationInfo");

//      System.out.println("doGetAuthenticationInfo"+token);
//      1.把AuthenticationToken转换为UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
//      2.从UsernamePasswordToken中来获取username
        String username = upToken.getUsername();
//      3.调用数据库的方法，从数据库中查询username对应的记录
        System.out.println("从数据库中获取username:"+ username + "所对应的用户信息。");
//      4.若用户不存在，则可以抛出UnknownAccountException 异常
        if("unknown".equals(username)){
            throw new UnknownAccountException("用户不存在！");
        }

//      5.根据用户信息的情况，决定是否需要抛出其他的AuthenticationException异常
        if("monster".equals(username)){
            throw  new LockedAccountException("用户被锁定！");
        }
        //在这中间可以选择将数据库的密码进行加密
        //source 为来自数据库的密码
        String sourece="123456";

        /* public SimpleHash(String algorithmName, Object source, Object salt, int hashIterations)
        algorithmName:采用的加密算法
        source:需要加密的资源
        salt:盐值
        hashIterations:加密次数*/
        //将来自数据库的密码进行与前台密码相同的加密方式
        //SimpleHash credentials = new SimpleHash("MD5", sourece, salt, 1024);

        //盐值加密（不同的用户拥有不同的盐值，加密后的密码也就不同）
        //credentials为盐值加密后的密码
        ByteSource salt=ByteSource.Util.bytes(username);
        Object credentials=null;
        if("admin".equals(username)){
            credentials = new SimpleHash("SHA1", sourece, salt, 1024);
        }else if("user".equals(username)){
            credentials = new SimpleHash("SHA1", sourece, salt, 1024);
        }

//      6.根据用户的情况，来构建AuthenticationInfo 对象返回。通常使用的实现类为：SimpleAuthenticationInfo
//      以下信息是从数据库中获取的。
//        SimpleAuthenticationInfo(Object principal, Object credentials, String realmName)
//         public SimpleAuthenticationInfo(Object principal, Object hashedCredentials, ByteSource credentialsSalt, String realmName)
//        1）principal：认证的实体信息。可以是username,也可以是数据表对应的用户的实体类对象
//        2）credentials:数据库获取的密码
//        3）realmName：当前realm对象的name。调用父类的getName方法即可
//        4）credentialsSalt:盐值
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,credentials,salt,getName());

        return info;
    }
}
