package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import models.BlogModel;

public class BlogMapper implements RowMapper<BlogModel>
{

	@Override
	public BlogModel mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		BlogModel blog = new BlogModel();
		blog.setId(rs.getLong("blog_id"));
		blog.setUserId(rs.getLong("user_id"));
		blog.setTitle(rs.getString("title"));
		blog.setContent(rs.getString("content"));
		java.sql.Timestamp createdAtTimestamp = rs.getTimestamp("created_at");
        if (createdAtTimestamp != null)
        {
            blog.setCreatedAt(createdAtTimestamp.toLocalDateTime());
        }

        java.sql.Timestamp updatedAtTimestamp = rs.getTimestamp("updated_at");
        if (updatedAtTimestamp != null)
        {
            blog.setUpdatedAt(updatedAtTimestamp.toLocalDateTime());
        }
		return blog;
	}

}
