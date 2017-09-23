package ibanez.jacob.cat.xtec.ioc.eac1_2017s1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ibanez.jacob.cat.xtec.ioc.eac1_2017s1.domain.User;
import ibanez.jacob.cat.xtec.ioc.eac1_2017s1.util.Utils;

/**
 * The Main activity.
 * <p>
 * It manages the creation of a {@link User}, and the edition of its data.
 * <p>
 * The activity has a single UI with two different states, wich switch depending of there's a
 * {@link User} already created or not. There's a second activity for editing the {@link User}'s
 * attributes.
 *
 * @author <a href="mailto:jacobibanez@jacobibanez.com">Jacob Ibáñez Sánchez</a>.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The constant EXTRA_USER for sending a {@link User} between activitis inside an {@link Intent}.
     */
    public static final String EXTRA_USER = "ibanez.jacob.cat.xtec.ioc.eac1_2017s1.MainActivity.EXTRA_USER";

    private static final int ACTIVITY_EDIT_USER = 0;

    private User user;
    private LinearLayout userDataLayout;
    private EditText userNameText;
    private TextView userNameLabel, streetLabel, zipCodeLabel, townLabel, phoneNumberLabel, webPageLabel;
    private ImageButton addButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recuperem els widgets i les vistes del layout
        userDataLayout = (LinearLayout) findViewById(R.id.layoutUserData);
        userNameLabel = (TextView) findViewById(R.id.textViewUserName);
        userNameText = (EditText) findViewById(R.id.editTextUserName);
        streetLabel = (TextView) findViewById(R.id.textViewStreet);
        zipCodeLabel = (TextView) findViewById(R.id.textViewZipCode);
        townLabel = (TextView) findViewById(R.id.textViewTown);
        phoneNumberLabel = (TextView) findViewById(R.id.textViewPhoneNumber);
        webPageLabel = (TextView) findViewById(R.id.textViewWebPage);
        addButton = (ImageButton) findViewById(R.id.buttonAdd);
        cancelButton = (ImageButton) findViewById(R.id.buttonCancel);

        switchState();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ACTIVITY_EDIT_USER) {
            if (resultCode == RESULT_OK) {
                if (data.getExtras() != null) {
                    //recuperem l'usuari editat
                    user = (User) data.getExtras().getSerializable(EXTRA_USER);
                    switchState();
                }
            }
        }
    }

    /**
     * This method adds a new {@link User}.
     *
     * @param view the view
     */
    public void addUser(View view) {
        if (!userNameText.getText().toString().isEmpty()
                && !"".equals(userNameText.getText().toString().trim())) {
            //Si s'ha introduït un nom d'usuari, creem l'usuari
            String userName = userNameText.getText().toString();
            user = new User(userName);

            Utils.hideSoftKeyboard(this);
            switchState();
        } else {
            //No s'ha introduït nom d'usuari
            Toast.makeText(this, R.string.toast_warn_empty_user_name, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * This method cancels the addition of a {@link User}.
     *
     * @param view the view
     */
    public void cancelUser(View view) {
        userNameText.setText(null);
    }

    /**
     * This method edits the {@link User} attributes.
     *
     * @param view the view
     */
    public void editUser(View view) {
        //Creem l'intent explícit per obrir la nova activitat i li afegim els extres
        Bundle extras = new Bundle();
        extras.putSerializable(EXTRA_USER, user);

        Intent editUserIntent = new Intent(this, EditUserActivity.class);
        editUserIntent.putExtras(extras);

        //Comencem la nova activitat
        startActivityForResult(editUserIntent, ACTIVITY_EDIT_USER);
    }

    /**
     * This method removes the created {@link User}.
     *
     * @param view the view
     */
    public void removeUser(View view) {
        user = null;
        switchState();
    }

    /**
     * This method dials (not rings) the {@link User}'s phone number.
     *
     * @param view the view
     */
    public void dialUserPhoneNumber(View view) {
        if (user.getPhoneNumber() != null) {
            Intent intent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + user.getPhoneNumber()));
            startActivity(intent);
        } else {
            //El telèfon no ha sigut informat
            Toast.makeText(this, R.string.toast_error_phone_number, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * This method opens the {@link User}'s web site.
     *
     * @param view the view
     */
    public void viewUserWebSite(View view) {
        if (user.getWebSite() != null) {
            String webPage = user.getWebSite();
            if (!URLUtil.isValidUrl(webPage)) {
                webPage = "http://" + webPage;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webPage));
            startActivity(intent);
        } else {
            //La url no està informada o no és vàlida
            Toast.makeText(this, R.string.toast_error_web_page, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Sends the {@link User}
     * <p>
     * It checks for all {@link User} attributes to be filled.
     *
     * @param view The view
     */
    public void sendUser(View view) {
        if (user != null
                && user.getName() != null
                && user.getStreet() != null
                && user.getZipCode() != null
                && user.getTown() != null
                && user.getPhoneNumber() != null
                && user.getWebSite() != null) {
            //Totes les dades estan indicades
            Toast.makeText(this, R.string.toast_info_data_sent, Toast.LENGTH_LONG).show();
        } else {
            //Falten dades
            Toast.makeText(this, R.string.toast_error_missing_info, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * This method switches the state of the main activity, wich has a visible part of the layout
     * or another, if the {@link MainActivity#user} property is {@code null} or not
     */
    private void switchState() {
        if (user == null) {
            //Si no hi ha usuari creat, amaguem el layout amb les dades i fem visibles els dos botons
            userDataLayout.setVisibility(View.INVISIBLE);
            addButton.setVisibility(View.VISIBLE);
            cancelButton.setVisibility(View.VISIBLE);
            userNameText.setVisibility(View.VISIBLE);

            //Posem el valor per defecte de l'etiqueta del nom
            userNameLabel.setText(R.string.txt_user_name);
        } else {
            //Si ja hi ha usuari, fem desaparèixer els dos botons inicials i fem visibles les dades.
            addButton.setVisibility(View.INVISIBLE);
            cancelButton.setVisibility(View.INVISIBLE);
            userNameText.setVisibility(View.INVISIBLE);
            userNameText.setText(null);
            userDataLayout.setVisibility(View.VISIBLE);

            //Canviem el text de l'etiqueta amb el nom de l'usuari
            userNameLabel.setText(user.getName());

            //modifiquem els valors de les dades a la interfície gràfica
            String street = getString(R.string.undefined_value);
            String zipCode = getString(R.string.undefined_value);
            String town = getString(R.string.undefined_value);
            String phoneNumber = getString(R.string.undefined_value);
            String webPage = getString(R.string.undefined_value);

            if (user.getStreet() != null) {
                street = user.getStreet();
            }
            if (user.getZipCode() != null) {
                zipCode = user.getZipCode();
            }
            if (user.getTown() != null) {
                town = user.getTown();
            }
            if (user.getPhoneNumber() != null) {
                phoneNumber = user.getPhoneNumber();
            }
            if (user.getWebSite() != null) {
                webPage = user.getWebSite();
            }

            streetLabel.setText(street);
            zipCodeLabel.setText(zipCode);
            townLabel.setText(town);
            phoneNumberLabel.setText(phoneNumber);
            webPageLabel.setText(webPage);
        }
    }
}
