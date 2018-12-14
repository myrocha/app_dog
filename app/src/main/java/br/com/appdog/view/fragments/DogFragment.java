package br.com.appdog.view.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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


    /*    public static DogFragment newInstance(final String category) {
            mCategory = category;
            return new DogFragment();

        }*/
    FragmentDogBinding binding;


    @Inject
    public DogViewModel dogViewModel;

   /* @Inject
    public DogFragment() {

    }*/

    public static DogFragment newInstance(final String category) {
        DogFragment fragmentFirst = new DogFragment();
       Bundle args = new Bundle();
        args.putString("category", category);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // page = getArguments().getInt("someInt", 0);
        mCategory = getArguments().getString("category");
    }
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dog, container, false);
        binding.setViewModel(dogViewModel);
        getListDog();

        return binding.getRoot();
      //  return inflater.inflate(R.layout.fragment_dog, container, false);

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

        dogViewModel.getListDog(mCategory).observe(this, response -> {

            if (response != null) {
                final RecyclerView recyclerView = binding.recyclerResource;
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
                ListDogAdapter adapter = new ListDogAdapter(getContext());
                recyclerView.setAdapter(adapter);
                adapter.setList(response.getList()) ;
            } else {
                binding.progressbarLogin.setVisibility(View.GONE);
            }



        });


    }


}
