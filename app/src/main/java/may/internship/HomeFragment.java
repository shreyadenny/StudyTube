package may.internship;

import static android.content.Context.MODE_PRIVATE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    TextView name, channelsViewAll, subjectsViewAll, evaluationViewAll;
    SharedPreferences sp;
    ArrayList<ChannelList> channelListArrayList;
    ArrayList<SubjectList> subjectListArrayList;
    ArrayList<SubjectList> evalList;

    RecyclerView categoryRecyclerview;

    String[] categoryNameArray = {"Code for a cause","Engineering in 5 min","Electronics at your tips","Industry","Electrical concepts","Civil engineering"};
    int[] categoryImageArray = {R.drawable.code,R.drawable.mechanical,R.drawable.electronics,R.drawable.industry,R.drawable.electrical,R.drawable.civil};
    String[] views = {"23433", "354657", "56767", "3432", "1232", "34222"};
    String[] channelDescriptionArray = {"Code for Cause is an initiative started by a group of like-minded people working for a similar cause. Our primary focus is to provide guidance and mentorship to students. Not only for those who lack on-campus opportunities but also for those who lack awareness about the possibilities in the field. We provide a hands-on learning experience and keep students informed about the latest trends in technology, opportunities so that they can keep up with the fast-paced digital world via following a pi-shape learning pattern ",
            "Myself Shridhar Rajendra Mankar a Engineer l YouTuber l Educational Blogger l Educator l Podcaster. \n" + "My Aim- To Make Engineering Students Life EASY.\n" + "\n" + "On 5 Minutes Engineering you can find EASIEST explanations for all below mentioned subjects in DESI HINDI", "Hi, I am Basumati Rawal. I am an electronics practical holder teacher. I am going to teach from basic to advanced level of practical electronics. Stay Tuned.", "TECHNOLOGY IN SHORT focused on helping people acquire the skills and technical knowledge they need to thrive in the digital world.\n" + "We  explain all latest trending technologies in short and simple language.",
            "Covering all sectors, The Manufacturer, is an essential resource for every boardroom, senior management, delivering thought leadership articles, regulatory updates and best practice case studies.\n" +                    "\n" + "With regular events hosted around the country The Manufacturer brings extensive industry knowledge to you in person as well as online. For more information about our events and webinars go to our website.",
            "This channel has been created to enable the students to face competition exams and university level exams.\n" + "\n" + "In today's competition world every candidates/student's has the ability to find out success, good quality of knowledge and right guidelines to achieve his/her goals in life.\n" + "\n" +
            "Our channel provide knowledge of all subjects in electrical field in hindi. We have tried to present all the topics in very simple and lucid way. \n" + "\n" + "I would like to request my esteemed viewers to kindly send me their valuable suggestions for improvement of this channel and to notify me of any error/mistakes they may come across while going through this channel.\n" + "\n" + "Please like, subscribe and share my channel for more updates.\n" + "Contact\n" + " me:-lcitgauravjaiswal@gmail.com", "Swati Sen \n" + "Civil Engineer,  Civil Contractor, 11+ Years Experience\n" + "\n" + "This channel will provide videos related to Civil Engineering.\n" + "      \n" + "      # Civil Engineering Drawings\n" + "      # AutoCAD Designs\n" + "      # Detailed Building Drawings\n" + "      # Foundations\n" + "      # AutoCAD Civil 3D\n" + "      # Concrete Designs\n" + "      # Steel Designs\n" + "      # Structural Analysis\n" + "      # Building Estimations\n" + "      # Water Resource  & Irrigation Engineering\n" + "      # Quantity Survey \n" + "      # Land survey\n" + "      # Environmental Engineering\n" + "      # Public Health Engineering\n" + "      # Soil Mechanics\n" + "      # Concrete Technology\n" + "      # Civil Infrastructure Site oriented knowledge.\n" + "      # Transportation Engineering\n" + "\n" + "These videos will help to learn and develop construction skills. \n" + "It will give practical construction based site information.",
    "My Name is Engr Hedaetullah (B.Sc in Civil Engineering) and my other partner NJ Disha (B.Sc in Civil Engineering-Running)\n" +
            "In this Channel, We spread our Civil Engineering Knowledge all over the world to Civil Engineering Students.\n" +
            "\n" +
            "Here I teach the following Topics:\n" +
            "1. Here actually I focus to teach different types of Software that are really essential for Civil Engineers. Mainly you can learn here structural analysis and design Field perfectly.\n" +
            "2. Secondly I focus on Civil Engineering Basic knowledge that is really essential for Civil Engineers.\n" +
            "3. Also, I provide subject-wise Lectures.\n" +
            " \n" +
            "So, If you are a civil engineer then stay with us and support us so that we can do something better in our field."};

    String[] subjectsNameArray = {"Maths","Chemistry","Biology","English","Journalism","Fashion"};
    int[] subjectsImageArray = {R.drawable.math,R.drawable.chemistry,R.drawable.biology,R.drawable.english, R.drawable.journalism,R.drawable.fashion};
    String[] subjectsDescriptionArray = {"Mathematics is an area of knowledge that includes the topics of numbers, formulas and related structures, shapes and the spaces in which they are contained, and quantities and their changes. These topics are represented in modern mathematics with the major subdisciplines of number theory,[1] algebra,[2] geometry,[1] and analysis,[3][4] respectively. ",
            "Chemistry is the scientific study of the properties and behavior of matter.[1] It is a physical science under natural sciences that covers the elements that make up matter to the compounds made of atoms, molecules and ions: their composition, structure, properties, behavior and the changes they undergo during a reaction with other substances.[2][3][4][5] Chemistry also addresses the nature of chemical bonds in chemical compounds.",
            "Biology is the scientific study of life. It is a natural science with a broad scope but has several unifying themes that tie it together as a single, coherent field. For instance, all organisms are made up of cells that process hereditary information encoded in genes, which can be transmitted to future generations.",
            "English is a West Germanic language in the Indo-European language family, with its earliest forms spoken by the inhabitants of early medieval England. It is named after the Angles, one of the ancient Germanic peoples that migrated to the island of Great Britain.",
            "journalism, the collection, preparation, and distribution of news and related commentary and feature materials through such print and electronic media as newspapers, magazines, books, blogs, webcasts, podcasts, social networking and social media sites, and e-mail as well as through radio, motion pictures.",
            "The fashion industry consists of four levels: the production of raw materials, principally fibres and textiles but also leather and fur; the production of fashion goods by designers, manufacturers, contractors, and others; retail sales; and various forms of advertising and promotion."};

    String[] evalNameArray = {"Assignment","Test","Project","Presentation"};
    int[] evalImageArray = {R.drawable.assignments,R.drawable.test,R.drawable.project,R.drawable.presentation};
    String[] evalDescriptionArray = {"Written assignments with due dates every weekend. Includes notes from class and important questions to be answered in theory",
            "Multiples Choice Questions with multiple correct answers and Numericals for practical application",
            "Apply theoritical concepts to solve real world problems. Use innovative ideas to create a presentable project",
            "Evaluation of clarity of concepts through live presentation in class. Students are expected to explain concepts in class"};


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_home, container, false);
        sp = getActivity().getSharedPreferences(ConstantData.PREF,MODE_PRIVATE);

        name = view.findViewById(R.id.home_name);

//        Bundle bundle = getIntent().getExtras();
//        name.setText("Welcome "+bundle.getString("NAME"));
        name.setText("Welcome "+sp.getString(ConstantData.NAME,""));

        //channels
        channelsViewAll = view.findViewById(R.id.channel_viewall);
        channelsViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CommonMethod(getActivity(),ChannelsActivity.class);
            }
        });

        categoryRecyclerview = view.findViewById(R.id.home_category);
        categoryRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        categoryRecyclerview.setItemAnimator(new DefaultItemAnimator());

        channelListArrayList = new ArrayList<>();
        for(int i=0;i<categoryNameArray.length;i++){
            ChannelList list = new ChannelList();
            list.setName(categoryNameArray[i]);
            list.setImage(categoryImageArray[i]);
            list.setViews(views[i]);
            list.setDescription(channelDescriptionArray[i]);
            channelListArrayList.add(list);
        }
//        CategoryAdapter catAdapter = new CategoryAdapter(HomeActivity.this,categoryNameArray,categoryImageArray);
//        categoryRecyclerview.setAdapter(catAdapter);
        CategoryAdapter catAdapter = new CategoryAdapter(getActivity(),channelListArrayList);
        categoryRecyclerview.setAdapter(catAdapter);

       //subjects
        subjectsViewAll = view.findViewById(R.id.subjects_view_all);
        subjectsViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CommonMethod(getActivity(),ActivitySubjects.class);
            }
        });
        categoryRecyclerview = view.findViewById(R.id.subjects_category);
        categoryRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        categoryRecyclerview.setItemAnimator(new DefaultItemAnimator());
        subjectListArrayList = new ArrayList<>();
        for(int i=0;i<subjectsNameArray.length;i++){
            SubjectList list = new SubjectList();
            list.setName(subjectsNameArray[i]);
            list.setImage(subjectsImageArray[i]);
            list.setDescription(subjectsDescriptionArray[i]);
            subjectListArrayList.add(list);
        }
        SubjectAdapter catAdapter2 = new SubjectAdapter(getActivity(),subjectListArrayList);
        categoryRecyclerview.setAdapter(catAdapter2);

        //evaluation
        evaluationViewAll = view.findViewById(R.id.evaluation_viewall);
        evaluationViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CommonMethod(getActivity(),EvalActivity.class);
            }
        });
        categoryRecyclerview = view.findViewById(R.id.eval_category);
        categoryRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        categoryRecyclerview.setItemAnimator(new DefaultItemAnimator());

        evalList = new ArrayList<>();
        for(int i=0;i<evalNameArray.length;i++){
            SubjectList list = new SubjectList();
            list.setName(evalNameArray[i]);
            list.setImage(evalImageArray[i]);
            list.setDescription(evalDescriptionArray[i]);
            evalList.add(list);
        }
        SubjectAdapter catAdapter3 = new SubjectAdapter(getActivity(),evalList);
        categoryRecyclerview.setAdapter(catAdapter3);
        return view;
    }
}