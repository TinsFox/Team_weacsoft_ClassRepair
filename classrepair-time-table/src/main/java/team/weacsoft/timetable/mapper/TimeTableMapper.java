package team.weacsoft.timetable.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import team.weacsoft.timetable.dto.reponse.OnlineDto;
import team.weacsoft.timetable.entity.TimeTable;

import java.util.List;

/**
 * <p>
 * 值班表 Mapper 接口
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-01-26
 */
public interface TimeTableMapper extends BaseMapper<TimeTable> {

    @Select("SELECT t.user_id , t.create_time, name, phone, identity_id, nick_name from time_table t" +
            " LEFT JOIN user_info u ON t.user_id = u.id where t.state=1")
    List<OnlineDto> getAllOnline();
}
