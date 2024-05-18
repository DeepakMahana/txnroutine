package com.pismo.txnroutine.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountRequest {

    private String documentNumber;
}
