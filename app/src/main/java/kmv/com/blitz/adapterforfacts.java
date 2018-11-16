package kmv.com.blitz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nirnay Mittal on 08-09-2018.
 */

public class adapterforfacts extends RecyclerView.Adapter<adapterforfacts.ViewHolder>{

    private List<String> facts = new ArrayList<>();
    private Context context;

    public adapterforfacts(List<String> facts , Context context) {
        this.facts = facts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.factitem , parent , false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.fact.setText(facts.get(position));
    }

    @Override
    public int getItemCount() {
        return facts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        private TextView fact;
        public ViewHolder(View itemView) {
            super(itemView);

            fact = itemView.findViewById(R.id.factitemtext);
        }
    }

}
