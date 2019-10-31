package com.appface.akhil.sqlitedb.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.appface.akhil.sqlitedb.beans.ToDO;
import com.appface.akhil.sqlitedb.view.MVCListItemView;
import com.appface.akhil.sqlitedb.view.ToDoListItemMVCImpl;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewViewHolder> implements MVCListItemView.ListItemClickListener {

    private static final String TAG = "ToDoAdapter";
    private Context context;
    private List<ToDO> todos;
    private MVCListItemView.ListItemClickListener listItemClickListener;

    public ToDoAdapter(Context context, List<ToDO> todos, MVCListItemView.ListItemClickListener listItemClickListener) {
        this.context = context;
        this.todos = todos;
        this.listItemClickListener = listItemClickListener;
    }

    @NonNull
    @Override
    public ToDoViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ToDoListItemMVCImpl toDoListItemMVC = new ToDoListItemMVCImpl((LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE ), viewGroup);
        toDoListItemMVC.initViews();
        toDoListItemMVC.setListItemClickListener(this);
        return new ToDoViewViewHolder(toDoListItemMVC);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewViewHolder toDoViewViewHolder, int i) {
        final ToDO toDO = todos.get(i);
        toDoViewViewHolder.listItemMVC.bindDataToView(toDO);
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    @Override
    public void onItemClicked(int position) {
        listItemClickListener.onItemClicked(position);
    }

    // ViewHolder Class

    public class ToDoViewViewHolder extends RecyclerView.ViewHolder {

        private ToDoListItemMVCImpl listItemMVC;

        public ToDoViewViewHolder(ToDoListItemMVCImpl listItemMVC) {
            super(listItemMVC.getRootView());
            this.listItemMVC = listItemMVC;
        }
    }
}
