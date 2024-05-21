package com.pismo.txnroutine.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountResponse {

    @JsonProperty("account_id")
    private Long id;

    @JsonProperty("document_number")
    private String documentNumber;

}
