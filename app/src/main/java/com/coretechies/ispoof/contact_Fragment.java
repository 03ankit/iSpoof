package com.coretechies.ispoof;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link contact_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class contact_Fragment extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public contact_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Account_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static contact_Fragment newInstance(String param1, String param2) {
        contact_Fragment fragment = new contact_Fragment();
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

    RecyclerView recyclerView;
    contactAdapter adapter;
    List<contactAdapter> mCountryModel;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_contacts, container, false);
        /*recyclerView = view.findViewById(R.id.contactRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new contactAdapter(dataqueue());
        recyclerView.setAdapter(adapter);*/
        return view;


    }

    /*public ArrayList<ContactModel> dataqueue()
    {
        ArrayList<ContactModel> holder=new ArrayList<>();

        ContactModel ob1=new ContactModel();
        ob1.setHeader("Alex");
        ob1.setDesc("009192354578");
        ob1.setIdContact("alex_1564");
        holder.add(ob1);

        ContactModel ob2=new ContactModel();
        ob2.setHeader("Amatya");
        ob2.setDesc("009192354578");
        ob2.setIdContact("-");
        holder.add(ob2);

        ContactModel ob3=new ContactModel();
        ob3.setHeader("Bob");
        ob3.setDesc("009192354578");
        ob3.setIdContact("bob2547");
        holder.add(ob3);

        ContactModel ob4=new ContactModel();
        ob4.setHeader("Zecean");
        ob4.setDesc("00919234578");
        ob4.setIdContact("zc");
        holder.add(ob4);

        ContactModel ob5=new ContactModel();
        ob5.setHeader("Contact 5");
        ob5.setDesc("00919254578");
        ob5.setIdContact("alex_1564");
        holder.add(ob5);

        ContactModel ob6=new ContactModel();
        ob6.setHeader("Contact 6");
        ob6.setDesc("00919235457");
        ob6.setIdContact("zc");
        holder.add(ob6);

        ContactModel ob7=new ContactModel();
        ob7.setHeader("Contact 7");
        ob7.setDesc("00919234578");
        ob7.setIdContact("bob2547");
        holder.add(ob7);

        ContactModel ob8=new ContactModel();
        ob8.setHeader("Contact 8");
        ob8.setDesc("00192354578");
        ob8.setIdContact("bob2547");
        holder.add(ob8);

        ContactModel ob9=new ContactModel();
        ob9.setHeader("Contact 9");
        ob9.setDesc("09192354578");
        ob9.setIdContact("alex_1564");
        holder.add(ob9);

        ContactModel ob10=new ContactModel();
        ob10.setHeader("Zecean");
        ob10.setDesc("00919234578");
        ob10.setIdContact("-");
        holder.add(ob10);

        ContactModel ob11=new ContactModel();
        ob11.setHeader("Contact 11");
        ob11.setDesc("00919235478");
        ob11.setIdContact("zc");
        holder.add(ob11);

        ContactModel ob12=new ContactModel();
        ob12.setHeader("Alex");
        ob12.setDesc("00919235478");
        ob12.setIdContact("alex_1564");
        holder.add(ob12);
        return holder;
    }*/

}