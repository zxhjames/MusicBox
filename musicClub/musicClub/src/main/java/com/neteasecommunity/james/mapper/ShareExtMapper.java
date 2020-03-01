package com.neteasecommunity.james.mapper;

import com.neteasecommunity.james.model.Share;
import com.neteasecommunity.james.model.ShareExample;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ShareExtMapper {
    List<Share> selectAllByExampleWithDate(ShareExample shareExample);
}
