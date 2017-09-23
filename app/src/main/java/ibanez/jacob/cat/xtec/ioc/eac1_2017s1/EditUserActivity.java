package ibanez.jacob.cat.xtec.ioc.eac1_2017s1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import ibanez.jacob.cat.xtec.ioc.eac1_2017s1.domain.User;
import ibanez.jacob.cat.xtec.ioc.eac1_2017s1.util.Utils;

/**
 * Activity for editing a single {@link User}.
 *
 * @author <a href="mailto:jacobibanez@jacobibanez.com">Jacob Ibáñez Sánchez</a>.
 */
public class EditUserActivity extends AppCompatActivity {

    private static final String TAG = "EDIT_USER";

    private User user;
    private Intent data;
    private Bundle extras;
    private EditText streetText, zipCodeText, townText, phoneNumberText, webPageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        //Recuperem els widgets del layout
        streetText = (EditText) findViewById(R.id.editTextStreet);
        zipCodeText = (EditText) findViewById(R.id.editTextZipCode);
        townText = (EditText) findViewById(R.id.editTextTown);
        phoneNumberText = (EditText) findViewById(R.id.editTextPhoneNumber);
        webPageText = (EditText) findViewById(R.id.editTextWebPage);

        //Recuperem l'intent i els seus extres
        data = getIntent();
        extras = data.getExtras();

        if (extras != null && extras.getSerializable(MainActivity.EXTRA_USER) != null) {
            user = (User) extras.getSerializable(MainActivity.EXTRA_USER);

            //Omplim els widgets amb els valors que ja existeixen
            streetText.setText(user.getStreet());
            zipCodeText.setText(user.getZipCode());
            townText.setText(user.getTown());
            phoneNumberText.setText(user.getPhoneNumber());
            webPageText.setText(user.getWebSite());
        } else {
            String msg = "An error occured, no User provided";
            Log.e(TAG, msg);
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Saves the {@link User} with the current data and sends it back to {@link MainActivity}.
     *
     * @param view the view
     */
    public void saveUser(View view) {
        if (user != null) {
            //Afegim les noves dades a l'usuari. Si no n'hi ha de noves, es faran servir les que ja tenia
            user.setStreet(Utils.getTextFromEditText(streetText.getText().toString()));
            user.setZipCode(Utils.getTextFromEditText(zipCodeText.getText().toString()));
            user.setTown(Utils.getTextFromEditText(townText.getText().toString()));
            user.setPhoneNumber(Utils.getTextFromEditText(phoneNumberText.getText().toString()));
            user.setWebSite(Utils.getTextFromEditText(webPageText.getText().toString()));

            //Retornem l'intent amb les dades a l'activitat original
            data.putExtras(extras);
            setResult(RESULT_OK, data);

            finish();
        }
    }

    /**
     * Cancels the edition of the {@link User}.
     *
     * @param view the view
     */
    public void cancelEdition(View view) {
        //retornem el codi d'activitat cancel·lada i tanquem l'activitat
        setResult(RESULT_CANCELED);
        finish();
    }
}
