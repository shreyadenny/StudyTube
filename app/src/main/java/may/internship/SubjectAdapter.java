package may.internship;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.MyHolder>{
    Context context;
    String[] catNameArray;
    ArrayList<SubjectList> subjectArrayList;
    int[] categoryImageArray;
    SharedPreferences sp;

    public SubjectAdapter(Context context, ArrayList<SubjectList> subjectArrayList) {
        this.context = context;
        this.subjectArrayList=subjectArrayList;
        sp = context.getSharedPreferences(ConstantData.PREF,Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public SubjectAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_category,parent,false);
        return new SubjectAdapter.MyHolder(view);
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        CircleImageView imageView;
        TextView name;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.custom_category_image);
            name = itemView.findViewById(R.id.custom_category_name);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectAdapter.MyHolder holder, int position) {
        holder.imageView.setImageResource(subjectArrayList.get(position).getImage());
        holder.name.setText(subjectArrayList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(ConstantData.CHANNEL_NAME,subjectArrayList.get(position).getName()).commit();
                sp.edit().putString(ConstantData.CHANNEL_IMAGE,String.valueOf(subjectArrayList.get(position).getImage())).commit();
                sp.edit().putString(ConstantData.CHANNEL_DESCRIPTION,subjectArrayList.get(position).getDescription()).commit();
                new CommonMethod(context, ChannelDetailActivity.class);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjectArrayList.size();
    }


}
