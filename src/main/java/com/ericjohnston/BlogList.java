package com.ericjohnston;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import models.BlogModel;
public class BlogList
{
	private List<BlogModel> blogs = new ArrayList<BlogModel>();
	
	@XmlElement(name="blog")
	public List<BlogModel> getBlogs()
	{
		return blogs;
	}
}
