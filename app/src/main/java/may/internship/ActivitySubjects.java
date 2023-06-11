package may.internship;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;

public class ActivitySubjects extends AppCompatActivity {
    RecyclerView subjectRecyclerview;
    String[] subjectsNameArray = {"Maths","Chemistry","Biology","English","Journalism","Fashion"};
    int[] subjectsImageArray = {R.drawable.math,R.drawable.chemistry,R.drawable.biology,R.drawable.english, R.drawable.journalism,R.drawable.fashion};
    String[] subjectsDescriptionArray = {"Mathematics is an area of knowledge that includes the topics of numbers, formulas and related structures, shapes and the spaces in which they are contained, and quantities and their changes. These topics are represented in modern mathematics with the major subdisciplines of number theory,[1] algebra,[2] geometry,[1] and analysis,[3][4] respectively. ",
            "Chemistry is the scientific study of the properties and behavior of matter.[1] It is a physical science under natural sciences that covers the elements that make up matter to the compounds made of atoms, molecules and ions: their composition, structure, properties, behavior and the changes they undergo during a reaction with other substances.[2][3][4][5] Chemistry also addresses the nature of chemical bonds in chemical compounds.",
            "Biology is the scientific study of life. It is a natural science with a broad scope but has several unifying themes that tie it together as a single, coherent field. For instance, all organisms are made up of cells that process hereditary information encoded in genes, which can be transmitted to future generations.",
            "English is a West Germanic language in the Indo-European language family, with its earliest forms spoken by the inhabitants of early medieval England. It is named after the Angles, one of the ancient Germanic peoples that migrated to the island of Great Britain.",
            "journalism, the collection, preparation, and distribution of news and related commentary and feature materials through such print and electronic media as newspapers, magazines, books, blogs, webcasts, podcasts, social networking and social media sites, and e-mail as well as through radio, motion pictures.",
            "The fashion industry consists of four levels: the production of raw materials, principally fibres and textiles but also leather and fur; the production of fashion goods by designers, manufacturers, contractors, and others; retail sales; and various forms of advertising and promotion."};
    ArrayList<SubjectList> subjectListArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);

        subjectRecyclerview = findViewById(R.id.subject_recyclerview);
        subjectRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        subjectRecyclerview.setItemAnimator(new DefaultItemAnimator());
        subjectListArrayList = new ArrayList<>();
        for(int i=0;i<subjectsNameArray.length;i++){
            SubjectList list = new SubjectList();
            list.setName(subjectsNameArray[i]);
            list.setImage(subjectsImageArray[i]);
            list.setDescription(subjectsDescriptionArray[i]);
            subjectListArrayList.add(list);
        }
        SubjectAdapter catAdapter = new SubjectAdapter(ActivitySubjects.this,subjectListArrayList);
        subjectRecyclerview.setAdapter(catAdapter);
    }
}