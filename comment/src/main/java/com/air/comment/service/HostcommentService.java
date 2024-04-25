package com.air.comment.service;

import com.air.comment.domain.dto.request.CommentRequest;
import com.air.comment.domain.dto.request.HostcommentRequest;
import com.air.comment.domain.entity.HostComment;
import com.air.comment.domain.repository.HostcommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HostcommentService {
    private final HostcommentRepository hostcommentRepository;

    public void addHostComment(HostcommentRequest request){
        HostComment hostComment = HostComment.builder()
                .roomId(request.roomId())
                .commentStar(request.commentStar())
                .commentContent(request.commentContent())
                .build();
        HostComment save = hostcommentRepository.save(hostComment);
    }

    @Transactional
    public void editHostComment(int userid, String newHostComment){
        HostComment hostComment = hostcommentRepository.findById(userid).orElseThrow();
        hostComment.setHostcomment(newHostComment);
    }

    public void deleteComment(int commentId){
        hostcommentRepository.deleteById(commentId);
    }

}
