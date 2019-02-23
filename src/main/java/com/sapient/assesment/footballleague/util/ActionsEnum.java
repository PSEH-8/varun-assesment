package com.sapient.assesment.footballleague.util;

/**
 * ActionsEnum
 */

public enum ActionsEnum {

    GET_COUNTRIES("get_countries"), GET_LEAGUES("get_leagues"), GET_STANDINGS("get_standings");

    private String action;

    private ActionsEnum(String action) {
        this.action = action;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    
}