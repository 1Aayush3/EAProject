package edu.miu.common.service.contract;

import lombok.Data;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class SyncEventResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String sourceName;

    private String targetName;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private Integer sourceCount = 0;

    private Integer createCount = 0;

    private Integer updateCount = 0;

    private Integer deleteCount = 0;

    private CommonAuditData AuditData = new CommonAuditData();

    public String getDuration() {
        if(Objects.nonNull(this.startDateTime) && Objects.nonNull(this.endDateTime)) {
            Duration duration = Duration.between(this.startDateTime, this.endDateTime);
            return String.format("%02d:%02d:%02d",
                    duration.toHours(),
                    duration.toMinutes() % 60,
                    duration.getSeconds() % 60);
        } else {
            return "";
        }
    }

}
