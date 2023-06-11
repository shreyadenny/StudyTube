package may.internship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ChannelDetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView name,views,description;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_detail);

        sp = getSharedPreferences(ConstantData.PREF,MODE_PRIVATE);

        imageView = findViewById(R.id.channel_image);
        name = findViewById(R.id.channel_name);
        views = findViewById(R.id.viewCount);
        description = findViewById(R.id.channel_description);

        name.setText(sp.getString(ConstantData.CHANNEL_NAME,""));
        views.setText(sp.getString(ConstantData.CHANNEL_VIEWS,""));
        imageView.setImageResource(Integer.parseInt(sp.getString(ConstantData.CHANNEL_IMAGE,"")));
        description.setText(sp.getString(ConstantData.CHANNEL_DESCRIPTION,""));
    }
}