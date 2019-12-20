package handong.vacation.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import handong.vacation.myapplication.object.Fruit;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.FruitViewHolder> {
    Context context;
    List<Fruit> list;

    public CustomAdapter(Context context, List<Fruit> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FruitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // xml -> view
        View view = LayoutInflater.from(context).inflate(R.layout.custom_recycler_view,parent,false);
        FruitViewHolder viewHolder = new FruitViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FruitViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // it allocate value to custom recycler view -> one column
    class FruitViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tvName, tvDesc;

        public FruitViewHolder(@NonNull View itemView) {
            super(itemView);

            iv = itemView.findViewById(R.id.imageView1);
            tvName = itemView.findViewById(R.id.tvName);
            tvDesc = itemView.findViewById(R.id.tvDesc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos == RecyclerView.NO_POSITION)
                        return;

                    Toast.makeText(context, list.get(pos).getName()+"가 선택됨",Toast.LENGTH_SHORT).show();
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(context);
                    dlg.setTitle(tvName.getText().toString())
                            .setMessage("Do you want to delete?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which){
                                    list.remove(getAdapterPosition());
                                    notifyItemRemoved(getAdapterPosition());
                                    notifyItemRangeChanged(getAdapterPosition(),list.size());
                                    Toast.makeText(context, "삭제되었습니다",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("CANCEL",null)
                            .show();

                    return false;
                }
            });
        }

        public void setData(Fruit fruit){
            iv.setImageResource(fruit.getImageId());
            tvName.setText(fruit.getName());
            tvDesc.setText(fruit.getDescription());
        }
    }
}
