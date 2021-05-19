package com.hsbc.library.repository;

import com.hsbc.library.domain.Borrowing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface BorrowingRepository extends CrudRepository<Borrowing, Long> {
}
