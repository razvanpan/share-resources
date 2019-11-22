package com.intech.shareresources.dao.impl;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.intech.shareresources.controller.UserController;
import com.intech.shareresources.dao.ResourceDao;
import com.intech.shareresources.dao.mapper.ResourceMapper;
import com.intech.shareresources.model.ResourceComplex;
import com.intech.shareresources.model.ResourceOfUser;
import com.intech.shareresources.model.ResourcePicture;
import com.intech.shareresources.model.ResourceToEdit;
import com.intech.shareresources.model.ResourceToInsert;
import com.intech.shareresources.util.DbUtil;
import com.intech.shareresources.util.JsonUtil;

@Service
public class ResourceDaoImpl implements ResourceDao {

  private static final Logger LOG = LogManager.getLogger(UserController.class);
  private final static Pattern LTRIM = Pattern.compile("^\\s+");
  @Autowired
  private JdbcTemplate jdbcTemplate;
  @Autowired
  private DbUtil dbUtil;
  @Autowired
  private JsonUtil jsonUtil;
  @Autowired
  private ResourceMapper resMap;

  @Override
  public boolean addNewResource(ResourceToInsert resource) {
    LOG.info(() -> "Adding resource");

    int addRows = jdbcTemplate.update(
        "INSERT INTO resource (idcategory, title, iduser, type, photo, shortdescription, tags, registerdate) VALUES (?,?,?,?,?,?,?,?)",
        resource.getIdCategory(), resource.getTitle(), resource.getIdUser(),
        resource.getType().orElse(null), resource.getPhoto().orElse(null),
        resource.getShortDescription().orElse(null),
        dbUtil.wrapToJsonb(jsonUtil.convertToJson(resource.getTags())),
        Timestamp.valueOf(LocalDateTime.now()));
    if (addRows == 0) {
      LOG.info(() -> "Resource can not be added");
      return false;
    } else {
      LOG.info(() -> "Resource was added");
      return true;
    }
  }

  @Override
  public boolean editResource(ResourceToEdit resource) {
    LOG.info(() -> "Updating resource in dao");
    int addRows = jdbcTemplate.update(
        "update resource set idcategory = ?, title = ?, type = ?, shortdescription = ?, tags = ? where idresource = ?",
        resource.getIdCategory(), resource.getTitle(), resource.getType().orElse(null),
        resource.getShortDescription().orElse(null),
        dbUtil.wrapToJsonb(jsonUtil.convertToJson(resource.getTags())), resource.getIdResource());

    if (addRows == 0) {
      LOG.info(() -> "Resource can not be updated");
      return false;
    } else {
      LOG.info(() -> "Resource was updated");
      return true;
    }

  }

  public List<ResourceOfUser> findAllUserResources(int id) {
    LOG.info(() -> "Select all data fom table resource into string");
    String sql =
        "SELECT * FROM resource res INNER JOIN category cat ON cat.idcategory=res.idcategory where iduser = ?";

    LOG.info(() -> "All data from table resource has been selected");
    LOG.info(() -> "Convert all data from db into single object");
    List<ResourceOfUser> resources = jdbcTemplate.query(sql, new Object[] {id},
        (ResultSet rs, int rowNm) -> resMap.convertToResourceOfUser(rs));
    LOG.info(() -> "The converson has been made");
    LOG.info(() -> "Resource returned");
    return resources;
  }

  @Override
  public List<ResourceComplex> findAll(String title, Long id) {
    LOG.info(() -> "Select all data fom table resource into string");
    if (title != null && title != "") {
      String rtrim = null;
      rtrim = title.replaceAll("\\s+$", "");
      title = rtrim;
    }
    String sql = null;
    List<ResourceComplex> resource = null;
    if ((title == null && id == null) || (title == "" && id == null) || (title == null && id == 0)
        || (title == "" && id == 0)) {
      sql =
          "SELECT * FROM resource res INNER JOIN \"user\" usr ON res.iduser = usr.iduser INNER JOIN category cat ON cat.idcategory=res.idcategory";
      resource =
          jdbcTemplate.query(sql, (ResultSet rs, int rowNm) -> resMap.convertToResourceComplex(rs));
    }
    if ((title != null && id == null && title != "") || (title != null && id == 0 && title != "")) {
      sql =
          "SELECT * FROM resource res INNER JOIN \"user\" usr ON res.iduser = usr.iduser INNER JOIN category cat ON cat.idcategory=res.idcategory where res.title=?";
      resource = jdbcTemplate.query(sql, new Object[] {title},
          (ResultSet rs, int rowNm) -> resMap.convertToResourceComplex(rs));
    }
    if ((title == "" && id != null && id != 0) || (title == null && id != null && id != 0)) {
      sql =
          "SELECT * FROM resource res INNER JOIN \"user\" usr ON res.iduser = usr.iduser INNER JOIN category cat ON cat.idcategory=res.idcategory where res.idcategory=?";
      resource = jdbcTemplate.query(sql, new Object[] {id},
          (ResultSet rs, int rowNm) -> resMap.convertToResourceComplex(rs));
    }
    if (title != null && id != null && !(title.isEmpty()) && id != 0) {
      sql =
          "SELECT * FROM resource res INNER JOIN \"user\" usr ON res.iduser = usr.iduser INNER JOIN category cat ON cat.idcategory=res.idcategory where res.title=? AND res.idcategory=? ";
      resource = jdbcTemplate.query(sql, new Object[] {title, id},
          (ResultSet rs, int rowNm) -> resMap.convertToResourceComplex(rs));

    }
    LOG.info(() -> "All data from table resource has been selected");
    LOG.info(() -> "Convert all data from db into single object");
    LOG.info(() -> "The converson has been made");
    LOG.info(() -> "Resource returned");
    return resource;
  }

  public static String ltrim(String s) {
    return LTRIM.matcher(s).replaceAll("");
  }

  @Override
  public ResourceComplex findResourceById(long id) {
    String sql =
        "SELECT * FROM resource res INNER JOIN \"user\" usr ON res.iduser = usr.iduser INNER JOIN category cat ON cat.idcategory=res.idcategory WHERE idresource= ?";
    ResourceComplex resource = jdbcTemplate.query(sql, new Object[] {id},
        (ResultSet rs) -> resMap.convertToSingleResourceComplex(rs));
    LOG.info("Resource with coresponding ID was found!");
    return resource;
  }

  @Override
  public boolean changePicture(ResourcePicture resource) {
    LOG.info(() -> "Updating resource picture in dao");
    int addRows = jdbcTemplate.update("update resource set photo = ? where idresource = ?",
        resource.getPhoto().orElse(null), resource.getIdResource());
    if (addRows == 0) {
      LOG.info(() -> "Picture can not be changed");
      return false;
    } else {
      LOG.info(() -> "Picture was changed");
      return true;
    }
  }



}
