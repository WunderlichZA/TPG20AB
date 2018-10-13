package za.ac.cut.hockeyapplication;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.backendless.Backendless;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.List;

import businesslayer.model.Users;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemsFragment extends android.support.v4.app.Fragment {

    ListView listView;
    private ProgressDialog progressDialog;
    private List<Users> usersList;
    private ArrayAdapter<Users> adapter;

    public ItemsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_items, container, false);

        listView = view.findViewById(R.id.list_view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadUsers();
    }

    public void loadUsers(){
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMax(100);
        progressDialog.setMessage("Loading users");
        progressDialog.setTitle("Roles");
        progressDialog.show();

        try {

            Backendless.Data.of(Users.class).find(new BackendlessCallback<List<Users>>() {
                @Override
                public void handleResponse(List<Users> response) {
                    if (response != null) {
                        usersList = response;
                        progressDialog.dismiss();
                        adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, usersList);
                        listView.setAdapter(adapter);
                        Log.e("Tag", "handleResponse: " + usersList.get(1).getName().toString());
                    }
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    super.handleFault(fault);
                    progressDialog.dismiss();
                    //errorMessage.setVisibility(View.VISIBLE);
                    Log.e("Tag", "handleResponse: " + fault.getMessage());
                }
            });
        } catch (Exception e) {
            Log.e("Exception", "getUsers: " + e.getMessage());
        }
    }

}
