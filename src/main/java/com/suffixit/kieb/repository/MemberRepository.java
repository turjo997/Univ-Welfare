package com.suffixit.kieb.repository;

import com.suffixit.kieb.dto.MemberStatisticDTO;
import com.suffixit.kieb.entities.Member;
import com.suffixit.kieb.entities.Users;
import com.suffixit.kieb.enumerations.ApproveStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {


    boolean existsByEmailId(String email);
    Optional<Member> findByEmailId(String emailId);

    Page<Member> findAll(Specification<Member> specification, Pageable pageable);

    Member findMemberById(Integer memberId);
    @Query(value =
            "SELECT a.date_list AS date,\n" +
                    "       COALESCE(b.totalMembers,0) totalMembers,\n" +
                    "       COALESCE(b.totalPendingMembers, 0) AS totalPendingMembers,\n" +
                    "       COALESCE(b.totalApprovedMembers, 0) AS totalApprovedMembers,\n" +
                    "       COALESCE(b.totalDeclinedMembers, 0) AS totalDeclinedMembers\n" +
                    "FROM (SELECT DATE_ADD(CURDATE(), INTERVAL -(n - 1) DAY) AS date_list\n" +
                    "FROM (\n" +
                    "    SELECT @n \\:= @n + 1 AS n\n" +
                    "    FROM (\n" +
                    "        SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4\n" +
                    "        UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7\n" +
                    "        UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10\n" +
                    "        UNION ALL SELECT 11 UNION ALL SELECT 12 UNION ALL SELECT 13\n" +
                    "        UNION ALL SELECT 14 UNION ALL SELECT 15 UNION ALL SELECT 16\n" +
                    "        UNION ALL SELECT 17 UNION ALL SELECT 18 UNION ALL SELECT 19\n" +
                    "        UNION ALL SELECT 20 UNION ALL SELECT 21 UNION ALL SELECT 22\n" +
                    "        UNION ALL SELECT 23 UNION ALL SELECT 24 UNION ALL SELECT 25\n" +
                    "        UNION ALL SELECT 26 UNION ALL SELECT 27 UNION ALL SELECT 28\n" +
                    "        UNION ALL SELECT 29 UNION ALL SELECT 30\n" +
                    "    ) numbers\n" +
                    "    CROSS JOIN (SELECT @n \\:= 0) init\n" +
                    ") t\n" +
                    ") a\n" +
                    "LEFT JOIN (\n" +
                    "    SELECT date_format(m.add_date,'%Y-%m-%d') AS date,\n" +
                    "        count(m.id) AS totalMembers,\n" +
                    "        sum(case when m.approve_status='PENDING' then 1 else 0 END) AS totalPendingMembers,\n" +
                    "        sum(case when m.approve_status='APPROVED' then 1 else 0 END) AS totalApprovedMembers,\n" +
                    "        sum(case when m.approve_status='DECLINED' then 1 else 0 END) AS totalDeclinedMembers\n" +
                    "    FROM member m\n" +
                    "    WHERE add_date BETWEEN DATE_SUB(CURDATE(), INTERVAL 30 DAY) AND CURDATE()\n" +
                    "    GROUP BY date_format(m.add_date,'%Y-%m-%d')\n" +
                    ") b ON a.date_list = b.date\n" +
                    "ORDER BY a.date_list;", nativeQuery = true)
    List<MemberStatisticDTO> getMemberStatisticsForDate();

//    @Query(value =
//            "SELECT a.date_list,\n" +
//                    "       COALESCE(b.total,0) total,\n" +
//                    "       COALESCE(b.pending, 0) AS pending,\n" +
//                    "       COALESCE(b.approved, 0) AS approved,\n" +
//                    "       COALESCE(b.declined, 0) AS declined\n" +
//                    "FROM (SELECT DATE_ADD(DATE(NOW()), INTERVAL -(LEVEL - 1) DAY) AS date_list\n" +
//                    "FROM (\n" +
//                    "with recursive rnums as (\n" +
//                    "select 1 as level\n" +
//                    "union all\n" +
//                    "select level+1 as level from rnums\n" +
//                    "where level < 30\n" +
//                    ")\n" +
//                    "select * from rnums\n" +
//                    ") AS t\n" +
//                    ") a\n" +
//                    "LEFT JOIN (\n" +
//                    "    SELECT date_format(m.add_date,'%Y-%m-%d') AS DATE1,\n" +
//                    "        count(m.id) AS total,\n" +
//                    "        sum(case when m.approve_status='PENDING' then 1 else 0 END) AS pending,\n" +
//                    "        sum(case when m.approve_status='APPROVED' then 1 else 0 END) AS approved,\n" +
//                    "        sum(case when m.approve_status='DECLINED' then 1 else 0 END) AS declined\n" +
//                    "    FROM member m\n" +
//                    "    WHERE add_date BETWEEN DATE_SUB(CURDATE(), INTERVAL 30 DAY) AND CURDATE()\n" +
//                    "    GROUP BY date_format(m.add_date,'%Y-%m-%d')\n" +
//                    ") b ON a.date_list = b.date1\n" +
//                    "ORDER BY a.date_list", nativeQuery= true)
//    List<MemberStatisticDTO> getMemberStatisticsForDate();

    boolean existsByRollAndApproveStatus(String rollNo, ApproveStatus approveStatus);


    long countByApproveStatus(ApproveStatus approveStatus);


    Set<Member> findAllByRollIn(Set<String> memberList);

}
