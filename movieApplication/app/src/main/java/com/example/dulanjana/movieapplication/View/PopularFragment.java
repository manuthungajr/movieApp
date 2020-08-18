package com.example.dulanjana.movieapplication.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.solver.widgets.Helper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dulanjana.movieapplication.Model.Movie;
import com.example.dulanjana.movieapplication.Presenter.PopularPresenter;
import com.example.dulanjana.movieapplication.Presenter.PopularPresenterImpl;
import com.example.dulanjana.movieapplication.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class PopularFragment extends Fragment implements PopularFragmentview {
    private Helper mHelper;
    GridView gridView;
    List<Movie> movieList;
    Context context;
    CustomAdapter customAdapter ;

    private OnFragmentInteractionListener mListener;

    public PopularFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static PopularFragment newInstance(String param1, String param2) {
        PopularFragment fragment = new PopularFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populatePopularMovieArray();

    }

    private void populatePopularMovieArray() {
        PopularPresenter popularPresenter = new PopularPresenterImpl(this,context);
        popularPresenter.fetchPopularMovies();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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
    public void OnSuccess(List<Movie> PopularMovies) {
        this.movieList = PopularMovies;
        customAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(Error error) {

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class CustomAdapter extends BaseAdapter{

        private final LayoutInflater mInflater;

        public CustomAdapter(Context c){
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            if(movieList!=null) {
                return movieList.size();
            }else{
                return 0;
            }

        }

        @Override
        public Object getItem(int i) {
            return movieList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = mInflater.inflate(R.layout.row_data,null);

            TextView name = view1.findViewById(R.id.movies);
            ImageView image = view1.findViewById(R.id.images);

            name.setText(movieList.get(i).getTitle());
            //   image.setText(movieList.get(i).getImageUrl());
            URL url = null;
 //           try {
//                url = new URL(movieList.get(i).getImageUrl());
//                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                image.setImageBitmap(bmp);
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
 //           } catch (IOException e) {
//                e.printStackTrace();
//            }

            return view1;
        }
    }
}
