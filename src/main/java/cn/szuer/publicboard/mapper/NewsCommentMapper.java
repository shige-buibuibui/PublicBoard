package cn.szuer.publicboard.mapper;

import cn.szuer.publicboard.model.NewsComment;
import cn.szuer.publicboard.model.NewsCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsCommentMapper {
    int countByExample(NewsCommentExample example);

    int deleteByExample(NewsCommentExample example);

    int deleteByPrimaryKey(Integer commentid);

    int insert(NewsComment record);

    int insertSelective(NewsComment record);

    List<NewsComment> selectByExampleWithBLOBs(NewsCommentExample example);

    List<NewsComment> selectByExample(NewsCommentExample example);

    NewsComment selectByPrimaryKey(Integer commentid);

    int updateByExampleSelective(@Param("record") NewsComment record, @Param("example") NewsCommentExample example);

    int updateByExampleWithBLOBs(@Param("record") NewsComment record, @Param("example") NewsCommentExample example);

    int updateByExample(@Param("record") NewsComment record, @Param("example") NewsCommentExample example);

    int updateByPrimaryKeySelective(NewsComment record);

    int updateByPrimaryKeyWithBLOBs(NewsComment record);

    int updateByPrimaryKey(NewsComment record);
}