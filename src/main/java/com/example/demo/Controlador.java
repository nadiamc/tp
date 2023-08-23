package com.example.demo;

import com.mercadopago.client.common.AddressRequest;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.common.PhoneRequest;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

import com.mercadopago.MercadoPagoConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Controlador {


    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/detalles")
    public ModelAndView verDetalle() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("detalles");
        return modelAndView;
    }
//localhost:8080/notification
    @RequestMapping(value = "/notification", method = RequestMethod.POST)
    public ResponseEntity<Object> realizarPago(@RequestBody HashMap<String,Object> notificacion) {

        System.out.println(notificacion.toString());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/pago", method = RequestMethod.POST)
    public ResponseEntity<Object> realizarPago(@RequestBody PreferencePart preferencePart) {

        MercadoPagoConfig.setAccessToken("APP_USR-8709825494258279-092911-227a84b3ec8d8b30fff364888abeb67a-1160706432");
        MercadoPagoConfig.setIntegratorId("dev_24c65fb163bf11ea96500242ac130004");

        Item item = preferencePart.getItem();
        Payer payer = preferencePart.getPayer();

        PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                .id(item.getId())
                .title(item.getTitle())
                .description(item.getDescription())
                .pictureUrl(item.getPictureUrl())
                .categoryId(item.getCategoryId())
                .quantity(item.getQuantity())
                .currencyId(item.getCurrencyId())
                .unitPrice(new BigDecimal(item.getUnitPrice()))
                .build();

        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(itemRequest);


        Phone phone = payer.getPhone();
        PhoneRequest phoneRequest = PhoneRequest.builder()
                .areaCode(phone.getAreaCode())
                .number(phone.getNumber())
                .build();

        Address address = payer.getAddress();
        AddressRequest addressRequest = AddressRequest.builder()
                .streetName(address.getStreetName())
                .streetNumber(address.getStreetNumber())
                .zipCode(address.getZipCode())
                .build();

        Identification identification = payer.getIdentification();
        IdentificationRequest identificationRequest = IdentificationRequest.builder()
                .type(identification.getType())
                .number(identification.getNumber())
                .build();

        PreferencePayerRequest payerRequest = PreferencePayerRequest.builder()
                .name(payer.getName())
                .surname(payer.getSurname())
                .email(payer.getEmail())
                .address(addressRequest)
                .phone(phoneRequest)
                .identification(identificationRequest)
                .build();

        PreferenceBackUrlsRequest preferenceBackUrlsRequest = PreferenceBackUrlsRequest.builder()
                .failure("https://tp-mp.onrender.com/failure")
                .pending("https://tp-mp.onrender.com/pending")
                .success("https://tp-mp.onrender.com/success")
                .build();

        PreferencePaymentMethodRequest preferencePaymentMethodRequest = PreferencePaymentMethodRequest.builder()
                .id("visa")
                .build();

        List<PreferencePaymentMethodRequest> preferencePaymentMethodList = Arrays.asList(preferencePaymentMethodRequest);

        PreferencePaymentMethodsRequest preferencePaymentMethodsRequest = PreferencePaymentMethodsRequest.builder()
                .installments(6)
                .excludedPaymentMethods(preferencePaymentMethodList)
                .build();

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .payer(payerRequest)
                .backUrls(preferenceBackUrlsRequest)
                .paymentMethods(preferencePaymentMethodsRequest)
                .notificationUrl("https://tp-mp.onrender.com/notifications")
                .externalReference("holaasdfasdf@gmail.com")
                .autoReturn("approved")
                .build();


        PreferenceClient client = new PreferenceClient();
        HashMap<String, String> mensaje = new HashMap<>();

        try {

            Preference preference = client.create(preferenceRequest);
            mensaje.put("PreferenceID", preference.getId());

        } catch (MPException e) {
            throw new RuntimeException(e);
        } catch (MPApiException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }


    @RequestMapping("/failure")
    public ModelAndView verFailurePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("failure");
        return modelAndView;
    }

    @RequestMapping("/pending")
    public ModelAndView verPendingPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pending");
        return modelAndView;
    }

    @RequestMapping("/success")
    public ModelAndView verSuccessPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        return modelAndView;
    }
}

