package com.klarna.starter;

import com.klarna.rest.Client;
import com.klarna.rest.api.checkout.CheckoutOrdersApi;
import com.klarna.rest.api.checkout.model.CheckoutOrder;
import com.klarna.rest.api.payments.model.PaymentsMerchantSession;
import com.klarna.rest.api.payments.model.PaymentsOrderLine;
import com.klarna.rest.api.payments.model.PaymentsSession;
import com.klarna.rest.http_transport.HttpTransport;
import com.klarna.rest.model.ApiException;
import com.klarna.rest.api.payments.PaymentsSessionsApi;
import com.klarna.rest.model.ContentTypeException;
import com.klarna.rest.model.ProtocolException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Klarna Checkout Example
 */
public class TestPaymentsSessionsApi
{
    public static void main( String[] args )
    {
        Client client = new Client("PN03278_76d1a9c22ca0", "2STA6ZJNi2ulRkBA", HttpTransport.NA_TEST_BASE_URL);
        //CheckoutOrdersApi checkoutOrdersApi = client.newCheckoutOrdersApi();
        com.klarna.rest.api.payments.PaymentsSessionsApi paymentsSessionsApi = client.newPaymentsSessionsApi();
        try {
            final List<PaymentsOrderLine> lines = Arrays.asList(
                    new PaymentsOrderLine()
                            .type("physical")
                            .reference("123050")
                            .name("Comatoes")
                            .quantity(10L)
                            .quantityUnit("kg")
                            .unitPrice(600L)
                            .taxRate(2500L)
                            .totalAmount(6000L)
                            .totalTaxAmount(1200L),

                    new PaymentsOrderLine()
                            .type("physical")
                            .reference("543670")
                            .name("Cananas")
                            .quantity(1L)
                            .quantityUnit("bag")
                            .unitPrice(5000L)
                            .taxRate(2500L)
                            .totalAmount(4000L)
                            .totalDiscountAmount(1000L)
                            .totalTaxAmount(800L)
            );

            PaymentsSession sessionRequest = new PaymentsSession()
                    .purchaseCountry("US")
                    .purchaseCurrency("USD")
                    .locale("en-US")
                    .orderAmount(10000L)
                    .orderTaxAmount(2000L)
                    .customPaymentMethodIds(Collections.singletonList("PAY_NOW"))
                    .orderLines(lines);

            PaymentsMerchantSession session = paymentsSessionsApi.create(sessionRequest);
            System.out.println(session);

        } catch (IOException | ProtocolException | ContentTypeException e) {
            System.out.println("Connection problem: " + e.getMessage());
        } catch (ApiException e) {
            System.out.println("API issue: " + e.getMessage());
        }



    }
}