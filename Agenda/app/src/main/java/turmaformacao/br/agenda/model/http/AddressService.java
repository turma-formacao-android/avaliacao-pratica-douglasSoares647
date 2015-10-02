package turmaformacao.br.agenda.model.http;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import turmaformacao.br.agenda.model.entities.Address;

/**
 * Created by dhb_s on 01/10/2015.
 */
public final class AddressService {


    private  static final String URL = "http://correiosapi.apphb.com/cep/";

    private AddressService() {
        super();
    }


    public static Address getAddressByZipCode(String zipCode) {
        Address address = null;
        try {
            java.net.URL url = new URL(URL+zipCode);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            Log.i("getAddressByZipCode", "Codigo de retorno da requisição de endereço : " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();


                ObjectMapper objectMapper = new ObjectMapper();
                address = objectMapper.readValue(inputStream,Address.class);
            }

        } catch (Exception e) {
            Log.e(AddressService.class.getName(), e.getMessage());
        }

        return address;
    }
}
