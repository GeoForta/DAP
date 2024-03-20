package com.example.ex3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    //localBinder este o clasa definita in cadrul
    //serviciului. Serviciul ofera un obiect IBinder
    //oentru a permite altor componente sa interactioneze cu serviciul
    private char[][] tabla;
    private boolean[][] patrateApasate;
    private final IBinder binder = new LocalBinder();
    public MyService() {
    }

    //clasa locala pentru a obtine IBinder pentru comunicare
    public class LocalBinder extends Binder
    {
        MyService getService()
        {
            return MyService.this;
        }
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        tabla = new char[3][3];
        patrateApasate = new boolean[3][3];

        //indicam ca celulele sunt goale
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j)
            {
                tabla[i][j] = ' ';
                patrateApasate[i][j] = false;
            }

    }

    public void placeSymbol(int linie, int coloana, char simbol)
    {
        if (linie >= 0 && linie < 3 && coloana >= 0 && coloana < 3 && tabla[linie][coloana] == ' ')
        {
            tabla[linie][coloana] = simbol;
            patrateApasate[linie][coloana] = true;

            gameState();
        }
    }

    public void gameState()
    {
        for (int i = 0; i < 3; ++i)
        {
            if (tabla[i][0] == tabla[i][1] && tabla[i][1] == tabla[i][2] && tabla[i][0] != ' ')
            {
                //linia orizontala
                String castigator;
                if (tabla[i][0] == 'X')
                {
                    castigator = "Player X";
                }
                else
                {
                    castigator = "Player 0";
                }
                sendGameResultEvent(castigator + "wins");
            }
        }
        for (int i = 0; i < 3; ++i)
        {
            if (tabla[0][i] == tabla[1][i] && tabla[1][i] == tabla[2][i] && tabla[0][i] != ' ')
            {
                //linia verticala
                String castigator;
                if (tabla[0][i] == 'X')
                {
                    castigator = "Jucator X";
                }
                else
                {
                    castigator = "Jucator 0";
                }
                sendGameResultEvent(castigator + "castiga");
            }
        }
        for (int i = 0; i < 3; ++i)
        {
            if (tabla[0][0] == tabla[1][1] && tabla[1][1] == tabla[2][2] && tabla[0][0] != ' ')
            {
                //diagonala
                String castigator;
                if (tabla[0][0] == 'X')
                {
                    castigator = "Player X";
                }
                else
                {
                    castigator = "Player 0";
                }
                sendGameResultEvent(castigator + "wins");
            }
        }

        if (isDraw())
        {
            sendGameResultEvent("Draw");
            return;
        }

    }
    private boolean isDraw()
    {
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                if (tabla[i][j] == ' ')
                {
                    return false; //jocul se continua
                }
            }
        }
        return true; // e remiza
    }

    private void sendGameResultEvent(String message)
    {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    public void setTabla(char[][] tabla) {
        this.tabla = tabla;
    }
}