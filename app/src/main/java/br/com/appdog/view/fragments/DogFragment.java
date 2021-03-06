package br.com.appdog.view.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import br.com.appdog.R;
import br.com.appdog.databinding.FragmentDogBinding;
import br.com.appdog.view.adapter.ListDogAdapter;
import br.com.appdog.viewmodel.DogViewModel;
import dagger.android.support.AndroidSupportInjection;

public class DogFragment extends Fragment {
    private String mCategory;
    FragmentDogBinding binding;
    @Inject
    public DogViewModel dogViewModel;






    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCategory = getArguments().getString("category");
    }
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dog, container, false);
        binding.setViewModel(dogViewModel);
        getListDog();

        return binding.getRoot();


    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mCategory = bundle.getString("category");
        }
    }

    public void getListDog() {

        dogViewModel.getListUrlCache(mCategory).observe(this, response -> {
            final RecyclerView recyclerView = binding.recyclerResource;
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
            ListDogAdapter adapter = new ListDogAdapter(getContext());
            recyclerView.setAdapter(adapter);
            if (response == null || response.size() == 0) {
                dogViewModel.getListDog(mCategory).observe(this, result -> {
                    if (result != null) {
                        adapter.setList(/*response.getList()*/result.getList()) ;
                        dogViewModel.saveUrl(result.getList(), mCategory);
                    } else {
                        //aviso de erro
                        binding.emptyState.setVisibility(View.VISIBLE);
                    }

                });
                } else {

                adapter.setList(response) ;

            }

            binding.progressbarLogin.setVisibility(View.GONE);





        });



    }


}
