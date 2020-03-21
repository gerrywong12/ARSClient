package com.example.ars;

import android.content.Context;
import android.graphics.ColorSpace;
import android.media.Image;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ars.Interface.RecyclerViewClickListener;
import com.example.ars.Model.ReservationModel;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReservationsRecyclerViewAdapter extends RecyclerView.Adapter<ReservationsRecyclerViewAdapter.RecyclerHolder> {
    List<ReservationModel> models;
    Context context;
    ImageButton btnSeeReservationDetail;
    private RecyclerViewClickListener listener;
    private int reservation_id;

    public ReservationsRecyclerViewAdapter(Context context, List<ReservationModel> models, RecyclerViewClickListener listener) {
        this.models = models;
        this.context = context;
        this.listener = listener;
    }
    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reservations_row, viewGroup, false);
//        btnSeeReservationDetail = (ImageButton) view.findViewById(R.id.btnDetail);
//
//        btnSeeReservationDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                System.out.println(i.);
//            }
//        })
        return new RecyclerHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, final int position) {


        try{
            String bookingDate = models.get(position).getStartBookingDateTime();
            String displayDate;
            String displayTIme;


            Date date1=new SimpleDateFormat("yyyy-mm-dd").parse(bookingDate.substring(0, 10));
            SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd MMM yyyy");
            displayDate = formatter.format(date1);

            Date time1=new SimpleDateFormat("hh:mm").parse(bookingDate.substring(11, 16));
            System.out.println(bookingDate.substring(11, 16));
            SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm a");
            displayTIme = formatterTime.format(time1);


            System.out.println("DATEE");
            System.out.println(displayDate);
            holder.dateText.setText(displayDate.toString());
            holder.timeText.setText(displayTIme);
        } catch (Exception e) {
            System.out.println("ADA ERROR = " + e);
        }
        holder.numberOfPaxText.setText(Integer.toString(models.get(position).getNumber_of_pax()));
        reservation_id = models.get(position).getReservation_id();
        System.out.println("RESERVATION ID = " + reservation_id);



    }

    public List<ReservationModel> getList() {
        return this.models;
    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView dateText;
        TextView timeText;
        TextView numberOfPaxText;
        TextView paxText;
        ImageButton btnDetail;
        private WeakReference<RecyclerViewClickListener> listenerRef;
        public RecyclerHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            dateText = itemView.findViewById(R.id.date_text);
            timeText = itemView.findViewById(R.id.time_text);
            numberOfPaxText = itemView.findViewById(R.id.number_of_pax_text);
            paxText = itemView.findViewById(R.id.pax);
            btnDetail = itemView.findViewById(R.id.btnDetail);
            listenerRef = new WeakReference<>(listener);
            btnDetail.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if (v.getId() == btnDetail.getId()) {
                Toast.makeText(v.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
//                View row = v.getLayoutManager().findViewByPosition(YourPosition);

            } else {
                Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }

            listenerRef.get().onPositionClicked(getAdapterPosition());
        }
    }
}
