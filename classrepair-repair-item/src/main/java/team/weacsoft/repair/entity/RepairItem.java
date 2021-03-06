package team.weacsoft.repair.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import team.weacsoft.common.persistence.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 报修表
 * state: 0-异常订单 1-待处理 2-处理中 3-已处理 4-已取消
 * @author GreenHatHG
 * @since 2020-01-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RepairItem extends BaseEntity {

    /**
     * 报修单id，后端自动生成，规则：当前日期+时间戳前十一位数字，2020012715801331743
     */
    private String repairItemId;

    /**
     *  接单人(表id)
     */
    private Integer receiver = 0;

    /**
     *  报修人(表id)
     */
    private Integer orderer;

    /**
     * 课室
     */
    @NotBlank
    @Size(max = 100)
    private String classroom;

    /**
     * 故障设备
     */
    @NotNull
    private Integer equipmentType;

    /**
     * 问题描述
     */
    @NotBlank
    @Size(max = 300)
    private String problem;

    /**
     * 报修人手机号
     */
    private String ordererPhone = "";

}
