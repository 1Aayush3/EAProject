package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.Location;
import edu.miu.cs.cs544.domain.Scanner;
import edu.miu.cs.cs544.domain.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void whenCalculateAttendanceByMemberId_thenReturnObjectList() {
        // given
        Session session1 = new Session(LocalDate.of(2024, 2, 1), LocalTime.of(10, 0, 0), LocalTime.of(12, 0, 0));
        Session session2 = new Session(LocalDate.of(2024, 2, 7), LocalTime.of(10, 0, 0), LocalTime.of(12, 0, 0));
        Session session3 = new Session(LocalDate.of(2024, 2, 14), LocalTime.of(9, 0, 0), LocalTime.of(11, 0, 0));

//        Location location = new Location()
//
//        Scanner scanner = new Scanner()


        // when
//        Object[] found = memberRepository.calculateAttendanceByMemberId()
    }
}
