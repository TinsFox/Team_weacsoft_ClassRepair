package team.weacsoft.repair.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.repair.dto.request.CommonRepairItemDto;
import team.weacsoft.repair.entity.RepairItem;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 报修表 服务类
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-01-27
 */
public interface IRepairItemStateService extends IService<RepairItem> {

    /**
     * 获取所有的未接订单
     */
    Page<CommonRepairItemDto> getAllMissedOrder(PageRequest pageRequest);

    /**
     * 管理员-我的待处理订单
     */
    Page<CommonRepairItemDto> getMyAllMissedOrders(PageRequest pageRequest, HttpServletRequest request);

    /**
     * 管理员-我的所有已处理订单
     */
    Page<CommonRepairItemDto> getMyAllProcessedOrders(PageRequest pageRequest, HttpServletRequest request);

    /**
     * 管理员-他人待处理订单
     */
    Page<CommonRepairItemDto> getOtherAllMissedOrders(PageRequest pageRequest, HttpServletRequest request);

    /**
     * 管理员-他人所有已处理订单
     */
    Page<CommonRepairItemDto> getOtherAllProcessedOrders(PageRequest pageRequest, HttpServletRequest request);

    /**
     * 用户侧-获取我的待处理订单
     */
    Page<CommonRepairItemDto> getUserAllMissedOrders(PageRequest pageRequest, HttpServletRequest request);

    /**
     * 用户侧-获取我的所有报修单
     */
    Page<CommonRepairItemDto> getUserAllOrders(PageRequest pageRequest, HttpServletRequest request);

    /**
     * 模糊搜索订单
     */
    Page<CommonRepairItemDto> searchRepairItem(PageRequest pageRequest, String repairItemId,
                                                String ordererName, Integer receiverIdentityId);
}
