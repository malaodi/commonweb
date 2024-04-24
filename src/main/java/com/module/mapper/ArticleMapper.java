package com.module.mapper;

import com.module.pojo.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 音乐 数据层
 *
 * @author administrator
 */
import org.springframework.stereotype.Component;
@Component
public interface ArticleMapper {



    /**
     * 数据统计查询
     *
     * @param map
     * @return
     */
    public List<Map> countData(Map map);

    /**
     * 查询音乐信息
     *
     * @param id 音乐ID
     * @return 音乐信息
     */
    public Article selectArticleById(Integer id);

    /**
     * 查询音乐列表
     *
     * @param Article 音乐信息
     * @return 音乐集合
     */
    public List<Article> selectArticleList(Article article);

    /**
     * 查询所有音乐
     *
     * @return 音乐列表
     */
    public List<Article> selectAll(Map map);

    /**
     * 根据推荐算法实现推荐音乐内容
     *
     * @param map
     * @return
     */
    public List<Article> selectTuijian(@Param("listname") String[] listname);

    /**
     * 新增音乐
     *
     * @param Article 音乐信息
     * @return 结果
     */
    public int insertArticle(Article article);

    /**
     * 修改音乐
     *
     * @param article 音乐信息
     * @return 结果
     */
    public int updateArticle(Article article);

    /**
     * 批量修改
     *
     * @param list
     * @return
     */
    public int batchUpdate(List<Article> list);

    /**
     * 删除音乐
     *
     * @param id 音乐ID
     * @return 结果
     */
    public int deleteArticleById(Integer id);

    /**
     * 批量删除音乐
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteArticle(Integer[] ids);

    /**
     * 批量添加
     *
     * @param list
     * @return
     */
    public int batchAdd(List<Article> list);

}
