

package com.example.teachergradebook.holder;

import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import com.example.teachergradebook.R;
import com.example.teachergradebook.TableView.TableViewAdapter;
import com.example.teachergradebook.TableView.TableViewListener;


public class CellViewHolder extends AbstractViewHolder {

    public  EditText cell_textview;
    public  LinearLayout cell_container;
    public TableViewAdapter.MyCustomEditTextListener myCustomEditTextListener;

    public CellViewHolder(View itemView, TableViewAdapter.MyCustomEditTextListener myCustomEditTextListener) {
        super(itemView);
        this.cell_textview = (EditText) itemView.findViewById(R.id.cell_data);
        this.cell_container = (LinearLayout) itemView.findViewById(R.id.cell_container);
        this.cell_textview.addTextChangedListener(myCustomEditTextListener);
    }

    public void setData(Object data) {
        cell_textview.setText(String.valueOf(data));

        // If your TableView should have auto resize for cells & columns.
        // Then you should consider the below lines. Otherwise, you can ignore them.

        // It is necessary to remeasure itself.
        cell_container.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
        //cell_textview.requestLayout();


    }

//    public void getData(Context context){
//
//        cell_textview.requestFocus();
//        final InputMethodManager inputMethodManager = (InputMethodManager) context
//                .getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputMethodManager.showSoftInput(cell_textview, InputMethodManager.SHOW_IMPLICIT);
//
//        cell_textview.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                Log.i("MyApp",s.toString());
//            }
//        });
//    }
//
//

}