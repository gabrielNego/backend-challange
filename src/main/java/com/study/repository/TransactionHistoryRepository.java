package com.study.repository;

import com.study.repository.entity.TransactionHistory;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransactionHistoryRepository implements PanacheRepository<TransactionHistory>{

}
