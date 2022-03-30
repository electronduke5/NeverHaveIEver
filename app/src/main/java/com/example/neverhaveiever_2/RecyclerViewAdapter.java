package com.example.neverhaveiever_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.neverhaveiever_2.CustomQuestionModel.CustomQModel;
import com.example.neverhaveiever_2.data.DatabaseHelper;
import com.example.neverhaveiever_2.data.QuestionContract;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<CustomQModel> models;

    public RecyclerViewAdapter(Context context, List<CustomQModel> models) {
        this.inflater = LayoutInflater.from(context);
        this.models = models;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_question_model,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        CustomQModel model = models.get(position);

        holder.txtView.setText(model.getText());
        holder.btnDelete.setOnClickListener(view ->{
            removeAt(view, position, model.getID());
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView txtView;
        final ImageView btnDelete;

        ViewHolder(View view){
            super (view);
            this.txtView = view.findViewById(R.id.txtquestion);
            this.btnDelete = view.findViewById(R.id.btnDelete);
        }
    }

    /**
     *
     * @param view
     * @param position Позиция в RecyclerView
     * @param elementID ID удаляемого элемента в БД
     */
    private void removeAt(View view, int position, int elementID){
        SQLiteDatabase db = new DatabaseHelper(view.getContext()).getWritableDatabase();
        db.delete(QuestionContract.CustomQuestion.TABLE_NAME, QuestionContract.CustomQuestion._ID + "=" + elementID, null);
        models.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,models.size());
    }
}