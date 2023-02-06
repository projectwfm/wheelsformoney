package com.jsp.jspwfm.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.jspwfm.Models.Entities.Image;

@Repository
public interface ImageDao extends JpaRepository<Image, Integer>
{

}
