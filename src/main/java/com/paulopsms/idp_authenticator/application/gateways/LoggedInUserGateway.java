package com.paulopsms.idp_authenticator.application.gateways;

import com.paulopsms.idp_authenticator.domain.entities.user.User;

public interface LoggedInUserGateway {

    User getLoggedInUser();
}
