package com.android7.relativelayoutexample;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class CustomDialog extends DialogFragment {
    public interface OnCustomDialogListener{
        void setText(String text);
        void setValue(Double value);
    }

    OnCustomDialogListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.custom_dialog,null);
        final EditText editText = view.findViewById(R.id.edit_text);


        builder.setMessage(getString(R.string.dialog_2_message))
                .setPositiveButton(getString(R.string.dialog_positive_button), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(mListener!=null){
                            mListener.setText(editText.getText().toString());
                        }
                    }
                })
                .setNegativeButton(getString(R.string.dialog_negative_button), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        builder.setView(view);
        // Create the AlertDialog object and return it
        return builder.create();

    }

    public void setOnCustomClickListener(OnCustomDialogListener listener){
        mListener = listener;
    }
}
