package com.Xoot.CreditoParaTi.mapper;

import com.Xoot.CreditoParaTi.mapper.DocStatusMap;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DocStatusMapper implements RowMapper<DocStatusMap> {
    @Override
    public DocStatusMap mapRow(ResultSet rs, int rowNum) throws SQLException {
        DocStatusMap docStatusMap = new DocStatusMap();
        docStatusMap.setTypeDocument(rs.getInt("typeDocument"));
        docStatusMap.setStatus(rs.getInt("status"));
        return docStatusMap;
    }
}
