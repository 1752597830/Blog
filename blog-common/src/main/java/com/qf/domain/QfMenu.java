package com.qf.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("qf_menu")
@ApiModel(value = "QfMenu对象", description = "")
public class QfMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("菜单名")
    private String name;

    @ApiModelProperty("菜单icon")
    private String icon;

    @ApiModelProperty("菜单路径")
    private String path;

    @ApiModelProperty("组件")
    private String component;

    @ApiModelProperty("父id")
    private Integer parentId;

    @ApiModelProperty("排序")
    private Byte orderNum;

    @ApiModelProperty("删除状态")
    private Boolean delStatus;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建者")
    private String createBy;

    @ApiModelProperty("修改者")
    private String updateBy;
}
