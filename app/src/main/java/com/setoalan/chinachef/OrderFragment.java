package com.setoalan.chinachef;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OrderFragment extends DialogFragment implements DialogInterface.OnClickListener {

    @Bind(R.id.radio_group_pick_up_delivery) private RadioGroup mPickUpDelivery;
    @Bind(R.id.radio_pick_up) private RadioButton mPickUp;
    @Bind(R.id.radio_delivery) private RadioButton mDelivery;
    @Bind(R.id.customer_name) private EditText mCustomerName;
    @Bind(R.id.customer_phone_number) private EditText mCustomerPhoneNumber;
    @Bind(R.id.customer_address) private EditText mCustomerAddress;
    @Bind(R.id.radio_group_payment) private RadioGroup mPayment;
    @Bind(R.id.radio_cash) private RadioButton mCash;
    @Bind(R.id.radio_credit_debit) private RadioButton mCreditDebit;
    @Bind(R.id.customer_card_number) private EditText mCardNumber;
    @Bind(R.id.customer_card_exp_date) private EditText mCardExpDate;
    @Bind(R.id.layout_credit_card) private LinearLayout mCreditCardLayout;

    private OnOrderDialogResult mOnOrderDialogResult;

    public interface OnOrderDialogResult {
        void onNameResult(String data);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mOnOrderDialogResult = (OnOrderDialogResult) activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_new_order, null);

        ButterKnife.bind(this, view);

        mPickUpDelivery.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_pick_up) {
                    mCustomerAddress.setVisibility(View.INVISIBLE);
                    mCash.setVisibility(View.INVISIBLE);
                    mCreditDebit.setVisibility(View.INVISIBLE);
                    mCreditCardLayout.setVisibility(View.INVISIBLE);
                } else if (checkedId == R.id.radio_delivery) {
                    mCustomerAddress.setVisibility(View.VISIBLE);
                    mCash.setVisibility(View.VISIBLE);
                    mCreditDebit.setVisibility(View.VISIBLE);
                    if (mCreditDebit.isChecked()) mCreditCardLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        mPayment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_cash)
                    mCreditCardLayout.setVisibility(View.INVISIBLE);
                else
                    mCreditCardLayout.setVisibility(View.VISIBLE);
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.new_order)
                .setPositiveButton(R.string.create_order, this)
                .create();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (mCustomerName.getText().toString().trim().equals("")) {
            Toast.makeText(getActivity(), "Name is required.", Toast.LENGTH_SHORT).show();
        } else if (mCustomerPhoneNumber.getText().toString().trim().equals("")) {
            Toast.makeText(getActivity(), "Phone number is required.", Toast.LENGTH_SHORT).show();
        } else if (mDelivery.isChecked() && mCustomerAddress.getText().toString().trim().equals("")) {
            Toast.makeText(getActivity(), "Address is required.", Toast.LENGTH_SHORT).show();
        } else if (mCreditDebit.isChecked() && (mCardNumber.getText().toString().trim().equals("") || mCardExpDate.getText().toString().trim().equals(""))) {
            Toast.makeText(getActivity(), "Card info is requried.", Toast.LENGTH_SHORT).show();
        } else {
            Order.newOrder(getActivity(), mCustomerName.getText().toString(),
                    mCustomerPhoneNumber.getText().toString(),
                    mPickUp.isChecked(),
                    mCustomerAddress.getText().toString(),
                    mCardNumber.getText().toString(),
                    mCardExpDate.getText().toString());
            mOnOrderDialogResult.onNameResult(mCustomerName.getText().toString());
        }
    }

}
