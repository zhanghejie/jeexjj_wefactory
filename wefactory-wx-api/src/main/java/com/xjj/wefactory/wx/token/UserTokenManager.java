package com.xjj.wefactory.wx.token;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.xjj.wefactory.wx.util.CharUtil;

public class UserTokenManager {
    private static Map<String, UserToken> tokenMap = new HashMap<>();
    private static Map<Long, UserToken> idMap = new HashMap<>();

    public static Long getUserId(String token) {


        UserToken userToken = tokenMap.get(token);
        if(userToken == null){
            return null;
        }

        if(userToken.getExpireTime().isBefore(LocalDateTime.now())){
            tokenMap.remove(token);
            idMap.remove(userToken.getUserId());
            return null;
        }

        return userToken.getUserId();
    }
    

    public static UserToken generateToken(Long id){
        UserToken userToken = null;

//        userToken = idMap.get(id);
//        if(userToken != null) {
//            tokenMap.remove(userToken.getToken());
//            idMap.remove(id);
//        }

        String token = CharUtil.getRandomString(32);
        while (tokenMap.containsKey(token)) {
            token = CharUtil.getRandomString(32);
        }

        LocalDateTime update = LocalDateTime.now();
        LocalDateTime expire = update.plusDays(1);

        userToken = new UserToken();
        userToken.setToken(token);
        userToken.setUpdateTime(update);
        userToken.setExpireTime(expire);
        userToken.setUserId(id);
        tokenMap.put(token, userToken);
        idMap.put(id, userToken);

        return userToken;
    }
}
