package com.example.sampleapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    String uri1="https://i3.ytimg.com/vi/bQaWsVQSLdY/default.jpg";
    String uri2="https://i4.ytimg.com/vi/cJQCniWQdno/mqdefault.jpg";
    String uri3="https://i1.ytimg.com/vi/D8dA4pE5hEY/mqdefault.jpg";
    String[] urls={uri1,uri2,uri3};
    public int[] textureArrayWin = {
            R.drawable.samplepicture1,
            R.drawable.samplepicture2,
            R.drawable.samplepicture3,
            R.drawable.samplepicture4,
            R.drawable.samplepicture5,
            R.drawable.samplepicture6
    };

    DatabaseReference reff;
    Button btnSave;
    //User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*// Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");*/

        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*User user = new User();
                reff = FirebaseDatabase.getInstance().getReference("User");
                user.setName("Joel 5");
                user.setAge("25 6:34pm 12/6/19");
                reff.setValue(user);*/
                /*reff = FirebaseDatabase.getInstance().getReference("User");
                String id = reff.push().getKey();
                User user = new User();
                user.setName("Joel2");
                user.setAge("lmao yeet oof");
                reff.child(id).setValue(user);
                Toast.makeText(MainActivity.this, "Data Received Successfully",Toast.LENGTH_SHORT).show();*/
                Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                startActivity(intent);
            }
        });

        GridView grd=(GridView)findViewById(R.id.gridView);
        grd.setAdapter(new MyAdapter(this));
        grd.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent,View v,int pos,long id)
            {
                Toast.makeText(getBaseContext(),"pic"+(pos+1)+"select ", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public class MyAdapter extends BaseAdapter{
        private Context context;
        ImageView ivImage;

        public MyAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return textureArrayWin.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.layout_listitem, null, false);
            }
            ivImage = (ImageView)convertView.findViewById(R.id.ivPicture);
            ivImage.setImageResource(textureArrayWin[position]);

            return convertView;
        }
    }

    public class getAllVideoLink extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... strings) {
            reff = FirebaseDatabase.getInstance().getReference("Video");
            reff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    showData(dataSnapshot);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            return null;
        }
    }

    private void showData(DataSnapshot dataSnapshot) {
        /*for(int i = 0; i < dataSnapshot.getChildrenCount(); i++) {
            VideoInfo videoInfo = new VideoInfo();
            videoInfo.setVideoTitle(ds.child("video " + i).getValue(VideoInfo.class).getVideoTitle());
        }*/
        for(DataSnapshot ds : dataSnapshot.getChildren()){

            VideoInfo videoInfo = new VideoInfo();
            videoInfo.setVideoTitle(ds.child("Video").getValue(VideoInfo.class).getVideoTitle());
        }
    }
}

