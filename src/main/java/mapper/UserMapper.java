package mapper;

import org.springframework.jdbc.core.RowMapper;
import models.UserModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserModel>
{
    @Override
    public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        UserModel user = new UserModel();
        user.setId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return user;
    }
}