package edu.miu.cs.cs544.repository;

import edu.miu.common.repository.BaseRepository;

import edu.miu.cs.cs544.domain.Attendance;

import java.util.Optional;

public interface AttendanceRepository extends BaseRepository<Attendance, Integer> {
    public Optional<Attendance> findByAttendanceId(Integer attendanceId);

}
