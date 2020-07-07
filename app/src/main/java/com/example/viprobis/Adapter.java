package com.example.viprobis;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {


    View V_store;




    private static OnClicker listener;
    private String[] data;
    private String collect;



    public interface OnClicker{
        void onItemClick(int position);
    }

    public Adapter(OnClicker listener, String[] data ){
        this.listener =  listener;

        this.data=data;

    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

        LayoutInflater inflater= LayoutInflater.from(parent.getContext()) ;
        final View view=inflater.inflate(R.layout.recycler_view_invoice,parent,false);
        final viewHolder vHolder= new viewHolder(view);



        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        String datas=data[position];
        int a=position+1;
        holder.category_text.setText(datas);
        holder.pos_text.setText(a+".");


    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        TextView category_text;
        TextView pos_text;
        LinearLayout itemworker;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            category_text=itemView.findViewById(R.id.text_view_name);
            pos_text=itemView.findViewById(R.id.posi);




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!= null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }

            });

        }
    }
}
