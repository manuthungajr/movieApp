package com.example.dulanjana.movieapplication.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dulanjana.movieapplication.Model.Movie;
import com.example.dulanjana.movieapplication.Presenter.UpcomingPresenter;
import com.example.dulanjana.movieapplication.Presenter.UpcomingPresenterImpl;
import com.example.dulanjana.movieapplication.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class upComingFragment extends Fragment implements UpcomingFragmentView{


    GridView gridView;
    List<Movie> movieList;
    Context context;
    CustomAdapter customAdapter;

    private OnFragmentInteractionListener mListener;

    public upComingFragment() {
        // Required empty public constructor
    }

    public static upComingFragment newInstance(String param1, String param2) {
        upComingFragment fragment = new upComingFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateUpcomingMovieArray();
    }

    private void populateUpcomingMovieArray() {
        UpcomingPresenter upcomingPresenter = new UpcomingPresenterImpl(this,context);
         upcomingPresenter.fetchUpComingMovies();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myFragmentView = inflater.inflate(R.layout.fragment_popular, container, false);
        gridView = (GridView) myFragmentView.findViewById(R.id.popularGridview);
        // Inflate the layout for this fragment
        customAdapter = new CustomAdapter(getActivity());
        gridView.setAdapter(customAdapter);
        return myFragmentView;
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
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void OnSuccess(List<Movie> UpComingMovies) {
        this.movieList = UpComingMovies;
        customAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(Error error) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private class CustomAdapter extends BaseAdapter {

        private final LayoutInflater mInflater;


        public CustomAdapter(Context c){
            this.mInflater = LayoutInflater.from(c);
        }
        @Override
        public int getCount() {
            if (movieList != null) {
                return movieList.size();
            } else {
                return 0;
            }
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.row_data,null);

            TextView name = view1.findViewById(R.id.movies);
            ImageView image = view1.findViewById(R.id.images);

            name.setText(movieList.get(i).getTitle());
            //   image.setText(movieList.get(i).getImageUrl());
//            URL url = null;
//            try {
//                url = new URL(movieList.get(i).getImageUrl());
//                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                image.setImageBitmap(bmp);
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            return view1;
        }
    }
}
