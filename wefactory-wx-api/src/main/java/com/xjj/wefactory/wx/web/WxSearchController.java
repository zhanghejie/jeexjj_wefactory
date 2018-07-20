package com.xjj.wefactory.wx.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xjj.wefactory.business.keyword.entity.KeywordEntity;
import com.xjj.wefactory.customer.search.entity.SearchHistoryEntity;
import com.xjj.wefactory.wx.annotation.LoginUser;
import com.xjj.wefactory.wx.json.WxJson;
import com.xjj.wefactory.wx.service.KeywordService;
import com.xjj.wefactory.wx.service.SearchHistoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/search")
public class WxSearchController {
    @Autowired
    private KeywordService keywordsService;
    @Autowired
    private SearchHistoryService searchHistoryService;

    /**
     * 搜索页面信息
     *
     * 如果用户已登录，则给出用户历史搜索记录。
     *
     * @param userId 用户ID
     * @return 搜索页面信息
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *      {
     *          defaultKeyword: xxx,
     *          historyKeywordList: xxx,
     *          hotKeywordList: xxx
     *      }
     *  }
     *  失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("index")
    public Object index(@LoginUser Long userId) {
        //取出输入框默认的关键词
        KeywordEntity defaultKeyword = keywordsService.getDefault();
        //取出热闹关键词
        List<KeywordEntity> hotKeywordList = keywordsService.findListByProperty("isHot", 1);

        List<SearchHistoryEntity> historyList = null;
        if(userId != null) {
            //取出用户历史关键字
            historyList = searchHistoryService.findListByProperty("customerId",userId);
        }

        Map<String, Object> data = new HashMap();
        data.put("defaultKeyword", defaultKeyword);
        data.put("historyKeywordList", historyList);
        data.put("hotKeywordList", hotKeywordList);
        return WxJson.ok(data);
    }

    /**
     * 关键字提醒
     *
     * 当用户输入关键字一部分时，可以推荐系统中合适的关键字。
     *
     * @param keyword 关键字
     * @return 合适的关键字
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data: xxx
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("helper")
    public Object helper(String keyword) {
        if(keyword == null){
            return WxJson.badArgument();
        }

        Integer page = 1;
        Integer size = 10;
        //按关键字分页查询
        List<KeywordEntity> keywordsList = keywordsService.findListByPropertyLimit("keyword",keyword, (page-1)*size, size);
        String[] keys = new String[keywordsList.size()];
        int index = 0;
        for (KeywordEntity key : keywordsList) {
           keys[index++] = key.getKeyword();
        }
        return WxJson.ok(keys);
    }

    /**
     * 关键字清理
     *
     * 当用户输入关键字一部分时，可以推荐系统中合适的关键字。
     *
     * @param userId 用户ID
     * @return 清理是否成功
     *   成功则 { code: 0, msg: '成功' }
     *   失败则 { code: XXX, msg: XXX }
     */
    @PostMapping("clearhistory")
    public Object clearhistory(@LoginUser Long userId) {
        if(userId == null){
            return WxJson.unlogin();
        }
        searchHistoryService.deleteByProperty("customerId", userId);
        return WxJson.ok();
    }
}
