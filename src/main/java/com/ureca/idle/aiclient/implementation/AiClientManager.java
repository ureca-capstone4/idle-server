package com.ureca.idle.aiclient.implementation;

import com.ureca.idle.aiclient.business.dto.AddBookMbtiReq;
import com.ureca.idle.aiclient.business.dto.AddBookMbtiResp;

public interface AiClientManager {
    AddBookMbtiResp createBookMbti(AddBookMbtiReq req);
}
