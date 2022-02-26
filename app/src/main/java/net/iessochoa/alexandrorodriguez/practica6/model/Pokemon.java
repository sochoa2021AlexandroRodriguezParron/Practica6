package net.iessochoa.alexandrorodriguez.practica6.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Entity(tableName = Pokemon.TABLE_NAME,
        indices = {@Index(value = {Pokemon.NOMBRE},unique = true)})
public class Pokemon implements Parcelable {
    public static final String TABLE_NAME="pokemon";
    public static final String ID= BaseColumns._ID;
    public static final String NOMBRE="nombre";
    public static final String URL="url";
    public static final String FECHA_COMPRA="fechacompra";
    // url de las imagenes de los pokemon. Utiliza este en el instituto
    //urlIMAGEN="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";
    //este tiene mejores imágenes pero no funciona en el instituto por culpa del proxy.pero las imágenes son mejores
    public static final String urlIMAGEN="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";
    //"https://pokeres.bastionbot.org/images/pokemon/";

    /**PROPIEDADES DEL POKEMON**/
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name=ID)
    private int id;
    @ColumnInfo(name = NOMBRE)
    @NonNull
    @SerializedName("name")//retrofit
    private String nombre;
    @ColumnInfo(name = URL)
    @NonNull
    private String url;//este campo tiene el mismo nombre que el JSON
    @ColumnInfo(name = FECHA_COMPRA)
    @NonNull
    private Date fechaCompra;

    /**CONSTRUCTOR SIN ID Y POSIBLE NULO EN LA FECHA**/
    public Pokemon(@NonNull String nombre, @NonNull String url, Date fechaCompra) {
        this.nombre = nombre;
        this.url = url;
        this.fechaCompra = fechaCompra;
    }

    /**GETTERS & SETTERS**/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    @NonNull
    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(@NonNull Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    /**
     * PERMITE BUSCAR LA IMAGEN DEL POKEMON
     * @return
     */
    public String getUrlImagen(){
        String url = getUrl();
        String[] pokemonIndex = url.split("/");
        return (urlIMAGEN+pokemonIndex[pokemonIndex.length-1] +".png");
    }

    /**
     * PARA QUE NOS DEVUELVA LA FECHA EN FORMATO LOCAL
     * @return
     */
    public String getFechaCompraFormatoLocal(){
        //para mostrar la fecha en formato del idioma del dispositivo
        if(fechaCompra!=null) {
            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM,
                    Locale.getDefault());
            return df.format(fechaCompra);
        }else{
            //si es un pokemon de internet no tenemos fecha
            return "";
        }
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
