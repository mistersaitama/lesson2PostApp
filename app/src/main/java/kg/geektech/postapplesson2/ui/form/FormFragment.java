package kg.geektech.postapplesson2.ui.form;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import kg.geektech.postapplesson2.App;
import kg.geektech.postapplesson2.R;
import kg.geektech.postapplesson2.data.models.Post;
import kg.geektech.postapplesson2.databinding.FragmentFormBinding;
import kg.geektech.postapplesson2.databinding.FragmentPostBinding;
import kg.geektech.postapplesson2.ui.posts.PostAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FormFragment extends Fragment {

    private FragmentFormBinding binding;
    private static final int groupId = 39;
    private static final int userId = 4;
private Post post;


    public FormFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null){
            String title = getArguments().getString("title");
            String content = getArguments().getString("content");
            binding.etContent.setText(content);
            binding.etTitle.setText(title);
            binding.etContent.setText(content);

        }

            send();

    }

    private void send() {

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = binding.etTitle.getText().toString();
                String content = binding.etContent.getText().toString();
                post = new Post(
                        title,
                        content,
                        userId,
                        groupId
                );
                if (getArguments() == null) {
                    App.api.createPost(post).enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {
                            if (response.isSuccessful() && response.body() != null) {

                                requireActivity().onBackPressed();

                            }
                        }

                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {

                        }
                    });
                } else{

                    int id = requireArguments().getInt("id");
                    App.api.editPostById(id, post).enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {



                            requireActivity().onBackPressed();

                        }

                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {

                        }
                    });
                }
            }


        });
    }
}