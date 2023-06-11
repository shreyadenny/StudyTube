package may.internship;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;

public class EvalActivity extends AppCompatActivity {
    RecyclerView evalRecyclerview;
    ArrayList<SubjectList> evalList;
    String[] evalNameArray = {"Assignment","Test","Project","Presentation"};
    int[] evalImageArray = {R.drawable.assignments,R.drawable.test,R.drawable.project,R.drawable.presentation};
    String[] evalDescriptionArray = {"Written assignments with due dates every weekend. Includes notes from class and important questions to be answered in theory",
            "Multiples Choice Questions with multiple correct answers and Numericals for practical application",
            "Apply theoritical concepts to solve real world problems. Use innovative ideas to create a presentable project",
            "Evaluation of clarity of concepts through live presentation in class. Students are expected to explain concepts in class"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eval);

        evalRecyclerview = findViewById(R.id.eval_recyclerview);
        evalRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        evalRecyclerview.setItemAnimator(new DefaultItemAnimator());
        evalList = new ArrayList<>();
        for(int i=0;i<evalNameArray.length;i++){
            SubjectList list = new SubjectList();
            list.setName(evalNameArray[i]);
            list.setImage(evalImageArray[i]);
            list.setDescription(evalDescriptionArray[i]);
            evalList.add(list);
        }
        SubjectAdapter catAdapter = new SubjectAdapter(EvalActivity.this,evalList);
        evalRecyclerview.setAdapter(catAdapter);
    }
}