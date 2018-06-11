package com.example.suhanshu.demoasyntask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String list[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "a", "b",
            "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o"
            , "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o"
            , "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o"};
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_view);
        arrayList = new ArrayList<>();
//        arrayList.add("a");
//        arrayList.add("b");
//        arrayList.add("c");
//        arrayList.add("d");
//        arrayList.add("e");
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

//        ArrayAdapter<String> aras= (ArrayAdapter<String>) listView.getAdapter();
//        aras.add("Suhanshu");
//        aras.add("Sameer");

        new AsynTaskClass().execute();

    }

    public class AsynTaskClass extends AsyncTask<Void, String, Void> {

        ArrayAdapter<String> arrayAdapter2;
        int count=0;

        @Override
        protected void onPreExecute() {
            arrayAdapter2 = (ArrayAdapter<String>) listView.getAdapter();
            setProgressBarVisibility(true);
            setProgressBarIndeterminate(false);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (String item : list) {
                publishProgress(item);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            arrayAdapter2.add(values[0]);
            count++;
            setSupportProgress((count/list.length)*10000);
//            setProgress((count/list.length)*10000);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            setProgressBarVisibility(false);
            Toast.makeText(getApplicationContext(), "Task accomplished", Toast.LENGTH_LONG).show();

        }

    }
}
