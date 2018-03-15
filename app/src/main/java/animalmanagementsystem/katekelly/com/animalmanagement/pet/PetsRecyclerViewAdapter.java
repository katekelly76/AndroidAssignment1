package animalmanagementsystem.katekelly.com.animalmanagement.pet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import animalmanagementsystem.katekelly.com.animalmanagement.ClickInterface;
import animalmanagementsystem.katekelly.com.animalmanagement.R;

/**
 * Created by katekelly on 21/02/2018.
 */
// https://developer.android.com/guide/topics/ui/layout/recyclerview.html

public class PetsRecyclerViewAdapter extends RecyclerView.Adapter<PetsRecyclerViewAdapter.ViewHolder> {




    List<Pet> petList = new ArrayList<Pet>();
    PetDatabaseHandler db;

    ClickInterface clickInterface;


    public PetsRecyclerViewAdapter(Context context, List<Pet> petList, PetDatabaseHandler db, ClickInterface clickInterface) {
        this.petList = petList;
        db = new PetDatabaseHandler(context);
        this.clickInterface = clickInterface;
    }

    public PetsRecyclerViewAdapter(List<Pet> petList) {
        this.petList = petList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
// https://developer.android.com/reference/android/view/LayoutInflater.html
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_item, parent, false);
        return new ViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.ownername.setText(petList.get(position).getPetOwnerName());
        holder.ownerphone.setText(petList.get(position).getPetOwnerPhone());
        holder.petname.setText(petList.get(position).getPetName());
        holder.genderage.setText(petList.get(position).getPetGender()+" "+petList.get(position).getPetAge()+" years");

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickInterface.clickEdit(petList.get(position).getId());
            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickInterface.clickDelete(petList.get(position).getId());

            }
        });

        Log.e("ITEM", ""+ getItemCount());


    }

    @Override
    public int getItemCount() {
        return petList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ownername, ownerphone, petname, genderage;

        public Button edit, delete;

        public ViewHolder(View itemView) {
            super(itemView);

            ownername = (TextView) itemView.findViewById(R.id.ownername);
            ownerphone = (TextView) itemView.findViewById(R.id.ownerphone);
            petname = (TextView) itemView.findViewById(R.id.petname);
            genderage = (TextView) itemView.findViewById(R.id.gender_age);

            edit = (Button) itemView.findViewById(R.id.edit);
            delete = (Button) itemView.findViewById(R.id.delete);



        }
    }
}
