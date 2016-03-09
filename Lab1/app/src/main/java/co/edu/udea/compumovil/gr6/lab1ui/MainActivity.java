package co.edu.udea.compumovil.gr6.lab1ui;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private DatePicker d;
    private String date="";
    TextView textoFecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] paises= getResources().getStringArray(R.array.countries);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,paises);
        AutoCompleteTextView textView=(AutoCompleteTextView)findViewById(R.id.pais);
        textView.setAdapter(adapter);
        d=new DatePicker();
        Spinner spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapterSpinner=ArrayAdapter.createFromResource(
                this,R.array.hobbies_array,android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);
        textoFecha=(TextView)findViewById(R.id.textView4);
    }
    public void showDatePicker(View view){
        if(d!=null){
            d.show(getFragmentManager(),"datePicker");
        }
        else{
            d=new DatePicker();
        }
    }
    public void showInfo(View view){
        TextView out=(TextView)findViewById(R.id.textView);
        out.setText("");
        String ape,pais,telefono,direccion,email,hobbies,favorite,genero,fecha;
        String error=getResources().getString(R.string.error);
        String name=((EditText)findViewById(R.id.nombre)).getText().toString();
        if("".equals(name)){
            out.append(error);
            return;
        }
        ape=((EditText)findViewById(R.id.apellido)).getText().toString();
        if("".equals(ape)){
            out.append(error);
            return;
        }
        telefono=((EditText)findViewById(R.id.tel)).getText().toString();
        if("".equals(telefono)){
            out.append(error);
            return;
        }
        CheckBox c=(CheckBox)findViewById(R.id.checkBox);
        if(c.isChecked()){
            favorite=getResources().getString(R.string.favorito)+" : "+getResources().getString(R.string.isFav)+"\n";
        }else{
            favorite=getResources().getString(R.string.favorito)+" : "+getResources().getString(R.string.isNFav)+"\n";
        }
        email=((EditText)findViewById(R.id.mail)).getText().toString();
        if("".equals(email)){
            out.append(error);
            return;
        }
        direccion=((EditText)findViewById(R.id.address)).getText().toString();
        if("".equals(direccion)){
            out.append(error);
            return;
        }
        pais=((AutoCompleteTextView)findViewById(R.id.pais)).getText().toString();
        if("".equals(pais)){
            out.append(error);
            return;
        }
        RadioGroup r=(RadioGroup)findViewById(R.id.sexo);
        RadioButton rb=(RadioButton)findViewById(r.getCheckedRadioButtonId());
        if(rb==null) {
            out.append(error);
            return;
        }else{
            genero=rb.getText().toString();
        }
        fecha=d.getDia()+"/"+d.getMes()+"/"+d.getAnio();
        hobbies=((Spinner)findViewById(R.id.spinner)).getSelectedItem().toString();
        if("".equals(hobbies)){
            out.append(error);
            return;
        }
        name=getResources().getString(R.string.name)+" : "+name;
        ape=getResources().getString(R.string.ape)+" : "+ape;
        telefono=getResources().getString(R.string.tel)+" : "+telefono;
        pais=getResources().getString(R.string.pais)+" : "+pais;
        direccion=getResources().getString(R.string.address)+" : "+direccion;
        email=getResources().getString(R.string.email)+" : "+email;
        hobbies=getResources().getString(R.string.hobbies)+" : "+hobbies;
        genero=getResources().getString(R.string.sexo)+" : "+genero;
        fecha=getResources().getString(R.string.date)+" : "+fecha;
        String salida = name+"\n"+ape+"\n"+telefono+"\n"+favorite+ pais+"\n"+direccion+"\n"+email+"\n"+hobbies
                +"\n"+genero+"\n"+fecha;
        out.append(salida);
    }

    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int i, int i1, int i2) {
        date=new StringBuilder().append(i2).append("/").append(i1).append("/").append(i).toString();
        textoFecha.setText(date);
    }
}
