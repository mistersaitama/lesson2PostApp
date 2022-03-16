package kg.geektech.postapplesson2.ui.posts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import kg.geektech.postapplesson2.App;
import kg.geektech.postapplesson2.R;
import kg.geektech.postapplesson2.data.models.OnItemClick;
import kg.geektech.postapplesson2.data.models.Post;
import kg.geektech.postapplesson2.databinding.ItemPostBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Post> posts = new ArrayList<>();
    private OnItemClick listener;


    public void setListener(OnItemClick listener) {
        this.listener = listener;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }
    public Post getItem(int position){

        return posts.get(position);
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostBinding binding = ItemPostBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new PostViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.onBind(posts.get(position));

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void removelist(int position) {
        posts.remove(position);
    }

    public int getId(int position){
        posts.get(position);
        return position;
    }

    protected class PostViewHolder extends RecyclerView.ViewHolder {
        private ItemPostBinding binding;

        public PostViewHolder(@NonNull ItemPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Post post) {

            HashMap<Integer, String> username = new HashMap<>();


            username.put(1, "0");
            username.put(2, "Адилет Туракулов");
            username.put(3, "Сарьтаева Мунира");
            username.put(4, "Бактыбекова Берет");
            username.put(5, "максим Катунин");
            username.put(6, "Актан Азыгалиеве");
            username.put(7, "Арлен Султанбеков");
            username.put(8, "Жочунбаев Темирлан");
            username.put(9, "Азим Овуржаное");
            username.put(10, "мужахиддин Есенкулов");
            username.put(11, "Аскар Ибраимов");
            username.put(12, "Эльяз Дегенбаев");
            username.put(13, "Болот Муратов");
            username.put(14, "Айдар Ажибраимов");
            username.put(15, "Рамис Абасов");
            username.put(16, "Нурсултан Каныбеков");
            username.put(17, "Дастан Нурланбеков");

            ArrayList<String> values = new ArrayList<>(username.values());





            binding.tvUserId.setText(values.get(post.getUserId()));
            binding.tvTitle.setText(post.getTitle());
            binding.tvContent.setText(post.getContent());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(post, post.getId());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle(R.string.title_alert);
                    builder.setMessage(R.string.title_message);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            App.api.getPostDeleteById(post.getId()).enqueue(new Callback<Post>() {
                                @Override
                                public void onResponse(Call<Post> call, Response<Post> response) {
                                    posts.remove(getAdapterPosition());
                                    notifyDataSetChanged();
                                }

                                @Override
                                public void onFailure(Call<Post> call, Throwable t) {

                                }
                            });

                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    listener.onLongClick(post, post.getId());
                    return true;
                }
            });
        }
    }
}