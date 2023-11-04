package com.qf.domain;

import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author sin
 * @since 2023/10/26 17:08
 */
@Getter
@Setter
@ToString
@TableName("qf_menu")
@ApiModel(value = "QfMenu对象", description = "")
public class QfMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(ordinal = 1)
    private Integer id;

    @ApiModelProperty("菜单名")
    @JSONField(ordinal = 2)
    private String name;

    @ApiModelProperty("菜单icon")
    @JSONField(ordinal = 3)
    private String icon;

    @ApiModelProperty("菜单路径")
    @JSONField(ordinal = 4)
    private String path;

    @ApiModelProperty("组件")
    @JSONField(ordinal = 5)
    private String component;

    @ApiModelProperty("权限")
    @JSONField(ordinal = 6)
    private String perms;

    @ApiModelProperty("类型：M-目录 C-菜单 F-按钮")
    @JSONField(ordinal = 7)
    private char type;

    @ApiModelProperty("父id")
    @JSONField(ordinal = 8)
    private Integer parentId;



    @ApiModelProperty("排序")
    @JSONField(ordinal = 9)
    private Byte orderNum;

    @ApiModelProperty("删除状态")
    @JSONField(ordinal = 10)
    private Boolean delStatus;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    @JSONField(ordinal = 11)
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    @JSONField(ordinal = 12)
    private LocalDateTime updateTime;

    @ApiModelProperty("备注")
    @JSONField(ordinal = 13)
    private String remark;

    @ApiModelProperty("创建者")
    @JSONField(ordinal = 14)
    private String createBy;

    @ApiModelProperty("修改者")
    @JSONField(ordinal = 15)
    private String updateBy;

    @ApiModelProperty("孩子节点")
    @JSONField(ordinal = 16)
    private List<QfMenu> children;
}
