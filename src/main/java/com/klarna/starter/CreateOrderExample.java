package com.klarna.starter;

import com.klarna.rest.Client;
import com.klarna.rest.api.payments.PaymentsOrdersApi;
import com.klarna.rest.api.payments.PaymentsSessionsApi;
import com.klarna.rest.api.payments.model.*;
import com.klarna.rest.http_transport.HttpTransport;
import com.klarna.rest.model.ApiException;
import com.klarna.rest.model.ContentTypeException;
import com.klarna.rest.model.ProtocolException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Klarna Checkout Example
 */
public class CreateOrderExample
{
    public static void main( String[] args )
    {
        Client client = new Client("PN03278_76d1a9c22ca0", "2STA6ZJNi2ulRkBA", HttpTransport.NA_TEST_BASE_URL);
        String authorizationToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjgyMzA1ZWJjLWI4MTEtMzYzNy1hYTRjLTY2ZWNhMTg3NGYzZCJ9.ewogICJzZXNzaW9uX2lkIiA6ICI2ZWFmODUwMy1kOTU2LTE5YTQtOTU2MC00ZGI3OTY3YTY1MDkiLAogICJiYXNlX3VybCIgOiAiaHR0cHM6Ly9rbGFybmEtcGF5bWVudHMtbmEucGxheWdyb3VuZC5rbGFybmEuY29tL3BheW1lbnRzIiwKICAiZGVzaWduIiA6ICJrbGFybmEiLAogICJsYW5ndWFnZSIgOiAiZW4iLAogICJwdXJjaGFzZV9jb3VudHJ5IiA6ICJVUyIsCiAgInRyYWNlX2Zsb3ciIDogZmFsc2UsCiAgImVudmlyb25tZW50IiA6ICJwbGF5Z3JvdW5kIiwKICAibWVyY2hhbnRfbmFtZSIgOiAiWW91ciBidXNpbmVzcyBuYW1lIiwKICAic2Vzc2lvbl90eXBlIiA6ICJQQVlNRU5UUyIsCiAgImNsaWVudF9ldmVudF9iYXNlX3VybCIgOiAiaHR0cHM6Ly9uYS5wbGF5Z3JvdW5kLmtsYXJuYWV2dC5jb20iLAogICJleHBlcmltZW50cyIgOiBbIHsKICAgICJuYW1lIiA6ICJpbi1hcHAtc2RrLW5ldy1pbnRlcm5hbC1icm93c2VyIiwKICAgICJwYXJhbWV0ZXJzIiA6IHsKICAgICAgInZhcmlhdGVfaWQiIDogIm5ldy1pbnRlcm5hbC1icm93c2VyLWVuYWJsZSIKICAgIH0KICB9IF0KfQ.HPTT6tiNP2wJ0HrnL6V6CBIO6XtZo2oqWeVVDfrqAbXxibxvd8xE5-mS9YGAR70k_mx5BxG3ox-nztKWBmLoY6UKslMU51ogqOE3CbQ9JgHAt3FsuOI58rjr4-SaRsqhtVz3frZ5Nkxi8qiH7ZNvrv5VTwrtMGbBQvAokQN8WPMkPLgiMLpBS3vKIvOQzNunW6PDMoSVZHCky-l049MU7glfHvySrhjSFX9-yOhAJ-QolDKzFiegPCK6UeyihWGFxayLnmSHhPIsc_h-cmJ-2hbRKg7ohgHFA6bZbiGTgNuWXBHwCjEg9UemMpD-ApjqBwkpWqPYGgxLzqx0yWJggA";

        PaymentsOrdersApi paymentsOrdersApi = client.newPaymentsOrdersApi();

        try {
            final PaymentsAddress address = new PaymentsAddress()
                    .givenName("John")
                    .familyName("Doe")
                    .email("johndoe@example.com")
                    .title("Mr")
                    .streetAddress("13 New Burlington St")
                    .streetAddress2("Apt 214")
                    .postalCode("W13 3BG")
                    .city("London")
                    .phone("01895808221")
                    .country("GB");

            final List<PaymentsOrderLine> lines = Arrays.asList(
                    new PaymentsOrderLine()
                            .type("physical")
                            .reference("123050")
                            .name("Tomatoes")
                            .quantity(10L)
                            .quantityUnit("kg")
                            .unitPrice(600L)
                            .taxRate(2500L)
                            .totalAmount(6000L)
                            .totalTaxAmount(1200L),

                    new PaymentsOrderLine()
                            .type("physical")
                            .reference("543670")
                            .name("Bananas")
                            .quantity(1L)
                            .quantityUnit("bag")
                            .unitPrice(5000L)
                            .taxRate(2500L)
                            .totalAmount(4000L)
                            .totalDiscountAmount(1000L)
                            .totalTaxAmount(800L)
            );

            PaymentsCreateOrderRequest request = new PaymentsCreateOrderRequest()
                    .billingAddress(address)
                    .shippingAddress(address)
                    .purchaseCountry("GB")
                    .purchaseCurrency("GBP")
                    .locale("en-GB")
                    .orderAmount(10000L)
                    .orderTaxAmount(2000L)
                    .orderLines(lines);

            PaymentsOrder order = paymentsOrdersApi.create(authorizationToken, request);
            System.out.println(order);

        } catch (IOException e) {
            System.out.println("Connection problem: " + e.getMessage());
        } catch (ApiException e) {
            System.out.println("API issue: " + e.getMessage());
        }
    }



    }
