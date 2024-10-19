package com.flourimus.users.facade;

import com.flourimus.users.dto.CustomerDto;

public interface CustomerFacade {

    public CustomerDto getCustomer(final Integer id);

}
