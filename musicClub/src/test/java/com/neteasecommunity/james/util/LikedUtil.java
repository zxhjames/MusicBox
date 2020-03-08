package com.neteasecommunity.james.util;

import com.neteasecommunity.james.model.BType;

/**
 * 生成redis的key
 */
public class LikedUtil {
    public static  String getKey(BType bType,Object bId){
        return bType + ":" + bId;
    }

    public static String getReportKey(BType bType){
        BType type = BType.LIKE_ARTICLE_REPORT;
        switch (bType) {
            case LIKE_ARTICLE:
                type = BType.LIKE_ARTICLE_REPORT;
            case LIKE_VIDEO:
                type = BType.LIKE_VIDEO_REPORT;
        }
        return type+"";
    }


}
