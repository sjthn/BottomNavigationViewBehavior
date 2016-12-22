package com.example.srijith.bottomnavigationviewbehavior;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView cakeList = (RecyclerView) findViewById(R.id.cakeList);
        cakeList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        String[] cakeArray = getResources().getStringArray(R.array.cake_names);
        cakeList.setAdapter(new CakesAdapter(cakeArray));
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationViewBehavior());
    }

    private class CakesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private final String[] cakeArray;

        CakesAdapter(String[] cakeArray) {
            this.cakeArray = cakeArray;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);
            return new RecyclerView.ViewHolder(itemView) {
                @Override
                public String toString() {
                    return super.toString();
                }
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((TextView) holder.itemView.findViewById(android.R.id.text1)).setText(cakeArray[position]);
        }

        @Override
        public int getItemCount() {
            return cakeArray.length;
        }

    }

}
