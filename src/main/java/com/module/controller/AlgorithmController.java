package com.module.controller;


import com.module.mapper.ArticleMapper;
import com.module.mapper.CommonMapper;
import com.module.mapper.ScoreinfoMapper;
import com.module.pojo.Article;
import com.module.pojo.Scoreinfo;
import com.module.pojo.Userinfo;
import com.module.util.MyFilleUtil;
import com.module.util.ResultUtil;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AlgorithmController {

    public static int ALGORITHM_TYPE = 1;//临近的用户个数
    final static int NEIGHBORHOOD_NUM = 2;//临近的用户个数
    final static int RECOMMENDER_NUM = 9;//推荐物品的最大个数

    @Autowired   //注入对象
    public ScoreinfoMapper scoreinfoMapper;
    @Autowired
    ArticleMapper articleMapper;

    @Autowired   //注入对象
    public CommonMapper commonMapper;

    //公共查询方法
    @ResponseBody
    @RequestMapping("/pc/selectAction")
    public ResultUtil selectAction(String sql) {
        List<Map> mapList = commonMapper.selectAction(sql);
        return ResultUtil.ok(mapList);
    }

    //公共修改方法
    @ResponseBody
    @RequestMapping("/pc/updateAction")
    public ResultUtil updateAction(String sql) {
        commonMapper.updateAction(sql);
        return ResultUtil.ok("操作成功");
    }


    @RequestMapping("/")
    public String echartsB22() {
        return "redirect:pc/index";
    }


    @RequestMapping("/pc/algorithmList")
    public String algorithmList(HttpSession session, HttpServletRequest request, Model model) throws Exception {
        String filePath = request.getSession().getServletContext().getRealPath("/static") + File.separatorChar + "data.csv";
        System.out.println("filePath = " + filePath);
        Userinfo user = (Userinfo) session.getAttribute("loginUserinfo");
        int myid = user.getId();
        List<Scoreinfo> scoreinfoList = scoreinfoMapper.selectByMap(null);
        String content = "";
        for (Scoreinfo scoreinfo : scoreinfoList) {
            content = content + scoreinfo.getUid() + "," + scoreinfo.getTid() + "," + scoreinfo.getScore() + "\n";
        }
        MyFilleUtil.WriteFile(filePath, content);

        //List<Integer> arr = userCF(filePath,myid);// 基于用户的
        List<Integer> arr = new ArrayList<Integer>();
        if (ALGORITHM_TYPE == 1) {
            arr = itemCF(filePath, myid);// 基于物品的
        } else {
            arr = userCF(filePath, myid);// 基于用户的
        }
        List<Article> datainfoList = new ArrayList<Article>();
        for (Integer tid : arr) {
            Article datainfo = articleMapper.selectArticleById(tid);
            if (null != datainfo) {
                datainfoList.add(datainfo);
            }
        }
        model.addAttribute("datainfoList", datainfoList);
        return "pc/algorithm";
    }

    @ResponseBody
    @RequestMapping("/pc/algorithmSave")
    public ResultUtil algorithmSave(HttpSession session, Scoreinfo scoreinfo, Model model) throws Exception {
        scoreinfoMapper.insert(scoreinfo);
        return ResultUtil.ok("操作成功");
    }

    //基于用户的 CF（User CF） 推荐算法
    public static List<Integer> userCF(String filePath, int myid) throws Exception {
        List<Integer> arrList = new ArrayList<Integer>();
        DataModel model = new FileDataModel(new File(filePath));//数据模型
        UserSimilarity user = new EuclideanDistanceSimilarity(model);//用户相识度算法
        NearestNUserNeighborhood neighbor = new NearestNUserNeighborhood(NEIGHBORHOOD_NUM, user, model);
        //用户近邻算法
        Recommender r = new GenericUserBasedRecommender(model, neighbor, user);//用户推荐算法
        LongPrimitiveIterator iter = model.getUserIDs();///得到用户ID

        while (iter.hasNext()) {
            long uid = iter.nextLong();
            List<RecommendedItem> list = r.recommend(uid, RECOMMENDER_NUM);
            System.out.printf("uid:%s", uid);
            for (RecommendedItem ritem : list) {
                System.out.printf("(%s,%f)", ritem.getItemID(), ritem.getValue());
                if (myid == uid) {
                    arrList.add((int) ritem.getItemID());
                }
            }
            System.out.println();

        }
        return arrList;
    }

    //基于物品的 CF 的基本原理 推荐算法
    public static List<Integer> itemCF(String filePath, int myid) throws Exception {
        List<Integer> arrList = new ArrayList<Integer>();
        DataModel model = new FileDataModel(new File(filePath));//数据模型
        ItemSimilarity item = new EuclideanDistanceSimilarity(model);//用户相识度算法
        Recommender r = new GenericItemBasedRecommender(model, item);//物品推荐算法
        LongPrimitiveIterator iter = model.getUserIDs();
        while (iter.hasNext()) {
            long uid = iter.nextLong();
            List<RecommendedItem> list = r.recommend(uid, RECOMMENDER_NUM);
            System.out.printf("uid:%s", uid);
            for (RecommendedItem ritem : list) {
                System.out.printf("(%s,%f)", ritem.getItemID(), ritem.getValue());
                if (myid == uid) {
                    arrList.add((int) ritem.getItemID());
                }
            }
            System.out.println();
        }
        return arrList;
    }


}
