package com.example.teachergradebook.TableView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.evrencoskun.tableview.sort.SortState;

import com.example.teachergradebook.R;
import com.example.teachergradebook.holder.CellViewHolder;
import com.example.teachergradebook.holder.ColumnHeaderViewHolder;
//import com.example.teachergradebook.holder.MoodCellViewHolder;
import com.example.teachergradebook.holder.RowHeaderViewHolder;
import com.example.teachergradebook.data.Cell;
import com.example.teachergradebook.data.ColumnHeader;
import com.example.teachergradebook.data.RowHeader;

/**
 * Created by Денис on 19.02.2018.
 */


public class TableViewAdapter extends AbstractTableAdapter<ColumnHeader, RowHeader, Cell> {

    // Cell View Types by Column Position
    private static final int MOOD_CELL_TYPE = 1;
    private static final int GENDER_CELL_TYPE = 2;
    // add new one if it necessary..

    private static final String LOG_TAG = TableViewAdapter.class.getSimpleName();

    public TableViewAdapter(Context p_jContext) {
        super(p_jContext);

    }

    /**
     * This is where you create your custom Cell ViewHolder. This method is called when Cell
     * RecyclerView of the TableView needs a new RecyclerView.ViewHolder of the given type to
     * represent an item.
     *
     * @param viewType : This value comes from "getCellItemViewType" method to support different
     *                 type of viewHolder as a Cell item.
     *
     * @see #getCellItemViewType(int);
     */
    @Override
    public RecyclerView.ViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        View layout;

        layout = LayoutInflater.from(mContext).inflate(R.layout.table_view_cell_layout,
                        parent, false);
        return new CellViewHolder(layout);


    }


    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object cellItemModel, int
            columnPosition, int rowPosition) {
        Cell cell = (Cell) cellItemModel;


//             Get the holder to update cell item text
            CellViewHolder viewHolder = (CellViewHolder) holder;
            viewHolder.setData(cell.getData());

    }


    @Override
    public RecyclerView.ViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {

        // Get Column Header xml Layout
        View layout = LayoutInflater.from(mContext).inflate(R.layout
                .table_view_column_header_layout, parent, false);

        // Create a ColumnHeader ViewHolder
        return new ColumnHeaderViewHolder(layout, getTableView());
    }


    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object
            columnHeaderItemModel, int columnPosition) {
        ColumnHeader columnHeader = (ColumnHeader) columnHeaderItemModel;

        // Get the holder to update cell item text
        ColumnHeaderViewHolder columnHeaderViewHolder = (ColumnHeaderViewHolder) holder;
        columnHeaderViewHolder.setColumnHeader(columnHeader);
    }


    @Override
    public RecyclerView.ViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {

        // Get Row Header xml Layout
        View layout = LayoutInflater.from(mContext).inflate(R.layout
                .table_view_row_header_layout, parent, false);

        // Create a Row Header ViewHolder
        return new RowHeaderViewHolder(layout);
    }


    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object rowHeaderItemModel,
                                          int rowPosition) {
        RowHeader rowHeader = (RowHeader) rowHeaderItemModel;

        // Get the holder to update row header item text
        RowHeaderViewHolder rowHeaderViewHolder = (RowHeaderViewHolder) holder;
        rowHeaderViewHolder.row_header_textview.setText(String.valueOf(rowHeader.getData()));
    }


    @Override
    public View onCreateCornerView() {
        // Get Corner xml layout
        View corner = LayoutInflater.from(mContext).inflate(R.layout.table_view_corner_layout, null);
//        corner.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SortState sortState = TableViewAdapter.this.getTableView().getRowHeaderSortingStatus();
//                if(sortState != SortState.ASCENDING) {
//                    Log.d("TableViewAdapter", "Order Ascending");
//                    TableViewAdapter.this.getTableView().sortRowHeader(SortState.ASCENDING);
//                } else {
//                    Log.d("TableViewAdapter", "Order Descending");
//                    TableViewAdapter.this.getTableView().sortRowHeader(SortState.DESCENDING);
//                }
//            }
//        });
        return corner;
    }

    @Override
    public int getColumnHeaderItemViewType(int position) {
        // The unique ID for this type of column header item
        // If you have different items for Cell View by X (Column) position,
        // then you should fill this method to be able create different
        // type of CellViewHolder on "onCreateCellViewHolder"
        return 0;
    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        // The unique ID for this type of row header item
        // If you have different items for Row Header View by Y (Row) position,
        // then you should fill this method to be able create different
        // type of RowHeaderViewHolder on "onCreateRowHeaderViewHolder"
        return 0;
    }

    @Override
    public int getCellItemViewType(int column) {
        // The unique ID for this type of cell item
        // If you have different items for Cell View by X (Column) position,
        // then you should fill this method to be able create different
        // type of CellViewHolder on "onCreateCellViewHolder"

        return 0;

    }
}
