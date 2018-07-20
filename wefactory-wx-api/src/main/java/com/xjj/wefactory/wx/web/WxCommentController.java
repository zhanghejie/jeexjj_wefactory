package com.xjj.wefactory.wx.web;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xjj.wefactory.customer.comment.entity.CommentEntity;
import com.xjj.wefactory.wx.annotation.LoginUser;
import com.xjj.wefactory.wx.json.WxJson;
import com.xjj.wefactory.wx.service.BuyerService;
import com.xjj.wefactory.wx.service.CommentService;
import com.xjj.wefactory.wx.service.UserInfoService;
import com.xjj.wefactory.wx.token.UserInfo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/comment")
public class WxCommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 发表评论
     *
     * TODO, 对于评论，应该检测用户是否有权限评论。
     * 1. 如果用户没有购买过商品，则不能发表对该商品的评论
     * 2. 如果用户购买商品后规定时间内没有评论，则过期也不能再评论
     *
     * @param userId 用户ID
     * @param comment 评论内容
     * @return 发表评论操作结果
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data: xxx
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @PostMapping("post")
    public Object post(@LoginUser Long userId, @RequestBody CommentEntity comment) {
        if(userId == null){
            return WxJson.unlogin();
        }
        if(comment == null){
            return WxJson.badArgument();
        }

        comment.setAddTime(new Date());
        comment.setCustomerId(userId);
        commentService.save(comment);
        return WxJson.ok(comment);
    }

    /**
     * 评论数量
     *
     * @param type 类型ID。 如果是0，则查询商品评论；如果是1，则查询专题评论。
     * @param valueId 商品或专题ID。如果type是0，则是商品ID；如果type是1，则是专题ID。
     * @return 评论数量
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *          {
     *              allCount: xxx,
     *              hasPicCount: xxx
     *          }
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("count")
    public Object count(Integer type, Integer valueId) {
        int allCount = commentService.count(type, valueId, 0);
        int hasPicCount = commentService.count(type, valueId, 1);
        Map<String, Object> data = new HashMap();
        data.put("allCount", allCount);
        data.put("hasPicCount", hasPicCount);
        return WxJson.ok(data);
    }

    /**
     * 评论列表
     *
     * @param type 类型ID。 如果是0，则查询商品评论；如果是1，则查询专题评论。
     * @param valueId 商品或专题ID。如果type是0，则是商品ID；如果type是1，则是专题ID。
     * @param showType 显示类型。如果是0，则查询全部；如果是1，则查询有图片的评论。
     * @param page 分页页数
     * @param size 分页大小
     * @return 评论列表
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *          {
     *              data: xxx,
     *              count: xxx，
     *              currentPage: xxx
     *          }
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("list")
    public Object list(Integer type, Integer valueId, Integer showType,
                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if(null==type || null==valueId || null==showType){
            return WxJson.badArgument();
        }

        List<CommentEntity> commentList = commentService.query(type, valueId, showType, page, size);
        int count = commentService.count(type, valueId, showType);

        List<Map<String, Object>> commentVoList = new ArrayList<>(commentList.size());
        for(CommentEntity comment : commentList){
            Map<String, Object> commentVo = new HashMap<>();
            UserInfo userInfo = userInfoService.getInfo(comment.getCustomerId());
            commentVo.put("userInfo", userInfo);
            commentVo.put("addTime", comment.getAddTime());
            commentVo.put("content",comment.getContent());
            commentVo.put("picList", comment.getPicUrls());

            commentVoList.add(commentVo);
        }
        Map<String, Object> data = new HashMap();
        data.put("data", commentVoList);
        data.put("count", count);
        data.put("currentPage", page);
        return WxJson.ok(data);
    }
}