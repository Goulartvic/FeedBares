package enterprises.tanheta.feedbares.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import enterprises.tanheta.feedbares.R;
import enterprises.tanheta.feedbares.activities.DetailPubActivity;
import enterprises.tanheta.feedbares.activities.RegisterActivity;
import enterprises.tanheta.feedbares.model.PubModel;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private Context context;
    private Activity activity;
    private List<PubModel> pubs;

    public SearchAdapter(Context context, List<PubModel> pubs, Activity activity) {
        this.context = context;
        this.pubs = pubs;
        this.activity = activity;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.pub_item, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, final int position) {
        holder.pubName.setText(pubs.get(position).getName());
        holder.address.setText(pubs.get(position).getAddress());
        holder.rating.setText(Double.toString(pubs.get(position).getRating()));
        Picasso.with(context).load(pubs.get(position).getPhotoUrl()).placeholder(R.mipmap.ic_launcher_round).into(holder.pubImage);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailPubActivity.class);
                intent.putExtra("pubName", pubs.get(position).getName());
                intent.putExtra("pubAddress", pubs.get(position).getAddress());
                intent.putExtra("pubPhotoUrl", pubs.get(position).getPhotoUrl());
                intent.putExtra("pubRating", pubs.get(position).getRating());
                intent.putExtra("pubId", pubs.get(position).getId());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pubs.size();
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {

        TextView pubName;
        TextView address;
        TextView rating;
        RelativeLayout layout;
        ImageView pubImage;

        public SearchViewHolder(View itemView) {
            super(itemView);
            pubName = itemView.findViewById(R.id.pub_name);
            address = itemView.findViewById(R.id.pub_address);
            rating = itemView.findViewById(R.id.pub_rating);
            pubImage = itemView.findViewById(R.id.pub_image);
            layout = itemView.findViewById(R.id.pub_layout);
        }
    }
}
