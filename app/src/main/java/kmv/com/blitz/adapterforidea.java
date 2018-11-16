package kmv.com.blitz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nirnay Mittal on 09-09-2018.
 */

public class adapterforidea extends RecyclerView.Adapter<adapterforidea.ViewHolder>{

    private List<String> idea = new ArrayList<>();
    private Context context;

    public adapterforidea(List<String> idea, Context context) {
        this.idea = idea;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.ideaitem,parent ,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final int[] i = {1};
        final String ida = idea.get(position);
        StringBuilder a = new StringBuilder();

        for(int j=0;j<15;j++)
        {
            try {
                a.append(ida.charAt(j));
            }catch(Exception e)
            {
                break;
            }
        }

        final String n = a + "...";

        holder.idea.setText(a.toString());

        holder.arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(i[0] == 1)
                {
                    holder.arrow.setBackgroundResource(R.drawable.arrow_up);
                    holder.idea.setText(ida);
                    i[0] =0;

                }
                else
                {
                    holder.arrow.setBackgroundResource(R.drawable.arrow_down);
                    holder.idea.setText(n);
                    i[0] =1;
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return idea.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton arrow;
        TextView idea;
        public ViewHolder(View itemView) {
            super(itemView);

            arrow = itemView.findViewById(R.id.arrow);
            idea = itemView.findViewById(R.id.ideatext);


        }
    }
}
