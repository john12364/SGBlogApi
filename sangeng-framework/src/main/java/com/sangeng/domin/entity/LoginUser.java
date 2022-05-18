package com.sangeng.domin.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class LoginUser  implements UserDetails  {

    @Autowired
    private User user;
    //存储权限信息
    private List<String> permissions;
    public LoginUser(User user) {
        this.user = user;
    }
    public LoginUser(User user,List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    //存储SpringSecurity所需要的权限信息的集合 不让redis对这个进行序列化 不然会报错
    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities != null) {
            return authorities;
        }
        /*
          List<GrantedAuthority> newList = new ArrayList<>();
          for(String permission: permissions) {
          SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
          newList.add(authority);
          }


        * */
//        //把permission中的字符串类型的权限信息转换成GrantedAuthority对象存入authorities中
//        authorities = permissions.stream().
//                map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
