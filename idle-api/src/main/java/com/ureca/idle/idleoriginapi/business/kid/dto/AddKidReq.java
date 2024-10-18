package com.ureca.idle.idleoriginapi.business.kid.dto;

import java.time.LocalDate;

// TODO ENUM value validate
public record AddKidReq(String name, String gender, LocalDate birthDate) {}
