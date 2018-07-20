package com.xjj.wefactory.wx.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xjj.wefactory.business.topic.entity.TopicEntity;
import com.xjj.wefactory.wx.json.WxJson;
import com.xjj.wefactory.wx.service.TopicService;

@RestController
@RequestMapping("/wx/topic")
public class WxTopicController {
    @Autowired
    private TopicService topicService;

    /**
     * 专题列表
     *
     * @param page 分页页数
     * @param size 分页大小
     * @return 专题列表
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *          {
     *              data: xxx,
     *              count: xxx
     *          }
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("list")
    public Object list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        List<TopicEntity> topicList = topicService.findListLimit(null,(page-1)*size, size);
        int total = topicService.getCount(null);
        Map<String, Object> data = new HashMap();
        data.put("data", topicList);
        data.put("count", total);
        return WxJson.ok(data);
    }

    /**
     * 专题详情
     *
     * @param id 专题ID
     * @return 专题详情
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data: xxx
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("detail")
    public Object detail(Long id) {
        if(id == null){
            return WxJson.badArgument();
        }

        TopicEntity topic = topicService.getById(id);
        return WxJson.ok(topic);
    }

    /**
     * 相关专题
     *
     * @param id 专题ID
     * @return 相关专题
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data: xxx
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("related")
    public Object related(Long id) {
        if(id == null){
            return WxJson.badArgument();
        }

        List<TopicEntity> topicRelatedList = topicService.findRelatedList(id, 0, 4);
        return WxJson.ok(topicRelatedList);
    }
}