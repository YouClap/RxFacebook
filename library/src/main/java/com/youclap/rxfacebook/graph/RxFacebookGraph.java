package com.youclap.rxfacebook.graph;

import com.facebook.AccessToken;

/**
 * Class that handles Facebook Graph requests
 */
public final class RxFacebookGraph {

    /**
     * Method which calls the /user/me endpoint to fetch the user data for the given access token.
     *
     * @param accessToken Access token to be used in the request
     * @param fields      Field list to request
     */
    public static RxFacebookGraphRequestSingle newMeRequest(AccessToken accessToken, String... fields) {

        return new RxFacebookGraphRequestSingle(accessToken, fields);
    }
}
