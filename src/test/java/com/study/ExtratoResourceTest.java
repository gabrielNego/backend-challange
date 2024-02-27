package com.study;

import com.fasterxml.jackson.databind.JsonNode;
import com.study.utils.FileReader;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.IOException;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
class ExtratoResourceTest {

    @ParameterizedTest
    @ArgumentsSource(ExtratoArgumentsProvider.class)
    void testExtratoEndpoint(Integer id, String arquivoRespostaEsperada) throws IOException {
        JsonNode expectedResponse = FileReader.readExpectedResponseFile(arquivoRespostaEsperada);
        JsonPath expectedResponseJsonPath = JsonPath.from(expectedResponse.toPrettyString());

        given()
                .when()
                .get("/clientes/{id}/extrato", id)
                .then()
                .statusCode(200)
                .body(
                        "saldo.total", is(expectedResponseJsonPath.getInt("saldo.total")),
                        "saldo.data_extrato", notNullValue(),
                        "saldo.limite", is(expectedResponseJsonPath.getInt("saldo.limite")),
                        "ultimas_transacoes.size()", is(10),
                        "ultimas_transacoes", equalTo(expectedResponseJsonPath.getList("ultimas_transacoes"))
                );

    }

    @Test
    void testExtratoEndpointUsuarioNaoExiste() {
        given()
                .when()
                .get("/clientes/{id}/extrato", 99)
                .then()
                .statusCode(404);
    }

    static class ExtratoArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
//                    Arguments.of(1,"expected-response-extrato-1.json"),
//                    Arguments.of(2,"expected-response-extrato-2.json"),
//                    Arguments.of(3,"expected-response-extrato-3.json"),
//                    Arguments.of(4,"expected-response-extrato-4.json"),
//                    Arguments.of(5,"expected-response-extrato-5.json")
                    Arguments.of(6,"expected-response-extrato-6.json")
            );
        }
    }

}