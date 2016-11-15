package com.baltasarq.listacompra4.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.baltasarq.listacompra4.Core.Item;
import com.baltasarq.listacompra4.R;

public class MainActivity extends AppCompatActivity {
    protected static final int CODIGO_EDICION_ITEM = 100;
    protected static final int CODIGO_ADICION_ITEM = 102;

    private ArrayAdapter<Item> adaptadorItems;
    private ListaCompra4App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.app = (ListaCompra4App) this.getApplication();

        ListView lvLista = (ListView) this.findViewById( R.id.lvLista );
        Button btInserta = (Button) this.findViewById( R.id.btInserta );

        // Lista
        this.adaptadorItems = new ArrayAdapter<>(
                this,
                android.R.layout.simple_selectable_list_item,
                app.getItemList() );
        lvLista.setAdapter( this.adaptadorItems );

        // Inserta
        btInserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent subActividad = new Intent( MainActivity.this, ItemEdicionActivity.class );

                subActividad.putExtra( "pos", -1 );
                MainActivity.this.startActivityForResult( subActividad, CODIGO_ADICION_ITEM );
            }
        });

        // Modifica
        lvLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent subActividad = new Intent( MainActivity.this, ItemEdicionActivity.class );

                subActividad.putExtra( "pos", i );
                MainActivity.this.startActivityForResult( subActividad, CODIGO_EDICION_ITEM );

                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if ( requestCode == CODIGO_ADICION_ITEM
          && resultCode == Activity.RESULT_OK )
        {
            this.adaptadorItems.notifyDataSetChanged();
            this.actualizaNumElementos();
        }

        if ( requestCode == CODIGO_EDICION_ITEM
          && resultCode == Activity.RESULT_OK )
        {
            this.adaptadorItems.notifyDataSetChanged();
        }

        return;
    }

    /** Actualiza el num. de elementos existentes en la vista. */
    private void actualizaNumElementos()
    {
        TextView lblNum = (TextView) this.findViewById( R.id.lblNum );

        lblNum.setText( Integer.toString( this.adaptadorItems.getCount() ) );
    }
}
