package com.lottery.lottery.repository;

import com.lottery.lottery.entity.LotteryEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LotteryEntryRepository extends JpaRepository<LotteryEntry, Long> {
    List<LotteryEntry> findByMobile(String mobile);
}
