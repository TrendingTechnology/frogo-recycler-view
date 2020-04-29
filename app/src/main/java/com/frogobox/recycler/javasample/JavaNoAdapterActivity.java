package com.frogobox.recycler.javasample;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.frogobox.recycler.FrogoRecyclerView;
import com.frogobox.recycler.R;
import com.frogobox.recycler.base.BaseActivity;
import com.frogobox.recycler.boilerplate.adapter.callback.FrogoAdapterCallback;
import com.frogobox.recycler.model.ExampleModel;
import com.frogobox.recycler.util.Constant;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class JavaNoAdapterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activityFrogoRvSampleBinding.getRoot());
        setupFrogoRecyclerView();
        setupDetailActivity("Java No Adapter");
    }

    private ArrayList<ExampleModel> listData() {
        ArrayList<ExampleModel> exampleModels = new ArrayList<>();
//        exampleModels.add(new ExampleModel(Constant.NICK_NAME));
//        exampleModels.add(new ExampleModel(Constant.NICK_NAME));
//        exampleModels.add(new ExampleModel(Constant.NICK_NAME));
//        exampleModels.add(new ExampleModel(Constant.NICK_NAME));
        return exampleModels;
    }

    private void setupFrogoRecyclerView() {

        FrogoAdapterCallback frogoAdapterCallback = new FrogoAdapterCallback<ExampleModel>() {
            @Override
            public void setupInitComponent(@NotNull View view, ExampleModel data) {
                // Init component content item recyclerview
                TextView tvExample = view.findViewById(R.id.tv_example_item);
                tvExample.setText(data.getName());
            }

            @Override
            public void onItemClicked(ExampleModel data) {
                // setup item clicked on frogo recycler view
                showToast(data.getName());
            }

            @Override
            public void onItemLongClicked(ExampleModel data) {
                // setup item long clicked on frogo recycler view
                showToast(data.getName());
            }
        };

        activityFrogoRvSampleBinding.frogoRecyclerView.injector()
                .addData(listData())
                .addCustomView(R.layout.frogo_rv_list_type_1)
                .addEmptyView(null)
                .addCallback(frogoAdapterCallback)
                .createLayoutLinearVertical(false)
                .createAdapter()
                .build(activityFrogoRvSampleBinding.frogoRecyclerView);

    }

}
