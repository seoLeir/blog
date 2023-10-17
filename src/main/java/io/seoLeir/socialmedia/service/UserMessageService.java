package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.dto.message.MessageChatControllerDto;
import io.seoLeir.socialmedia.dto.page.PageRequestDto;
import io.seoLeir.socialmedia.dto.page.PageResponseDto;
import io.seoLeir.socialmedia.entity.Message;
import io.seoLeir.socialmedia.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMessageService {

    private final MessageRepository userMessageRepository;


    public PageResponseDto<MessageChatControllerDto> getUserDialogues(String username, PageRequestDto pageRequestDto) {
        Pageable pageable = PageRequest.of(pageRequestDto.pageNumber(), pageRequestDto.pageSize(), pageRequestDto.sort());
        Page<Message> messagePage = userMessageRepository.getUserAllDialoguesUsernameOrderBySentDateTime(username, pageable);
        return null;
    }
}
