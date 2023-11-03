package net.fernandosalas.posts.service;

import net.fernandosalas.posts.payload.PostDto;
import net.fernandosalas.posts.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    //List<PostDto> getAllPost();
    PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);
}
