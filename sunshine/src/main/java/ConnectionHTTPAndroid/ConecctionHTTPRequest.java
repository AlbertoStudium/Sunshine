package ConnectionHTTPAndroid;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by alberto on 07/02/2015.
 */
public class ConecctionHTTPRequest extends AsyncTask <URL,Integer,String> {

    private HttpURLConnection urlConnection = null;
    private BufferedReader reader= null;
    private InputStream inputStream= null;
    private String response= null;
    private URL url;



    @Override
    protected String doInBackground(URL... params) {
        this.url = params[0];

        try {
            this.urlConnection = (HttpURLConnection) this.url.openConnection();
            this.urlConnection.setRequestMethod("GET");
            this.urlConnection.connect();

            this.inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            // comprobamos si el inputStream devuelto no es igual a null si no seguimos con la ejecucion
            if (this.inputStream == null) {
                this.response = null;
            } else {
                reader = new BufferedReader(new InputStreamReader(this.inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                // si el String esta vacio devolvemos un null en caso contrario devolvemos la cadena
                if (buffer.length() == 0) {
                    this.response = null;

                } else {
                    this.response = buffer.toString();
                }

                reader.close();
            }
            this.urlConnection.disconnect();

        }catch(Exception e){

            Log.e("PlaceholderFragment", "Error con la conexion", e);
        }
        return this.response;
    }

}
