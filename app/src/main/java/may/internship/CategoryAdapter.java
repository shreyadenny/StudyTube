package may.internship;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyHolder> {

    Context context;
    String[] catNameArray;
    ArrayList<CartList> channelArrayList;
    int[] categoryImageArray;
    SharedPreferences sp;
    SQLiteDatabase db;


    public CategoryAdapter(Context context, ArrayList<CartList> channelArrayList) {
        this.context = context;
        this.channelArrayList=channelArrayList;
        sp = context.getSharedPreferences(ConstantData.PREF, MODE_PRIVATE);

        db = context.openOrCreateDatabase("MayInternship",MODE_PRIVATE,null);
        String tableQuery = "CREATE TABLE IF NOT EXISTS RECORD(NAME VARCHAR(100),EMAIL VARCHAR(100),CONTACT BIGINT(10),PASSWORD VARCHAR(15),DOB VARCHAR(10),GENDER VARCHAR(6),CITY VARCHAR(50))";
        db.execSQL(tableQuery);

        String wishlistTableQuery = "CREATE TABLE IF NOT EXISTS WISHLIST(CONTACT INT(10),PRODUCTNAME VARCHAR(100))";
        db.execSQL(wishlistTableQuery);

        String cartTableQuery = "CREATE TABLE IF NOT EXISTS CART(CONTACT VARCHAR(100),ORDERID INT(10),PRODUCTNAME VARCHAR(100),QTY INT(10),PRODUCTPRICE BIGINT(100),VIEWS BIGINT(100),PRODUCTIMAGE BIGINT(100))";
        db.execSQL(cartTableQuery);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_category,parent,false);
        return new MyHolder(view);
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        CircleImageView imageView;
        TextView name, price;
        ImageView add, removeCart, wishlist, wishlistFill;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.custom_category_image);
            name = itemView.findViewById(R.id.custom_category_name);
            add = itemView.findViewById(R.id.custom_product_cart);
            removeCart = itemView.findViewById(R.id.custom_product_cart_remove);
            wishlist = itemView.findViewById(R.id.custom_product_wishlist);
            wishlistFill = itemView.findViewById(R.id.custom_product_wishlist_fill);
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

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CommonMethod(context,"Add To Cart");
                int iQty = 1;
                int productPrice = Integer.parseInt(channelArrayList.get(position).getPrice());
                int iTotalPrice = iQty * productPrice;
                int views=Integer.parseInt(channelArrayList.get(position).getViews());
                String cont =sp.getString(ConstantData.CONTACT,"");
                String insertQuery = "INSERT INTO CART VALUES('"+cont+"','0','"+channelArrayList.get(position).getName()+"','"+iQty+"','"+iTotalPrice+"','"+views+"','"+channelArrayList.get(position).getImage()+"')";
                db.execSQL(insertQuery);
                holder.add.setVisibility(View.GONE);
                holder.removeCart.setVisibility(View.VISIBLE);
            }
        });
        holder.removeCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String removeQuery = "DELETE FROM CART WHERE CONTACT='"+sp.getString(ConstantData.CONTACT,"")+"' AND PRODUCTNAME='"+channelArrayList.get(position).getName()+"' AND ORDERID='0'";
                db.execSQL(removeQuery);
                holder.add.setVisibility(View.VISIBLE);
                holder.removeCart.setVisibility(View.GONE);
            }
        });

        if(channelArrayList.get(position).isWishlist()){
            holder.wishlist.setVisibility(View.GONE);
            holder.wishlistFill.setVisibility(View.VISIBLE);
        }
        else{
            holder.wishlist.setVisibility(View.VISIBLE);
            holder.wishlistFill.setVisibility(View.GONE);
        }

        holder.wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String insertQuery = "INSERT INTO WISHLIST VALUES('"+sp.getString(ConstantData.CONTACT,"")+"','"+channelArrayList.get(position).getName()+"')";
                db.execSQL(insertQuery);
                holder.wishlist.setVisibility(View.GONE);
                holder.wishlistFill.setVisibility(View.VISIBLE);
            }
        });

        holder.wishlistFill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String removeQuery = "DELETE FROM WISHLIST WHERE CONTACT='"+sp.getString(ConstantData.CONTACT,"")+"' AND PRODUCTNAME='"+channelArrayList.get(position).getName()+"'";
                db.execSQL(removeQuery);
                holder.wishlist.setVisibility(View.VISIBLE);
                holder.wishlistFill.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(channelArrayList!=null) return channelArrayList.size();
        return 0;
    }

}