package org.example.ormbulkinsert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TempService {

  private final JdbcTemplate jdbcTemplate;
  private final TempRepository tempRepository;

  public TempService(JdbcTemplate jdbcTemplate, TempRepository tempRepository) {
    this.jdbcTemplate = jdbcTemplate;
    this.tempRepository = tempRepository;
  }

  public void print() {
//    String sql = "SELECT * FROM temp";
//    jdbcTemplate.execute(sql);

    List<TempEntity> list = tempRepository.findAll();
    System.out.println(list.size());
  }
}
