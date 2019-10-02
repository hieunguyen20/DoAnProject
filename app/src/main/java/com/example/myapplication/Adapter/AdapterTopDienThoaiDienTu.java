package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.ObjectClass.SanPham;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterTopDienThoaiDienTu extends RecyclerView.Adapter<AdapterTopDienThoaiDienTu.ViewHolderTopDienThoai> {
    Context context;
    List<SanPham> sanPhamList;
    int layout;

    public AdapterTopDienThoaiDienTu(Context context, int layout, List<SanPham> sanPhamList){
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.layout = layout;
    }

    public class ViewHolderTopDienThoai extends RecyclerView.ViewHolder{
        ImageView imHinhSanPham;
        TextView txtTenSanPham, txtGiaTien, txtGiamGia;

        public ViewHolderTopDienThoai(@NonNull View itemView) {
            super(itemView);

            imHinhSanPham = itemView.findViewById(R.id.imTopDienThoaiDienTu);
            txtTenSanPham = itemView.findViewById(R.id.txtTieuDeTopDienTHoaiDienTu);
            txtGiaTien = itemView.findViewById(R.id.txtGiaDienTu);
            txtGiamGia = itemView.findViewById(R.id.txtGiamGiaDienTu);
        }
    }

    @NonNull
    @Override
    public ViewHolderTopDienThoai onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layout, parent, false);

        ViewHolderTopDienThoai viewHolderTopDienThoai = new ViewHolderTopDienThoai(view);

        return viewHolderTopDienThoai;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTopDienThoai holder, int position) {
       SanPham sanPham = sanPhamList.get(position);
        Picasso.with(context).load(sanPham.getANHLON()).resize(140,140).centerInside().into(holder.imHinhSanPham);
        holder.txtTenSanPham.setText(sanPham.getTENSP());

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGIA()).toString();
        holder.txtGiaTien.setText(gia+ " VND");

    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }


}
