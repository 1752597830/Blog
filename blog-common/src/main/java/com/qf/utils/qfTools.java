package com.qf.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : sin
 * @date : 2023/10/25 22:13
 * @Description :
 */
public class qfTools {
    public static boolean contains(String str,String ...args){
        for (String arg : args) {
            if(str.contains(arg)){
                return true;
            }
        }
        return false;
    }

    //public static String getUsername(){
    //    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //    if(authentication==null || authentication.getPrincipal().equals("anonymousUser")){
    //        return null;
    //    }
    //
    //    return ((User)authentication.getPrincipal()).getUsername();
    //}

    //public static boolean hasRole(String roleTag){
    //    List<Role> roles = getUser().getRoles();
    //    if(CollUtil.isEmpty(roles)){
    //        return false;
    //    }
    //    Set<String> roleTags = roles.stream().map(Role::getTag).collect(Collectors.toSet());
    //    if(CollUtil.isEmpty(roleTags)){
    //        return false;
    //    }
    //    return roleTags.contains(roleTag);
    //}
    //
    //public static boolean hasNoRole(String roleTag){
    //    return !hasRole(roleTag);
    //}
    //
    //public static User getUser(){
    //    if(SecurityContextHolder.getContext().getAuthentication() == null){
    //        return null;
    //    }
    //
    //    if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User){
    //        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //        return user;
    //    }
    //
    //    return null;
    //
    //}

}