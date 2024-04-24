package com.module.mapper;

import com.module.pojo.Scoreinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ScoreinfoMapper {

    @Select("select * from scoreinfo ")
    List<Scoreinfo> selectByMap(Map map);

    @Insert("INSERT INTO scoreinfo(uid, tid, score) VALUES ( #{uid},  #{tid}, #{score})")
    int insert(Scoreinfo scoreinfo);

}
