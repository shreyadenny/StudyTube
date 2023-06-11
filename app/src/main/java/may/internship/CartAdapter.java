package may.internship;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyHolder> {

    Context context;
    String[] catNameArray;
    ArrayList<ChannelList> channelArrayList;
    int[] categoryImageArray;
    SharedPreferences sp;


    public CartAdapter(Context context, ArrayList<ChannelList> channelArrayList) {
        this.context = context;
        this.channelArrayList=channelArrayList;
        sp = context.getSharedPreferences(ConstantData.PREF,Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cart,parent,false);
        return new MyHolder(view);
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView imageView,remove, wishlist, wishlistFill;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.custom_cart_image);
            name = itemView.findViewById(R.id.custom_cart_name);
            remove = itemView.findViewById(R.id.custom_cart_remove);
            wishlist = itemView.findViewById(R.id.custom_cart_wishlist);
            wishlistFill = itemView.findViewById(R.id.custom_cart_wishlist_fill);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.imageView.setImageResource(channelArrayList.get(position).getImage());
        holder.name.setText(channelArrayList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(ConstantData.CHANNEL_NAME,channelArrayList.get(position).getName()).commit();
                sp.edit().putString(ConstantData.CHANNEL_VIEWS,channelArrayList.get(position).getViews()).commit();
                sp.edit().putString(ConstantData.CHANNEL_IMAGE,String.valueOf(channelArrayList.get(position).getImage())).commit();
                sp.edit().putString(ConstantData.CHANNEL_DESCRIPTION,channelArrayList.get(position).getDescription()).commit();
                new CommonMethod(context, ChannelDetailActivity.class);
            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CommonMethod(context,"Remove from Cart");
            }
        });

        holder.wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.wishlist.setVisibility(View.GONE);
                holder.wishlistFill.setVisibility(View.VISIBLE);
            }
        });

        holder.wishlistFill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.wishlist.setVisibility(View.VISIBLE);
                holder.wishlistFill.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return channelArrayList.size();
    }

}