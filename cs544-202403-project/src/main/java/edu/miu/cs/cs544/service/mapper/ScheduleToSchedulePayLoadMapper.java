package edu.miu.cs.cs544.service.mapper;

import edu.miu.common.service.mapper.BaseMapper;
import edu.miu.cs.cs544.domain.Schedule;
import edu.miu.cs.cs544.service.contract.SchedulePayLoad;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component

public class ScheduleToSchedulePayLoadMapper extends BaseMapper<Schedule, SchedulePayLoad> {
    public ScheduleToSchedulePayLoadMapper(MapperFactory mapperFactory) {
        super( mapperFactory, Schedule.class, SchedulePayLoad.class);
    }
}
