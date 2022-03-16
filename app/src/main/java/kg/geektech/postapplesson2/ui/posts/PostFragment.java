package kg.geektech.postapplesson2.ui.posts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import kg.geektech.postapplesson2.App;
import kg.geektech.postapplesson2.R;
import kg.geektech.postapplesson2.base.BaseFragment;
import kg.geektech.postapplesson2.data.models.OnItemClick;
import kg.geektech.postapplesson2.data.models.Post;
import kg.geektech.postapplesson2.databinding.FragmentPostBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostFragment extends Fragment {
    private PostAdapter adapter;
    private FragmentPostBinding binding;
    private NavController controller;

    public PostFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostBinding.inflate(inflater, container, false);
        adapter = new PostAdapter();
        controller = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recycler.setAdapter(adapter);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.action_postFragment_to_formFragment);
            }
        });
        App.api.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setPosts(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
        adapter.setListener(new OnItemClick() {
            @Override
            public void onClick(Post post, int id) {

                App.api.getPostById(id).enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                        Bundle bundle = new Bundle();
                        bundle.putString("title", post.getTitle());
                        bundle.putString("content", post.getContent());
                        bundle.putInt("id", post.getId());
                        navController.navigate(R.id.formFragment, bundle);

                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onLongClick(Post post, int id) {


                setAlert(id);
                Log.d("Ray", "asdasd");

            }
        });
    }

    private void setAlert(int position) {


    }

    private void openFragment(Post posts) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        Bundle bundle = new Bundle();
        bundle.putString("post", posts.getTitle());
        navController.navigate(R.id.formFragment, bundle);
    }

}