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


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyHolder> {

    Context context;
    String[] catNameArray, cartPriceArray;
    ArrayList<CartList> channelArrayList;
    SQLiteDatabase db;
    int[] categoryImageArray;
    SharedPreferences sp;


    public CartAdapter(Context context, ArrayList<CartList> channelArrayList) {
        this.context = context;
        this.channelArrayList=channelArrayList;
        sp = context.getSharedPreferences(ConstantData.PREF,Context.MODE_PRIVATE);
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cart,parent,false);
        return new MyHolder(view);
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView name, price,qty;
        ImageView imageView,remove, wishlist, wishlistFill, add;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.custom_cart_image);
            price=itemView.findViewById(R.id.custom_cart_price);
            qty = itemView.findViewById(R.id.custom_cart_qty);
            add = itemView.findViewById(R.id.custom_product_cart);
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
        holder.price.setText(ConstantData.PRICE_SYMBOL + " " + channelArrayList.get(position).getPrice());
        holder.qty.setText("Qty : "+channelArrayList.get(position).getQty());

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
                CartFragment.iTotalAmount=CartFragment.iTotalAmount - Integer.parseInt(channelArrayList.get(position).getPrice());
                CartFragment.totalAmount.setText("Total : " +ConstantData.PRICE_SYMBOL+CartFragment.iTotalAmount);

                if(CartFragment.iTotalAmount>0){
                    CartFragment.totalAmount.setVisibility(View.VISIBLE);
                    CartFragment.checkout.setVisibility(View.VISIBLE);
                }
                else{
                    CartFragment.totalAmount.setVisibility(View.GONE);
                    CartFragment.checkout.setVisibility(View.GONE);
                }
                channelArrayList.remove(position);
                new CommonMethod(context,"Remove from Cart");
                notifyDataSetChanged();
            }
        });

        holder.wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.wishlist.setVisibility(View.GONE);
                holder.wishlistFill.setVisibility(View.VISIBLE);
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
        holder.wishlistFill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.wishlist.setVisibility(View.VISIBLE);
                holder.wishlistFill.setVisibility(View.GONE);
            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CommonMethod(context,"Add To Cart");
                int iQty = 1;
                int productPrice = Integer.parseInt(channelArrayList.get(position).getPrice());
                int iTotalPrice = iQty * productPrice;
                int views=Integer.parseInt(channelArrayList.get(position).getViews());
                String cont =sp.getString(ConstantData.CONTACT,"");
                String insertQuery = "INSERT INTO CART VALUES('"+cont+"','0','"+channelArrayList.get(position).getName()+"','"+iQty+"','"+iTotalPrice+"','"+views+"','"+channelArrayList.get(position).getImage()+"')";
                db.execSQL(insertQuery);
                holder.add.setVisibility(View.GONE);
                holder.remove.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return channelArrayList.size();
    }

}