package net.fernandosalas.posts.service.implementation;

import lombok.AllArgsConstructor;
import net.fernandosalas.posts.entity.Post;
import net.fernandosalas.posts.payload.PostDto;
import net.fernandosalas.posts.payload.PostResponse;
import net.fernandosalas.posts.repository.PostRepository;
import net.fernandosalas.posts.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImplementation implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        Post newPost = postRepository.save(post);
        return modelMapper.map(newPost, PostDto.class);
    }

//    @Override
//    public List<PostDto> getAllPost() {
//        List<Post> postList = postRepository.findAll();
//        return postList.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
//    }

    @Override
    public PostResponse getAllPost(int pageNo, int pageSize) {
        // Create a Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        // Retrieve a page of posts
        Page<Post> postList = postRepository.findAll(pageable);
        // Get content for page object
        List<Post> listOfPost = postList.getContent();

        List<PostDto> content = listOfPost.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(postList.getNumber());
        postResponse.setPageSize(postList.getSize());
        postResponse.setTotalElements(postList.getTotalElements());
        postResponse.setTotalPages(postList.getTotalPages());
        postResponse.setLast(postList.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).get();
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).get();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatePost = postRepository.save(post);
        return modelMapper.map(updatePost, PostDto.class);
    }

    @Override
    public void deletePostById(long id) {
        postRepository.deleteById(id);
    }
}
