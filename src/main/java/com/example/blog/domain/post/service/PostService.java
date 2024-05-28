package com.example.blog.domain.post.service;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.repository.PostRepository;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    /**
     * 게시글을 업로드한다.
     * @param userId 유저아이디
     */
    public Post createPost(Long userId, Post post) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 업로드 불가"));
        post.setUser(user);

        return postRepository.save(post);
    }

    /**
     * 게시글을 얻어온다.
     * @param postId 게시글아이디
     */
    public Post getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음"));
        return post;
    }

    /**
     * 게시글을 삭제한다.
     * @param postId 게시글아이디
     */
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    /**
     * 게시글을 수정한다.
     * @param postId 게시글아이디
     */
    public Post updatePost(Long postId, Post post) {
        Post foundPost = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음"));

        foundPost.update(post.getTitle(), post.getContent());
        return foundPost;
    }

}
