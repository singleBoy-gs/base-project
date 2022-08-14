package com.base.example.mapper;

import com.base.example.model.entity.HkCamera;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Single Minded
 * @create 2022-08-07 18:54:07
 * @Description
 * @since 1.0
 */
@Mapper
public interface ExampleMapper {
	String datasourceQuery(@Param("cameraIndexCode") String cameraIndexCode);

	List<HkCamera> datasourceQueryList();
}
