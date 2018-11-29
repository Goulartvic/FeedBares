package enterprises.tanheta.feedbares.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import enterprises.tanheta.feedbares.R;
import enterprises.tanheta.feedbares.model.CommentModel;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private Context context;
    private List<CommentModel> comments;

    public CommentAdapter(Context context, List<CommentModel> comments) {
        this.context = context;
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.comment_item, parent, false);
        return new CommentAdapter.CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, final int position) {
        holder.comment.setText(comments.get(position).getCommentary());
        holder.userName.setText(comments.get(position).getUserName());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {

        TextView comment;
        TextView userName;

        public CommentViewHolder(View itemView) {
            super(itemView);
            comment = itemView.findViewById(R.id.comment);
            userName = itemView.findViewById(R.id.user_comment);
        }
    }
}
