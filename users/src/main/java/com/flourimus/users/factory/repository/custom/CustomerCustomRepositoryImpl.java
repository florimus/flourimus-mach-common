package com.flourimus.users.factory.repository.custom;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.flourimus.users.common.utils.PasswordUtil;
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

    /**
     * Finds a customer by their email and password, as well as the organisation
     * vitals of the organisation they are associated with. The query string will
     * include the email of the customer, as well as any other organisation vitals
     * that the response indicates should be included.
     * 
     * @param request            the customer by email and password request to use
     * @param organisationVitals the organisation vitals of the associated
     *                           organisation
     * @return the customer found, or an empty optional if not found
     */
    @Override
    public Optional<Customer> findCustomerByEmailAndPassword(final CustomerByEmailAndPasswordRequest request,
            final OrganisationVitalsResponse organisationVitals) {

        String queryString = QueryStringConstructor.customerByEmail(organisationVitals);

        TypedQuery<Customer> query = entityManager.createQuery(
                queryString, Customer.class);

        query.setParameter("email", request.getEmail());

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
        return query.getResultStream()
                .filter(customer -> PasswordUtil.isPasswordMatch(request.getPassword(), customer.getPassword()))
                .findFirst();
    }

}
