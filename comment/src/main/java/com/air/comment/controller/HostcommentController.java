package com.air.comment.controller;

import com.air.comment.domain.dto.HostCommentDto;
import com.air.comment.domain.dto.request.CommentRequest;
import com.air.comment.domain.dto.request.HostcommentRequest;
import com.air.comment.domain.entity.HostComment;
import com.air.comment.service.HostcommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/host")
@RequiredArgsConstructor

public class HostcommentController {
    private final HostcommentService hostcommentService;

    @PostMapping
    public void addHostComment(@RequestBody HostcommentRequest request){
        hostcommentService.addHostComment(request);
    }

    @PutMapping("/edit/{userId}")
    public void editHostComment(@PathVariable("userId") int userId, @RequestBody String newHostComment){
    }
    @DeleteMapping("/{userId}")
    public void deleteComment(@PathVariable("commentId") int commentId){
        hostcommentService.deleteComment(commentId);
    }
}
