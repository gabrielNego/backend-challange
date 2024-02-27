package com.study;

import com.study.utils.FileReader;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;

import static io.restassured.RestAssured.given;

@QuarkusTest
class TransacaoResourceTest {


    @Nested
    class FailTests {

        @ParameterizedTest
        @ValueSource(strings = {"request-transacao-descricao-null.json",
                "request-transacao-tipo-null.json",
                "request-transacao-valor-null.json"})
        void testTransacaoEndpointBadRequest(String arquivoPayloadRequisicao) {
            given()
                    .when()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(new File(FileReader.REQUEST_FILE_PATH + arquivoPayloadRequisicao))
                    .post("/clientes/1/transacoes")
                    .then()
                    .statusCode(400);
        }

        @Test
        void testTransacaoEndpointUsuarioNaoExiste() {
            given()
                    .when()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(new File(FileReader.REQUEST_FILE_PATH + "request-transacao.json"))
                    .post("/clientes/99/transacoes")
                    .then()
                    .statusCode(404);
        }
    }

}
