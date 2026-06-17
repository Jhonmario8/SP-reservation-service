package com.sp.reservationservice.domain.constants;



public class DomainConstants {
    public static final String KEY_ROLE_NAME = "role_name";
    public static final String KEY_SUBJECT = "sub";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String MSG_COURT_NOT_FOUND = "Court not found";
    public static final String USER_ID_CLAIM = "userId" ;
    public static final String ROLE_NAME_CLAIM = "role_name" ;
    public static final String ONLY_ADMIN_CAN_CREATE_COURT = "Only admin can create court";
    public static final String COURT_ALREADY_RESERVED = "Court is already reserved for the selected time slot";
    public static final String ONLY_CLIENT_CAN_CREATE_RESERVATION = "Only client can create reservation";
    public static final String ONLY_ADMIN_CAN_UPDATE_COURT = "Only admin can update court";
    public static final String MSG_COURT_ALREADY_EXISTS = "Court already exists with name: ";
    public static final String ONLY_ADMIN_CAN_DISABLE_COURT = "Only admin can disable court";
    public static final String COURT_IS_DISABLED = "Court is disabled";
    public static final String MSG_COURT_TYPE_NOT_FOUND = "Court type not found";
    public static final String ONLY_CLIENT_CAN_GET_COURTS = "Only client can get courts";
    public static final String MSG_RESERVATION_NOT_FOUND = "Reservation not found";
    public static final String ONLY_CLIENT_CAN_CANCEL_RESERVATION = "Only client can cancel reservation";
    public static final String ONLY_OWNER_CAN_CANCEL_RESERVATION = "Only the owner of the reservation can cancel it";
    public static final String ONLY_ADMIN_CAN_UPDATE_RESERVATION_STATUS = "Only admin can update reservation status";
    public static final String RESERVATION_ALREADY_CANCELLED = "Reservation is already cancelled";
    public static final String RESERVATION_ALREADY_COMPLETED = "Reservation is already completed";
    public static final String ONLY_PENDING_RESERVATIONS_CAN_BE_CONFIRMED = "Only pending reservations can be confirmed";
    public static final String ONLY_CONFIRMED_RESERVATIONS_CAN_BE_COMPLETED = "Only confirmed reservations can be completed";
    public static final String ADMIN_CANNOT_CANCEL_RESERVATION = "Admin cannot cancel reservation";
    public static final String CANNOT_REVERT_TO_PENDING = "Cannot revert reservation status back to pending";
}
