package com.example.spike.collegeproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BDSQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FornecedoresDB";

    private static final String TABELA_FORNECEDORES = "Fornecedores";
    private static final String ID = "id";
    private static final String NOME = "nome";
    private static final String TIPO = "tipo";
    private static final String DESCRICAO = "descricao";

    private static final String[] COLUNAS = {ID, NOME, TIPO, DESCRICAO};


    public BDSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE Fornecedores(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome text," +
                "tipo text," +
                "descricao text)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Fornecedores");
    }

    public void addFornecedor(Fornecedor fornecedor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOME, fornecedor.getNome());
        values.put(TIPO, fornecedor.getTipo());
        values.put(DESCRICAO, fornecedor.getDescricao());
        db.insert(TABELA_FORNECEDORES, null, values);
        db.close();
    }

    public Fornecedor getFornecedor(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABELA_FORNECEDORES,
                COLUNAS,
                "id = ?",
                new String[]{String.valueOf(id)},
                null, null, null, null);
        if (cursor == null) {
            return null;
        } else {
            cursor.moveToFirst();
            Fornecedor fornecedor = cursorToFornecedor(cursor);
            return fornecedor;
        }
    }

    private Fornecedor cursorToFornecedor(Cursor cursor) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(Integer.parseInt(cursor.getString(0)));
        fornecedor.setNome(cursor.getString(1));
        fornecedor.setTipo(cursor.getString(2));
        fornecedor.setDescricao(cursor.getString(3));
        return fornecedor;
    }

    public ArrayList<Fornecedor> getAllFornecedores() {
        ArrayList<Fornecedor> listaFornecedores = new ArrayList<Fornecedor>();
        String query = "SELECT    *  FROM  " + TABELA_FORNECEDORES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Fornecedor fornecedor = cursorToFornecedor(cursor);
                listaFornecedores.add(fornecedor);

            } while
                    (cursor.moveToNext());
        }
        return listaFornecedores;
    }


    public Cursor carregaDados() {
        ArrayList<Fornecedor> listaFornecedores = new ArrayList<Fornecedor>();
        String query = "SELECT    *  FROM  " + TABELA_FORNECEDORES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return  cursor;
    }

    public  int updateFornecedor(Fornecedor fornecedor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOME, fornecedor.getNome());
        values.put(TIPO, fornecedor.getTipo());
        values.put(DESCRICAO, fornecedor.getDescricao());
        int i = db.update(TABELA_FORNECEDORES,  //tabela
                values,  //  valores
                ID + "  =  ?",  //  colunasparacomparar
                new String[]{String.valueOf(fornecedor.getId())});  //parÃ¢metros
        db.close();
        return i;  //  nÃºmero de  linhas modificadas
    }

    public  int deleteFornecedor(Fornecedor fornecedor) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABELA_FORNECEDORES,  //tabela
                ID + "  =  ?",    //  colunasparacomparar
                new String[]{String.valueOf(fornecedor.getId())});
        db.close();
        return i;  //  nÃºmerode  linhasexcluÃ­das}
    }
}