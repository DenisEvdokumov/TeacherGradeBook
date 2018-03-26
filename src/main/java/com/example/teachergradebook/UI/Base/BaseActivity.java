package com.example.teachergradebook.UI.Base;

import android.arch.lifecycle.LifecycleRegistry;
import android.support.v7.app.AppCompatActivity;

import com.example.teachergradebook.AndroidApplication;
import com.example.teachergradebook.data.TableRepositoryComponent;
//import com.example.teachergradebook.data.repository.TableRepository;

/**
 * Created by Денис on 06.03.2018.
 */

public class BaseActivity extends AppCompatActivity {
    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    protected TableRepositoryComponent getTableRepositoryComponent() {
        return ((AndroidApplication) getApplication()).getTableRepositoryComponent();
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}