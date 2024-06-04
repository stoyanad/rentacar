package org.example.rc.repository;

import org.example.rc.domain.RentalPackage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "packages", path = "packages")
public interface RentalPackageRepository extends CrudRepository<RentalPackage, String> {

    // Not exposed by Spring Data REST
    @Override
    @RestResource(exported = false)
    <S extends RentalPackage> S save(S s);

    @Override
    @RestResource(exported = false)
    <S extends RentalPackage> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    @RestResource(exported = false)
    void deleteById(String s);

    @Override
    @RestResource(exported = false)
    void delete(RentalPackage rentalPackage);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends RentalPackage> iterable);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
