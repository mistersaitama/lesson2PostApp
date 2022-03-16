package kg.geektech.postapplesson2.data.remote;

import java.util.List;

import kg.geektech.postapplesson2.data.models.Post;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostApi {

    @GET("/posts")
    Call<List<Post>> getPosts();


    @POST("/posts")
    Call<Post> createPost(
            @Body Post post
    );

    @GET("/films/{id}")
    Call<Post> getPostById(
            @Path("id") int id
    );

    @DELETE("/posts/{id}")
    Call<Post> getPostDeleteById(
            @Path("id") int id
    );

    @PUT("/posts/{id}")
    Call<Post> editPostById(
            @Path("id") int id,
            @Body Post post
    );



}
