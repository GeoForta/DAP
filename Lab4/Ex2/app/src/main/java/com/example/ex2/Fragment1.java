package com.example.ex2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_1, container, false);

        Button buton1 = rootView.findViewById(R.id.button1);
        buton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDescription();
            }
        });
        Button buton2 = rootView.findViewById(R.id.button2);
        buton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDescription2();
            }
        });
        Button buton3 = rootView.findViewById(R.id.button3);
        buton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDescription3();
            }
        });

        Button buton4 = rootView.findViewById(R.id.button4);
        buton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDescription4();
            }
        });



        return rootView;
    }

    private boolean isDialogOpen = false;

    private void showDescription() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Descriere capitol 1");
        builder.setMessage("Capitolul 1 ne introduce într-un univers plin de mister și suspans, prezentându-ne familia protagonistă care se mută într-un mic sat aflat în mijlocul unei păduri dense și întunecate. O atmosferă tensionată este creată încă de la început, pe măsură ce familia începe să exploreze mediul înconjurător, dezvăluind indicii despre istoria încărcată de legende despre vrăjitoare și forțe supranaturale care bântuie această regiune izolată.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (isDialogOpen) {
                    dialog.dismiss();
                    isDialogOpen = false;
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                isDialogOpen = true;
            }
        });

        dialog.show();
    }

    private void showDescription2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Descriere capitol 2");
        builder.setMessage("În Capitolul 2, suspansul și misterul se adâncesc într-o manieră captivantă. Familia protagonistă continuă să exploreze împrejurimile satului din pădurea întunecată și descoperă indicii îngrijorătoare despre trecutul său misterios. În același timp, evenimente neașteptate și întâlniri cu personaje enigmatice ridică întrebări despre adevărata natură a locului și a locuitorilor săi.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (isDialogOpen) {
                    dialog.dismiss();
                    isDialogOpen = false;
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                isDialogOpen = true;
            }
        });

        dialog.show();

    }
    private void showDescription3() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Descriere capitol 3");
        builder.setMessage("În Capitolul 3, intriga atinge cote noi de intensitate, iar misterele încep să se desfășoare într-un mod captivant. Familia protagonistă se confruntă cu provocări tot mai mari și cu amenințări din ce în ce mai sinistre pe măsură ce își continuă explorarea în pădurea întunecată. Întâlniri cu personaje stranii și descoperiri surprinzătoare dezvăluie detalii obscure despre istoria întunecată a satului și despre forțele malefice care îi amenință pe locuitorii săi.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (isDialogOpen) {
                    dialog.dismiss();
                    isDialogOpen = false;
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                isDialogOpen = true;
            }
        });

        dialog.show();
    }

    private void showDescription4() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Descriere capitol 4");
        builder.setMessage("În Capitolul 4, tensiunea atinge punctul culminant pe măsură ce secretele încep să se dezvăluie și pericolele se intensifică. Familia protagonistă se află în mijlocul unor evenimente dramatice și încercări dificile în confruntarea cu amenințările din pădurea întunecată. În acest capitol, misterul crește și se îngroașă, iar personajele trebuie să-și folosească ingeniozitatea și curajul pentru a supraviețui și a dezlega enigmele din jurul lor.\n" +
                "\n" +
                "Va urma...");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (isDialogOpen) {
                    dialog.dismiss();
                    isDialogOpen = false;
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                isDialogOpen = true;
            }
        });

        dialog.show();
    }

}