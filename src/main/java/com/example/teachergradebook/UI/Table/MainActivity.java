package com.example.teachergradebook.UI.Table;

import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.filter.Filter;
import com.evrencoskun.tableview.pagination.Pagination;
import com.example.teachergradebook.R;
import com.example.teachergradebook.TableView.TableViewAdapter;
import com.example.teachergradebook.UI.Base.BaseActivity;
import com.example.teachergradebook.data.model.Grade;
import com.example.teachergradebook.data.model.Practice;
import com.example.teachergradebook.data.model.Student;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements TableContract.View {

    @BindView(R.id.refresh) SwipeRefreshLayout refreshLayout;

    private List<com.example.teachergradebook.data.model.Student> mRowHeaderList;
    private List<Practice> mColumnHeaderList;
    private List<ArrayList<Student>> mCellList = new ArrayList<ArrayList<Student>>();

    public ArrayList<com.example.teachergradebook.data.model.Student> students = new ArrayList<com.example.teachergradebook.data.model.Student>();
    public ArrayList<Practice> practices = new ArrayList<Practice>();

    private AbstractTableAdapter mTableViewAdapter;
    private TableView mTableView;
    private Filter mTableFilter; // This is used for filtering the table.
    private Pagination mPagination; // This is used for paginating the table.

    @Inject TablePresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Student student = new Student("I love you");

        Practice practice = new Practice(12);

        students.add(student);
        practices.add(practice);
        mCellList.add(students);
        ButterKnife.bind(this);
        initializePresenter();

        RelativeLayout fragment_container = findViewById(R.id.fragment_container);
        // Create Table view
        mTableView = createTableView();
        mTableViewAdapter.setAllItems(practices, students , mCellList);
        fragment_container.addView(mTableView);



    }

    private void initializePresenter() {
        DaggerTableComonent.builder()
                .tablePresenterModule(new TablePresenterModule(this))
                .tableRepositoryComponent(getTableRepositoryComponent())
                .build()
                .inject(this);
    }

    private TableView createTableView() {
        TableView tableView = new TableView(getApplicationContext());

        // Set adapter
        mTableViewAdapter = new TableViewAdapter(getApplicationContext());
        tableView.setAdapter(mTableViewAdapter);

        refreshLayout.setOnRefreshListener(() -> presenter.loadTable(true));
        //mTableViewAdapter.setRowHeaderItems();

        // Disable shadow
        //tableView.getSelectionHandler().setShadowEnabled(false);

        // Set layout params
        FrameLayout.LayoutParams tlp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams
                .MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        tableView.setLayoutParams(tlp);

        // Set TableView listener
        //tableView.setTableViewListener(new TableViewListener(tableView));
        return tableView;
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }


    @Override
    public void showGroup(List<Group> groups) {

    }

    @Override
    public void showStudents(List<com.example.teachergradebook.data.model.Student> student) {
//        mTableViewAdapter.setRowHeaderItems(students);
//        mTableViewAdapter.setCellItems(students);
    }

    @Override
    public void showPractice(List<com.example.teachergradebook.data.model.Practice> practice) {

    }

    @Override
    public void showTable(List<com.example.teachergradebook.data.model.Student> students, List<com.example.teachergradebook.data.model.Practice> practices, List<Grade> grades) {

    }

    @Override
    public void clearQuestions() {

    }

    @Override
    public void showNoDataMessage() {

    }

    @Override
    public void showErrorMessage(String error) {

    }

    @Override
    public void showQuestionDetail(com.example.teachergradebook.data.model.Student student) {

    }

    @Override
    public void stopLoadingIndicator() {

    }

    @Override
    public void showEmptySearchResult() {

    }
}
