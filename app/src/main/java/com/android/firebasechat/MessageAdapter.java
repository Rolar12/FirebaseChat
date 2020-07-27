package com.android.firebasechat;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<FireBaseMessage> {
    public MessageAdapter(@NonNull Context context, int resource, List<FireBaseMessage> messages) {
        super(context, resource, messages);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater()
                    .inflate(R.layout.message_item, parent, false);
        }

        ImageView photoImageView = convertView.findViewById(R.id.photoImageView);
        TextView textTextView = convertView.findViewById(R.id.textTextView);
        TextView nameTextView = convertView.findViewById(R.id.nameTextView);

        FireBaseMessage fireBaseMessage = getItem(position);

        boolean isText = fireBaseMessage.getImageUrl() == null;

        if (isText) {
            textTextView.setVisibility(View.VISIBLE);
            photoImageView.setVisibility(View.INVISIBLE);
            textTextView.setText(fireBaseMessage.getText());
        } else {
            textTextView.setVisibility(View.GONE);
            photoImageView.setVisibility(View.VISIBLE);
            Glide.with(photoImageView.getContext()).load(fireBaseMessage.getImageUrl()).into(photoImageView);
        }

        nameTextView.setText(fireBaseMessage.getName());

        return convertView;
    }
}
