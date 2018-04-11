package org.sergiiz.rxkata;

import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;

class CountriesServiceSolved implements CountriesService {

    @Override
    public Single<String> countryNameInCapitals(Country country) {

        return Single.fromCallable(() -> country.getName().toUpperCase());

    }

    public Single<Integer> countCountries(List<Country> countries) {
        return Single.fromCallable(() -> countries.size());
    }

    public Observable<Long> listPopulationOfEachCountry(List<Country> countries) {
        return Observable.fromIterable(countries).map(e -> e.getPopulation());
    }

    @Override
    public Observable<String> listNameOfEachCountry(List<Country> countries) {
        return Observable.fromIterable(countries).map(e -> e.getName());
    }

    @Override
    public Observable<Country> listOnly3rdAnd4thCountry(List<Country> countries) {
        return Observable.fromIterable(countries).skip(2).take(2);
    }

    @Override
    public Single<Boolean> isAllCountriesPopulationMoreThanOneMillion(List<Country> countries) {
        Single<Long> allCount = Observable.fromIterable(countries).count();
        //allCount.
        Single<Long> pop = Observable.fromIterable(countries).filter(e -> e.getPopulation() > 1000000l).count();

        return Single.fromCallable(() -> pop.blockingGet().equals(allCount.blockingGet()));
    }

    @Override
    public Observable<Country> listPopulationMoreThanOneMillion(List<Country> countries) {

        return Observable.fromIterable(countries).filter(e -> e.getPopulation() > 1000000l);
    }

    @Override
    public Observable<Country> listPopulationMoreThanOneMillionWithTimeoutFallbackToEmpty(final FutureTask<List<Country>> countriesFromNetwork) {
        //Observable.fromIterable(countriesFromNetwork.)
        return null; // put your solution here
    }

    @Override
    public Observable<String> getCurrencyUsdIfNotFound(String countryName, List<Country> countries) {
        return Observable.fromIterable(countries).filter(e -> countryName.equals(e.getName())).map(e -> e.getCurrency()).defaultIfEmpty("USD");
        //return null; // put your solution here
    }

    @Override
    public Observable<Long> sumPopulationOfCountries(List<Country> countries) {
        return Observable.fromIterable(countries).map(e -> e.getPopulation()).reduce((x, y) -> x + y).toObservable();
    }

    @Override
    public Single<Map<String, Long>> mapCountriesToNamePopulation(List<Country> countries) {
        Map<String, Long> map = new HashMap<>();
       // Observable.fromIterable(countries).map(e -> Mapmap.put(e.getName(),e.getPopulation()));
        return Single.fromCallable( () -> map);
    }

    @Override
    public Observable<Long> sumPopulationOfCountries(Observable<Country> countryObservable1,
                                                     Observable<Country> countryObservable2) {
        return countryObservable1.mergeWith(countryObservable2).map(e -> e.getPopulation()).reduce((x, y) -> x + y).toObservable();
    }

    @Override
    public Single<Boolean> areEmittingSameSequences(Observable<Country> countryObservable1,
                                                    Observable<Country> countryObservable2) {
        return null; // put your solution here
    }
}
