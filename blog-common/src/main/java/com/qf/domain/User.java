package com.qf.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author : sin
 * @date : 2023/10/25 23:08
 * @Description :
 */
@Data
@TableName(value = "redpig_sys_user",autoResultMap = true)
@Schema(description = "用户")
public class User implements Serializable, UserDetails {

    /** 主键ID **/
    @Schema(description = "主键ID")
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /** 删除状态：0、已删除 1、未删除 **/
    @Schema(description = "删除状态：0、已删除 1、未删除")
    @TableField(value = "deleteStatus")
    private int deleteStatus;

    /** 创建时间 **/
    @Schema(description = "创建时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "createTime",fill = FieldFill.INSERT)
    private java.util.Date createTime;

    /** 更新时间 **/
    @Schema(description = "更新时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "updateTime",fill = FieldFill.INSERT_UPDATE)
    private java.util.Date updateTime;

    /** 用户名 **/
    @Schema(description = "用户名")
    @TableField(value = "username")
    private String username;

    /** 用户昵称 **/
    @Schema(description = "用户昵称")
    @TableField(value = "nickname")
    private String nickname;

    /** 密码 **/
    @Schema(description = "密码")
    @TableField(value = "password")
    private String password;

    /** 开启 **/
    @Schema(description = "开启或者关闭")
    @Getter(AccessLevel.NONE) //不生成getEnabled 因为继承了SpringSecurity的UserDetails 有isEnabled
    @TableField(value = "enabled")
    private boolean enabled;

    /** 性别：0、女 1、男 **/
    @Schema(description = "性别：0、女 1、男")
    @TableField(value = "sex")
    private int sex;

    /** 手机号 **/
    @Schema(description = "手机号")
    @TableField(value = "mobile")
    private String mobile;

    /** 邮箱 **/
    @Schema(description = "邮箱")
    @TableField(value = "email")
    private String email;

    /** 部门ID **/
    @Schema(description = "部门ID")
    @TableField(value = "dept_id")
    private Long deptId;


    /** 部门 **/
    @Schema(description = "部门")
    @TableField(exist = false)
    private Dept dept;
    //
    @TableField(exist = false)
    private List<Role> roles;
    //
    @Schema(hidden = true)
    @TableField(exist = false)
    private List<Perm> perms;

    /** 创建者 **/
    @Schema(description = "创建者")
    @TableField(value = "create_by",fill = FieldFill.INSERT)
    private String createBy;

    /** 更新者 **/
    @Schema(description = "更新者")
    @TableField(value = "update_by",fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @TableField(exist = false)
    private List<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
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
        return this.enabled;
    }
}
