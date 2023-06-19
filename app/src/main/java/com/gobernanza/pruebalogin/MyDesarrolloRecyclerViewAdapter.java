package com.gobernanza.pruebalogin;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.gobernanza.pruebalogin.databinding.FragmentItemBinding;

import java.util.List;

public class MyDesarrolloRecyclerViewAdapter extends RecyclerView.Adapter<MyDesarrolloRecyclerViewAdapter.ViewHolder> {

    private final List<perfil> mValues;
    private final NotasIteractionListener mListener;

    public MyDesarrolloRecyclerViewAdapter(List<perfil> items, NotasIteractionListener listener) {
        mValues = items;
        mListener = listener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvName.setText(holder.mItem.getNombre());
        holder.tvLastName.setText(holder.mItem.getApellido());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvName;
        public final TextView tvLastName;

        public perfil mItem;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            tvName = binding.editTextPersonalName;
            tvLastName = binding.editTextTextPersonLastName;

        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvName.getText() + "'";
        }
    }
}