package edu.miu.cs.cs544.service.mapper;

import edu.miu.common.service.mapper.BaseMapper;
import edu.miu.cs.cs544.domain.Schedule;
import edu.miu.cs.cs544.service.contract.SchedulePayLoad;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class SchedulePayLoadToScheduleMapper extends BaseMapper<SchedulePayLoad, Schedule> {

        public SchedulePayLoadToScheduleMapper(MapperFactory mapperFactory) {
            super( mapperFactory, SchedulePayLoad.class, Schedule.class);
        }
}
