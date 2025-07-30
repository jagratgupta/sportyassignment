package com.example.sporty.repository;

import com.example.sporty.model.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Long> {
    List<Bet> findByEventId(Long eventId);
}
