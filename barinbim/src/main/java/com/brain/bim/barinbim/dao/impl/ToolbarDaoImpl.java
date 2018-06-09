package com.brain.bim.barinbim.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.brain.bim.barinbim.dao.ToolbarDao;
import com.brain.bim.barinbim.model.gui.UiToolbarItem;


@Repository
public class ToolbarDaoImpl implements ToolbarDao {
  
  private final Logger logger = LoggerFactory.getLogger(UsersDaoImpl.class);

  private final JdbcTemplate               jdbcTemplate;

  
  @Autowired
  public ToolbarDaoImpl(final JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }


  
  @Override
  public List<UiToolbarItem> listToolbarItems() {
    // TODO Auto-generated method stub
    return queryForToolbarItems("", null);
  }  

  private List<UiToolbarItem> queryForToolbarItems(String parrent, UiToolbarItem parentItem) {
    
    final String sql = " select id, parent, label, url, image, status, pos, type from tbltoolbar where parent= ? order by pos";
    
    List<UiToolbarItem> list = jdbcTemplate.query(sql, new Object[]{parrent}, new RowMapper<UiToolbarItem>(){

      @Override
      public UiToolbarItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        return createToolbarItemFromResultSet(rs, parentItem);
      }
      
    });
    
    return list;
    
  }  

  private UiToolbarItem createToolbarItemFromResultSet(ResultSet rs, UiToolbarItem parentItem) throws SQLException {
      
    UiToolbarItem umodel = new UiToolbarItem();
  
    umodel.setId(rs.getString("id"));
    umodel.setLabel(rs.getString("label"));
    umodel.setUrl(rs.getString("url"));
    umodel.setImage(rs.getString("image"));
    umodel.setType(rs.getString("type"));
    umodel.setStatus(rs.getInt("status"));
    umodel.setPos(rs.getInt("pos"));
    umodel.setParent(parentItem);
    umodel.addchildren(queryForToolbarItems(umodel.getId(), umodel));
    
    return umodel;
      
  }
}
