package br.com.verycheap.verycheap.API;

import android.text.Editable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SefazApi {

    private OkHttpClient client = new OkHttpClient();



    public JSONArray RequisicaoApi(Editable descricao, double lat, double lon) throws IOException {

        try {
            JSONObject obj = new JSONObject();
            obj.put("descricao", descricao);
            obj.put("dias", 3);
            obj.put("latitude", lat);
            obj.put("longitude", lon);
            obj.put("raio", 15);

            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, obj.toString());

            Request request = new Request.Builder()
                    .url("http://api.sefaz.al.gov.br/sfz_nfce_api/api/public/consultarPrecosPorDescricao")
                    .header("AppToken", "84a57ce11ac1cf23adc5bd70b822f9db50dca9dd")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            assert response.body() != null;
            String respota = response.body().string();

            return new JSONArray(respota);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //FIM
        return null;
    }

}
