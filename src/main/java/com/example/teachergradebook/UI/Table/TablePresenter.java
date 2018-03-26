package com.example.teachergradebook.UI.Table;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import com.example.teachergradebook.data.model.Grade;
import com.example.teachergradebook.data.model.Practice;
import com.example.teachergradebook.data.model.Student;
import com.example.teachergradebook.data.repository.TableRepository;
import com.example.teachergradebook.util.schedulers.RunOn;

import java.security.acl.Group;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static com.example.teachergradebook.util.schedulers.SchedulerType.UI;
import static com.example.teachergradebook.util.schedulers.SchedulerType.IO;

/**
 * Created by Денис on 19.03.2018.
 */

public class TablePresenter implements TableContract.Presenter , LifecycleObserver {

    private TableRepository repository;

    private TableContract.View view;

    private Scheduler ioScheduler;
    private Scheduler uiScheduler;

    private CompositeDisposable disposable;
    Student student = new Student();

    @Inject public TablePresenter(TableRepository repository, TableContract.View view,
                                  @RunOn(IO)Scheduler ioScheduler, @RunOn(UI) Scheduler uiScheduler){
        this.repository = repository;
        this.view = view;
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;

        if (view instanceof LifecycleObserver) {
            ((LifecycleOwner) view).getLifecycle().addObserver(this);
        }

        disposable = new CompositeDisposable();
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onAttach() {
        loadTable(false);
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onDetach() {
        disposable.clear();
    }

    @Override
    public void loadTable(boolean onlineRequired) {
        //Clear old data on view
        view.clearQuestions();

        //Load new one and populate it into view
        Disposable disposableStudent = repository.loadStudents(onlineRequired)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(this::handleReturnedStudent, this::handleError, ()-> view.stopLoadingIndicator());
        disposable.add(disposableStudent);

        Disposable disposablePractice = repository.loadPractice(onlineRequired)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(this::handleReturnedPractice, this::handleError, () -> view.stopLoadingIndicator());

        disposable.add(disposablePractice);
    }

    @Override
    public void deleteGroup(Group group) {

    }

    @Override
    public void addGrade(String grade) {
        student.setName(grade);
        repository.addStudent(student);

    }

    private void handleError(Throwable throwable) {
        view.stopLoadingIndicator();
        view.showErrorMessage(throwable.getLocalizedMessage());
    }

    private void handleReturnedStudent(List<Student> list) {
        view.stopLoadingIndicator();
        if (list !=null && !list.isEmpty()){
            view.showStudents(list);
        } else {
            view.showNoDataMessage();
        }
    }

    private void handleReturnedPractice(List<Practice> list) {
        if (list !=null && !list.isEmpty()){
            view.showPractice(list);
        } else {
            view.showNoDataMessage();
        }
    }

}

