package com.starfish.notify.mapper;

import com.starfish.notify.model.NotifyModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotifyMapper {

    int deleteByPrimaryKey(Long id);

    int insert(NotifyModel record);

    int insertSelective(NotifyModel record);

    NotifyModel selectByPrimaryKey(Long id);

    List<NotifyModel> list(NotifyModel notifyModel);

    int updateByPrimaryKeySelective(NotifyModel record);

    int updateByPrimaryKey(NotifyModel record);

}