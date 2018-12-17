package br.com.appdog.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import java.util.List;

import javax.inject.Inject;

import br.com.appdog.model.ListDog;
import br.com.appdog.repository.DogRepository;


public class DogViewModel extends ViewModel {

    public final ObservableBoolean loading = new ObservableBoolean();

    public DogRepository dogRepository;
    @Inject
    public DogViewModel(final DogRepository repository) {
        this.dogRepository = repository;

    }

    /**
     * access the repository to login.
     * @return
     */
    public LiveData<ListDog> getListDog(final String category) {
        return dogRepository.getListDog(category);


    }

    /**
     * bsuca as url na base de dados para que o carregamento de cache das imagens seja possivel.
     * @param category
     * @return
     */
    public LiveData<List<String>> getListUrlCache(final String category) {
        return dogRepository.getListUrl(category);


    }

    /**
     * salva a  url.
     * @param urls
     * @param category
     */
    public void saveUrl(final List<String> urls, final String category){
        dogRepository.save(urls, category);
    }



    public void showLoading() {
        loading.set(true);
    }

    public void hideLoading() {
        loading.set(false);


    }
}
