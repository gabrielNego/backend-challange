package com.study.repository;

import com.study.repository.entity.TransactionHistory;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransactionHistoryRepository implements PanacheRepository<TransactionHistory>{

}
