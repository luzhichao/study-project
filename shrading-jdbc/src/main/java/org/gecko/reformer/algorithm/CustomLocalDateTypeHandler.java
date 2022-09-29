package org.gecko.reformer.algorithm;

import com.alibaba.csp.sentinel.util.StringUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 描述：解决shardingJdbc不支持LocalDate类型转换问题
 *
 * @author jianpeng
 * @date 2022/6/30 18:48*/


@Component
@MappedTypes(LocalDate.class)
@MappedJdbcTypes(value = JdbcType.DATE, includeNullJdbcType = true)
public class CustomLocalDateTypeHandler extends BaseTypeHandler<LocalDate> {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * 功能描述: 重写的第一个实现方法，返回的是string类型，会导致入参类型是localdatetime时，抛错！！！ 我的解决办法是第一个实现方法和原生的一致
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, LocalDate parameter, JdbcType jdbcType) throws SQLException {
        /*if (parameter != null) {
            ps.setString(i, dateTimeFormatter.format(parameter));
        }*/
        ps.setObject(i, parameter);
    }

    @Override
    public LocalDate getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String target = rs.getString(columnName);
        if (StringUtil.isEmpty(target)) {
            return null;
        } else {
            return LocalDate.parse(target, dateTimeFormatter);
        }
    }

    @Override
    public LocalDate getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String target = rs.getString(columnIndex);
        if (StringUtil.isEmpty(target)) {
            return null;
        }
        return LocalDate.parse(target, dateTimeFormatter);
    }

    @Override
    public LocalDate getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String target = cs.getString(columnIndex);
        if (StringUtil.isEmpty(target)) {
            return null;
        }
        return LocalDate.parse(target, dateTimeFormatter);
    }
}
