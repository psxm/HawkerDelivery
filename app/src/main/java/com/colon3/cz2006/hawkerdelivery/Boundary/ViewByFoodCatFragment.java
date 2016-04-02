package com.colon3.cz2006.hawkerdelivery.Boundary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.colon3.cz2006.hawkerdelivery.Adapter.DishAdapter;
import com.colon3.cz2006.hawkerdelivery.Controller.DishController;
import com.colon3.cz2006.hawkerdelivery.Entity.Dish;
import com.colon3.cz2006.hawkerdelivery.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewByFoodCatFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewByFoodCatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewByFoodCatFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private ListView list;
    public ArrayList<Dish> dishArrayList = new ArrayList<Dish>();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ViewByFoodCatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewByFoodCatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewByFoodCatFragment newInstance(String param1, String param2) {
        ViewByFoodCatFragment fragment = new ViewByFoodCatFragment();
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
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        list = (ListView) getView().findViewById(R.id.list);
        DishController controller = new DishController();

        if(position == 0){
            dishArrayList = controller.sortByRating();
            DishAdapter adapter = new DishAdapter(dishArrayList, getActivity());
            list.setAdapter(adapter);
        }
        else{

            String item = parent.getItemAtPosition(position).toString();
            dishArrayList = controller.sortByRatingByCat(item);
            DishAdapter adapter = new DishAdapter(dishArrayList, getActivity());
            list.setAdapter(adapter);
        }
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dish d = dishArrayList.get(position);
                Intent i = new Intent(getActivity(),OrderActivity.class);
                i.putExtra("dish_object",d);
                startActivity(i);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_view_by_food_cat,container,false);
        String[] foodCat = new String[] {"All Categories","Chinese","Western","Malay","Indian"};

        Spinner spinner = (Spinner)v.findViewById(R.id.food_cat_spinner);

        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_item_dark,foodCat);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerAdapter);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
