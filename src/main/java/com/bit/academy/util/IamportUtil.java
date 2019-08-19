
package com.bit.academy.util;

import com.google.gson.*;
import com.siot.IamportRestClient.Iamport;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.AuthData;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.request.ScheduleEntry;
import com.siot.IamportRestClient.request.escrow.EscrowLogisInvoiceData;
import com.siot.IamportRestClient.response.*;
import com.siot.IamportRestClient.response.escrow.EscrowLogisInvoice;
import com.siot.IamportRestClient.serializer.BalanceEntrySerializer;
import com.siot.IamportRestClient.serializer.EscrowInvoiceEntrySerializer;
import com.siot.IamportRestClient.serializer.ScheduleEntrySerializer;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class IamportUtil {

    public static final String API_URL = "https://api.iamport.kr";
    protected String api_key = "0539350563680408";
    protected String api_secret = "G0rC7ocbUzaiacPLArAAVFs7LzFOhamYaNBicOt6nEpyQfkqGU1CAtKl3palAxjLlU3xIwE6a3F1jZrZ";
    protected Iamport iamport = null;

    public IamportUtil() {
        this.iamport = this.create();
    }

    protected Iamport create() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(buildGsonConverter())
                .client(client)
                .build();

        return retrofit.create(Iamport.class);
    }

    protected GsonConverterFactory buildGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        // Adding custom deserializers
        Object escrowInvoiceStrategy = new EscrowInvoiceEntrySerializer();

        gsonBuilder.registerTypeAdapter(ScheduleEntry.class, new ScheduleEntrySerializer());
        gsonBuilder.registerTypeAdapter(Schedule.class, new ScheduleEntrySerializer());
        gsonBuilder.registerTypeAdapter(PaymentBalanceEntry.class, new BalanceEntrySerializer());
        gsonBuilder.registerTypeAdapter(EscrowLogisInvoiceData.class, escrowInvoiceStrategy);
        gsonBuilder.registerTypeAdapter(EscrowLogisInvoice.class, escrowInvoiceStrategy);

        Gson myGson = gsonBuilder.create();

        return GsonConverterFactory.create(myGson);
    }


    public IamportResponse<Payment> cancelPaymentByImpUid(CancelData cancel_data) throws IamportResponseException, IOException {
        AccessToken auth = getAuth().getResponse();
        Call<IamportResponse<Payment>> call = this.iamport.cancel_payment(auth.getToken(), cancel_data);

        Response<IamportResponse<Payment>> response = call.execute();
        if ( !response.isSuccessful() )	throw new IamportResponseException( getExceptionMessage(response), new HttpException(response) );

        return response.body();
    }

    public IamportResponse<AccessToken> getAuth() throws IamportResponseException, IOException {
        Call<IamportResponse<AccessToken>> call = this.iamport.token( new AuthData(this.api_key, this.api_secret) );
        Response<IamportResponse<AccessToken>> response = call.execute();

        if ( !response.isSuccessful() )	throw new IamportResponseException( getExceptionMessage(response), new HttpException(response) );

        return response.body();
    }

    protected String getExceptionMessage(Response<?> response) {
        String error = null;
        try {
            JsonElement element = new JsonParser().parse(response.errorBody().string());
            error = element.getAsJsonObject().get("message").getAsString();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if ( error == null )	error = response.message();

        return error;
    }
}