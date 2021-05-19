package com.hsbc.library.repository;

import com.hsbc.library.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ReaderRepository extends CrudRepository<Reader, Long> {

    List<Reader> findByName(String name);
}
