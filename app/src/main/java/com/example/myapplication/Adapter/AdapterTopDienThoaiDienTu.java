package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.ObjectClass.SanPham;
import com.example.myapplication.R;
import com.example.myapplication.View.ChiTietSanPham.ChiTietSanPhamActivity;
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
        CardView cardView;

        public ViewHolderTopDienThoai(@NonNull View itemView) {
            super(itemView);

            imHinhSanPham = itemView.findViewById(R.id.imTopDienThoaiDienTu);
            txtTenSanPham = itemView.findViewById(R.id.txtTieuDeTopDienTHoaiDienTu);
            txtGiaTien = itemView.findViewById(R.id.txtGiaDienTu);
            txtGiamGia = itemView.findViewById(R.id.txtGiamGiaDienTu);
            cardView = itemView.findViewById(R.id.cardView);
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
       final SanPham sanPham = sanPhamList.get(position);
        Picasso.with(context).load(sanPham.getANHLON()).resize(140,140).centerInside().into(holder.imHinhSanPham);
        holder.txtTenSanPham.setText(sanPham.getTENSP());

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGIA()).toString();
        holder.txtGiaTien.setText(gia+ " VND");
        holder.cardView.setTag(sanPham.getMASP());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iChitietsanpham = new Intent(context, ChiTietSanPhamActivity.class);
                iChitietsanpham.putExtra("masp",(int)view.getTag());
                context.startActivity(iChitietsanpham);
                Log.d("MASP", sanPham.getMASP()+"");
            }
        });

    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }


}
