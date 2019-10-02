package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.ObjectClass.ThuongHieu;
import com.example.myapplication.R;
import com.example.myapplication.View.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMucActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterThuongHieuLon extends RecyclerView.Adapter<AdapterThuongHieuLon.ViewHolderThuongHieu> {

    Context context;
    List<ThuongHieu> thuongHieus;
    boolean kiemtra;

    public AdapterThuongHieuLon(Context context, List<ThuongHieu> thuongHieus, boolean kiemtra){
        this.context = context;
        this.thuongHieus = thuongHieus;
        this.kiemtra = kiemtra;
    }



    public class ViewHolderThuongHieu extends RecyclerView.ViewHolder {

        TextView txtTieuDeThuongHieu;
        ImageView imHinhThuongHieu;
        ProgressBar progressBar;
        LinearLayout linearLayout;

        public ViewHolderThuongHieu(@NonNull View itemView) {
            super(itemView);

            txtTieuDeThuongHieu = itemView.findViewById(R.id.txtTieuDeThuongHieuLonDienTu);
            imHinhThuongHieu = itemView.findViewById(R.id.imHinhThuongHieuLonDienTu);
            progressBar = itemView.findViewById(R.id.progress_bar_download);
            linearLayout = itemView.findViewById(R.id.lnThuongHieuLon);
        }
    }

    @NonNull
    @Override
    public ViewHolderThuongHieu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_recycler_thuonghieulon, parent, false);

        ViewHolderThuongHieu viewHolder = new ViewHolderThuongHieu(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderThuongHieu holder, final int position) {
        final ThuongHieu thuongHieu = thuongHieus.get(position);
        holder.txtTieuDeThuongHieu.setText(thuongHieu.getTENTHUONHIEU());


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("click", thuongHieus.get(position).getTENTHUONHIEU()+ thuongHieus.get(position).getMATHUONGHIEU());
                Intent iHienThiSanPhamTheoDanhMuc = new Intent(context, HienThiSanPhamTheoDanhMucActivity.class);
                iHienThiSanPhamTheoDanhMuc.putExtra("MALOAI", thuongHieu.getMATHUONGHIEU());
                iHienThiSanPhamTheoDanhMuc.putExtra("TENLOAI",thuongHieu.getTENTHUONHIEU());
                iHienThiSanPhamTheoDanhMuc.putExtra("KIEMTRA", kiemtra);

                context.startActivity(iHienThiSanPhamTheoDanhMuc);
            }
        });

        Picasso.with(context).load(thuongHieu.getHINHTHUONGHIEU()).resize(180,120).centerInside().into(holder.imHinhThuongHieu, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public int getItemCount() {
        return thuongHieus.size();
    }


}
