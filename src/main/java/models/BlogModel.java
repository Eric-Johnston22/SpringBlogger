package models;

import java.time.LocalDateTime;

public class BlogModel 
{
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public BlogModel()
	{
		
	}
    
    
    
	public Long getId() 
    {
		return id;
	}
	
	public Long getUserId()
	{
		return userId;
	}
	public String getTitle() 
	{
		return title;
	}
	
	public String getContent() 
	{
		return content;
	}
	
	public LocalDateTime getCreatedAt() 
	{
		return createdAt;
	}
	
	public LocalDateTime getUpdatedAt() 
	{
		return updatedAt;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}



	public void setId(Long id)
	{
		this.id = id;
	}



	public void setTitle(String title)
	{
		this.title = title;
	}



	public void setContent(String content)
	{
		this.content = content;
	}



	public void setCreatedAt(LocalDateTime createdAt)
	{
		this.createdAt = createdAt;
	}



	public void setUpdatedAt(LocalDateTime updatedAt)
	{
		this.updatedAt = updatedAt;
	}
    
	
	
    // Getters and Setters
	
}
