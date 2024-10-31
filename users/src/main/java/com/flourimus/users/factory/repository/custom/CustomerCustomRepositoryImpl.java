package com.flourimus.users.factory.repository.custom;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.flourimus.users.dto.CustomerByEmailAndPasswordRequest;
import com.flourimus.users.dto.OrganisationVitalsResponse;
import com.flourimus.users.factory.entity.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class CustomerCustomRepositoryImpl implements CustomerCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Customer> findCustomerByEmail(final CustomerByEmailAndPasswordRequest request,
            final OrganisationVitalsResponse organisationVitals) {

        String queryString = QueryStringConstructor.customerByEmail(organisationVitals);
        TypedQuery<Customer> query = entityManager.createQuery(
                queryString, Customer.class);
        query.setParameter("email", request.getEmail());
        query.setParameter("password", request.getPassword());
        if (organisationVitals.isOrganizationId()) {
            query.setParameter("organizationId", request.getOrganizationId());
        }
        if (organisationVitals.isChannelId()) {
            query.setParameter("locationId", request.getChannelId());
        }
        if (organisationVitals.isBrandId()) {
            query.setParameter("brandId", request.getBrandId());
        }
        if (organisationVitals.isChannelId()) {
            query.setParameter("channelId", request.getChannelId());
        }
        return query.getResultStream().findFirst();
    }

}
