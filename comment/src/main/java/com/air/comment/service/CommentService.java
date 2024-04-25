package com.air.comment.service;

import com.air.comment.domain.dto.CommentDto;
import com.air.comment.domain.dto.request.CommentRequest;
import com.air.comment.domain.dto.response.CommentResponse;
import com.air.comment.domain.entity.Comment;
import com.air.comment.domain.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentDto addComment(CommentRequest request){
        Comment comment = Comment.builder()
                .commentId(request.commentId())
                .userId(request.userId())
                .roomId(request.roomId())
                .userName(request.userName())
                .starAvg(request.starAvg())
                .comment(request.comment())
                .commentCreatedAt(request.commentCreatedAt())
                .build();
        Comment save = commentRepository.save(comment);
        return new CommentDto(save.getCommentId(), save.getUserId(),
                save.getRoomId(), save.getUserName(),
                save.getStarAvg(), save.getComment(), save.getCommentCreatedAt());
    }

    public void deleteComment(int id){
        commentRepository.deleteById(id);
    }

    @Transactional
    public void editComment(int id, String newComment){
        Comment comment = commentRepository.findById(id)
                .orElseThrow();
        comment.setComment(newComment);
    }

    public List<CommentResponse> loadRoomComment(int roomId){
        List<Comment> allByRoomId = commentRepository.findAllByRoomId(roomId);
        return allByRoomId.stream().map(CommentResponse::form).toList();
    }

}
