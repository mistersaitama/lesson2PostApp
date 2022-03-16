package kg.geektech.postapplesson2.data.models;

public interface OnItemClick {
    void onClick(Post post, int id);
    void onLongClick(Post post, int id);
}
