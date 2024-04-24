package com.module.mapper;

import com.module.pojo.Sorttype;
import java.util.List;
import java.util.Map;

/**
 * 标签 数据层
 *
 * @author administrator
 *
 */
import org.springframework.stereotype.Component;
@Component
public interface SorttypeMapper
{

	/**
     * 查询标签信息
     *
     * @param id 标签ID
     * @return 标签信息
     */
	public Sorttype selectSorttypeById(Integer id);

	/**
     * 查询标签列表
     *
     * @param Sorttype 标签信息
     * @return 标签集合
     */
	public List<Sorttype> selectSorttypeList(Sorttype sorttype);

	/**
     * 查询所有标签
     *
     * @return 标签列表
     */
    public List<Sorttype> selectAll(Map map);
	/**
     * 新增标签
     *
     * @param Sorttype 标签信息
     * @return 结果
     */
	public int insertSorttype(Sorttype sorttype);

	/**
     * 修改标签
     *
     * @param sorttype 标签信息
     * @return 结果
     */
	public int updateSorttype(Sorttype sorttype);
	 /**
     * 批量修改
     * @param list
     * @return
     */
   public int batchUpdate(List<Sorttype> list);
	/**
     * 删除标签
     *
     * @param id 标签ID
     * @return 结果
     */
	public int deleteSorttypeById(Integer id);

	/**
     * 批量删除标签
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int batchDeleteSorttype(Integer[] ids);

    /**
     * 批量添加
     * @param list
     * @return
     */
   public int batchAdd(List<Sorttype> list);

}
