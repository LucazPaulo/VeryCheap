package br.com.verycheap.verycheap;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import br.com.verycheap.verycheap.API.Itens;
import br.com.verycheap.verycheap.API.SefazApi;
import br.com.verycheap.verycheap.Permissao.PermissionUtils;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class ActivityMain extends AppCompatActivity {

    private EditText edtDescricao;
    private RecyclerView rv;
    //private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtDescricao = findViewById(R.id.edtDescricao);

        //verificando a versão do android para dar as permissões
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            // Solicita as permissões
            String[] permissoes = new String[]{
                    ACCESS_COARSE_LOCATION,
                    ACCESS_FINE_LOCATION,
                    WRITE_EXTERNAL_STORAGE,
                    READ_EXTERNAL_STORAGE
            };
            PermissionUtils.validate(this, 0, permissoes);
        }


        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }


    public void ChamadaApiSefaz(View view) {

        Processo processo = new Processo(this);
        processo.execute();


     /*   try {
            ArrayList<Itens> Item = new ArrayList<Itens>();

            for (int i = 0; i < processo.getResult().length(); i++) {

                JSONObject resultado = processo.getResult().getJSONObject(i);


                Item.add(new Itens(resultado.getString("nomRazaoSocial"),
                        "R$: " + resultado.getString("valUltimaVenda"),
                        "Bairro: " + resultado.getString("nomBairro"),
                        "Descrição: " + resultado.getString("dscProduto"),
                        "Dias: " + resultado.getString("dthEmissaoUltimaVenda")));

            }

            PessoaAdapter adapter = new PessoaAdapter(Item);
            rv.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        */


      /*  double lat;
        double lon;

        try {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            SefazApi SefazApi = new SefazApi();

            // VerificaLocalizacao();

            // Inicio Localização
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            // Verifica se o GPS está ativo
            boolean enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

            // Caso não esteja ativo abre um novo diálogo com as configurações para  realizar se ativamento
            if (!enabled) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }


            String localizacao = "";
            Location location = lm.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            if (location != null) {
                lat = location.getLatitude();
                lon = location.getLongitude();

            } else {

                lat = 0.0;
                lon = 0.0;

            }

            // Fim Localização

            JSONArray result = SefazApi.RequisicaoApi(edtDescricao.getText(), lat, lon);

            ArrayList<Itens> Item = new ArrayList<Itens>();

            for (int i = 0; i < result.length(); i++) {

                JSONObject resultado = result.getJSONObject(i);


                Item.add(new Itens(resultado.getString("nomRazaoSocial"),
                        "R$: " + resultado.getString("valUltimaVenda"),
                        "Bairro: " + resultado.getString("nomBairro"),
                        "Descrição: " + resultado.getString("dscProduto"),
                        "Dias: " + resultado.getString("dthEmissaoUltimaVenda")));

            }

            PessoaAdapter adapter = new PessoaAdapter(Item);
            rv.setAdapter(adapter);

            //ArrayAdapter<Itens> adapter = new ArrayAdapter<Itens>(this, android.R.layout.simple_list_item_1, Item);

            // listView.setAdapter(adapter);

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }*/

    }


    public class Processo extends AsyncTask<JSONArray, JSONArray, JSONArray> {

        private ProgressDialog progress;
        private Context context;
        private double lat;
        private double lon;
       // private JSONArray result;


        Processo(Context context) {

            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            //Cria novo um ProgressDialogo e exibe
            progress = new ProgressDialog(context);
            progress.setMessage("Aguarde...");
            progress.setCancelable(false);
            progress.show();
        }

        @Override
        protected JSONArray doInBackground(JSONArray... paramss) {

            JSONArray result = null;
            try {
                StrictMode.ThreadPolicy policy =
                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                SefazApi SefazApi = new SefazApi();

                VerificaLocalizacao();


                result = SefazApi.RequisicaoApi(edtDescricao.getText(), this.lat, this.lon);


                // this.result = result;



                // listView.setAdapter(adapter);

               /* for (int i = 0; i < 7; i++) {

                    JSONObject resultado = result.getJSONObject(i);

                    String nomeEmpresa;

                    if (resultado.getString("nomFantasia") != null) {
                        nomeEmpresa = resultado.getString("nomFantasia");
                    } else {
                        nomeEmpresa = resultado.getString("nomRazaoSocial");
                    }

                    String valUltimaVenda = resultado.getString("valUltimaVenda");
                    String nomBairro = resultado.getString("nomBairro");
                    String dscProduto = resultado.getString("dscProduto");
                    String dthEmissaoUltimaVenda = resultado.getString("dthEmissaoUltimaVenda");


                    //Cacula a diferença de dias

                    DiasEntreDataAtual dias = new DiasEntreDataAtual();
                    int dias1 = dias.quantidadeDias(dthEmissaoUltimaVenda.substring(0, 10));


                    tvLista.append("Descriçao: " + dscProduto + "\n");
                    tvLista.append("Empresa: " + nomeEmpresa + "\n");
                    tvLista.append("Valor: R$" + valUltimaVenda + "\n");
                    tvLista.append("Bairro: " + nomBairro + "\n");
                    tvLista.append("Ha " + dias1 + " dias atras." + " \n");
                    tvLista.append("\n\n");


                }*/

            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }


        @Override
        protected void onPostExecute(JSONArray result) {
            //Cancela progressDialogo

            try {
                ArrayList<Itens> Item = new ArrayList<Itens>();

                for (int i = 0; i < result.length(); i++) {

                    JSONObject resultado = result.getJSONObject(i);


                    Item.add(new Itens(resultado.getString("nomRazaoSocial"),
                            "R$: " + resultado.getString("valUltimaVenda"),
                            "Bairro: " + resultado.getString("nomBairro"),
                            "Descrição: " + resultado.getString("dscProduto"),
                            "Dias: " + resultado.getString("dthEmissaoUltimaVenda")));

                }

                PessoaAdapter adapter = new PessoaAdapter(Item);
                rv.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }




            progress.setMessage("Carregado!");
            progress.dismiss();
        }


        private void VerificaLocalizacao() {

            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            // Verifica se o GPS está ativo
            boolean enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

            // Caso não esteja ativo abre um novo diálogo com as configurações para  realizar se ativamento
            if (!enabled) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }


            String localizacao = "";
            Location location = lm.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            if (location != null) {
                this.lat = location.getLatitude();
                this.lon = location.getLongitude();

            } else {

                this.lat = 0.0;
                this.lon = 0.0;

            }

        }


    }

}
