package com.intech.shareresources.util;

import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.util.PGobject;
import org.springframework.stereotype.Service;

@Service
public class DbUtil {

  private static final Logger LOG = LogManager.getLogger(DbUtil.class);

  public PGobject wrapToJsonb(String json) {
    PGobject pgJson = new PGobject();
    pgJson.setType("jsonb");
    try {
      pgJson.setValue(json);
    } catch (SQLException e) {
      LOG.error(() -> "Unable to wrap Jsonb", e);
    }
    return pgJson;
  }

}
